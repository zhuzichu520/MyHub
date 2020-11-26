package com.zhuzichu.android.web.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ProgressBar
import android.widget.ZoomButtonsController
import com.alibaba.android.arouter.facade.annotation.Route
import com.hiwitech.android.libs.tool.hideView
import com.hiwitech.android.libs.tool.showView
import com.qmuiteam.qmui.widget.webview.QMUIWebView
import com.qmuiteam.qmui.widget.webview.QMUIWebViewClient
import com.qmuiteam.qmui.widget.webview.QMUIWebViewContainer
import com.zhuzichu.android.shared.base.FragmentBase
import com.zhuzichu.android.shared.domain.UseCaseGetReadme
import com.zhuzichu.android.shared.entity.arg.ArgWeb
import com.zhuzichu.android.shared.route.RoutePath
import com.zhuzichu.android.web.BR
import com.zhuzichu.android.web.R
import com.zhuzichu.android.web.databinding.FragmentWebBinding
import com.zhuzichu.android.shared.view.XWebView
import com.zhuzichu.android.web.viewmodel.ViewModelWeb
import java.lang.reflect.Field

@Route(path = RoutePath.Web.FRAGMENT_WEB_MAIN)
class FragmentWeb : FragmentBase<FragmentWebBinding, ViewModelWeb, ArgWeb>() {

    private val useCaseGetReadme by lazy {
        UseCaseGetReadme()
    }

    companion object {
        private const val PROGRESS_PROCESS = 0
        private const val PROGRESS_GONE = 1
    }

    private lateinit var webView: XWebView

    private lateinit var progressHandler: ProgressHandler

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_web

    override fun initView() {
        super.initView()
        initTopBar()
        initWebView()
    }

    private fun initTopBar() {
        binding.topbar.addLeftImageButton(R.drawable.ic_topbar_back, R.id.topbar_left_back_button)
            .setOnClickListener {
                back()
            }
        updateTitle(arg.title)
    }

    private fun initWebView() {
        webView = XWebView(requireContext())
        progressHandler = ProgressHandler(binding.progressBar)
        val needDispatchSafeAreaInset = arg.needDispatchSafeAreaInset == true
        binding.webviewContainer.addWebView(webView, needDispatchSafeAreaInset)
        binding.webviewContainer.setCustomOnScrollChangeListener { _, scrollX, scrollY, oldScrollX, oldScrollY ->
            onScrollWebContent(scrollX, scrollY, oldScrollX, oldScrollY)
        }
        if (needDispatchSafeAreaInset) {
            hideView(binding.topbar)
            binding.webviewContainer.fitsSystemWindows = true
        } else {
            showView(binding.topbar)
            binding.webviewContainer.fitsSystemWindows = false
        }
        webView.webChromeClient = getWebViewChromeClient()
        webView.webViewClient = getWebViewClient()
        webView.requestFocus(View.FOCUS_DOWN)
        setZoomControlGone(webView)
        configWebView(binding.webviewContainer, webView)
        webView.loadUrl(arg.url)
    }

    private fun getWebViewChromeClient(): WebChromeClient {
        return ExplorerWebViewChromeClient(this)
    }

    private fun getWebViewClient(): QMUIWebViewClient {
        return ExplorerWebViewClient(
            this,
            arg.needDispatchSafeAreaInset == true
        )
    }

    private fun configWebView(webViewContainer: QMUIWebViewContainer, webView: QMUIWebView) {

    }

    private fun updateTitle(title: String?) {
        viewModel.title.value = title
    }

    private fun onScrollWebContent(
        scrollX: Int,
        scrollY: Int,
        oldScrollX: Int,
        oldScrollY: Int
    ) {

    }

    @Suppress("DEPRECATION")
    private fun setZoomControlGone(webView: WebView) {
        webView.settings.displayZoomControls = false
        val classType: Class<*>
        val field: Field
        try {
            classType = WebView::class.java
            field = classType.getDeclaredField("mZoomButtonsController")
            field.isAccessible = true
            val zoomButtonsController = ZoomButtonsController(
                webView
            )
            zoomButtonsController.zoomControls.visibility = View.GONE
            try {
                field[webView] = zoomButtonsController
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        }
    }

    private fun sendProgressMessage(progressType: Int, newProgress: Int, duration: Int) {
        val msg = Message()
        msg.what = progressType
        msg.arg1 = newProgress
        msg.arg2 = duration
        progressHandler.sendMessage(msg)
    }


    private class ExplorerWebViewClient(
        private val fragment: FragmentWeb,
        needDispatchSafeAreaInset: Boolean
    ) :
        QMUIWebViewClient(needDispatchSafeAreaInset, true) {
        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            fragment.updateTitle(view.title)
            if (fragment.progressHandler.dstProgressIndex == 0) {
                fragment.sendProgressMessage(
                    PROGRESS_PROCESS,
                    30,
                    500
                )
            }
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            fragment.sendProgressMessage(
                PROGRESS_GONE,
                100,
                0
            )
            fragment.updateTitle(view.title)
        }
    }

    private class ExplorerWebViewChromeClient(private val fragment: FragmentWeb) :
        WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            // 修改进度条
            if (newProgress > fragment.progressHandler.dstProgressIndex) {
                fragment.sendProgressMessage(
                    PROGRESS_PROCESS,
                    newProgress,
                    100
                )
            }
        }

        override fun onReceivedTitle(view: WebView, title: String) {
            super.onReceivedTitle(view, title)
            fragment.updateTitle(view.title)
        }

        override fun onShowCustomView(view: View, callback: CustomViewCallback) {
            callback.onCustomViewHidden()
        }

        override fun onHideCustomView() {}

    }

    private class ProgressHandler(
        val progress_bar: ProgressBar
    ) : Handler(Looper.getMainLooper()) {
        private var isPageFinished = false
        var dstProgressIndex = 0
        private var duration = 0
        private var animator: ObjectAnimator? = null
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                PROGRESS_PROCESS -> {
                    isPageFinished = false
                    dstProgressIndex = msg.arg1
                    duration = msg.arg2
                    progress_bar.visibility = View.VISIBLE
                    if (animator != null && animator?.isRunning == true) {
                        animator?.cancel()
                    }
                    animator = ObjectAnimator.ofInt(progress_bar, "progress", dstProgressIndex)
                    animator?.duration = duration.toLong()
                    animator?.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            if (progress_bar.progress == 100) {
                                sendEmptyMessageDelayed(PROGRESS_GONE, 500)
                            }
                        }
                    })
                    animator?.start()
                }
                PROGRESS_GONE -> {
                    dstProgressIndex = 0
                    duration = 0
                    progress_bar.progress = 0
                    progress_bar.visibility = View.GONE
                    if (animator != null && animator?.isRunning == true) {
                        animator?.cancel()
                    }
                    animator = ObjectAnimator.ofInt(progress_bar, "progress", 0)
                    animator?.duration = 0
                    animator?.removeAllListeners()
                    isPageFinished = true
                }
                else -> {
                }
            }
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

}
