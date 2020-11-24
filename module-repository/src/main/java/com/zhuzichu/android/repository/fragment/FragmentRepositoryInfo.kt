package com.zhuzichu.android.repository.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.ZoomButtonsController
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.activityViewModels
import com.hiwitech.android.libs.internal.MainHandler
import com.hiwitech.android.libs.tool.byteCountToDisplaySizeTwo
import com.hiwitech.android.libs.tool.toLong
import com.qmuiteam.qmui.nestedScroll.QMUIContinuousNestedTopAreaBehavior
import com.qmuiteam.qmui.nestedScroll.QMUIContinuousNestedTopDelegateLayout
import com.qmuiteam.qmui.widget.pullRefreshLayout.QMUIPullRefreshLayout
import com.qmuiteam.qmui.widget.webview.QMUIWebViewClient
import com.zhuzichu.android.repository.BR
import com.zhuzichu.android.repository.R
import com.zhuzichu.android.repository.databinding.FragmentRepositoryInfoBinding
import com.zhuzichu.android.repository.viewmodel.ShareViewModel
import com.zhuzichu.android.repository.viewmodel.ViewModelRepositoryInfo
import com.zhuzichu.android.shared.base.FragmentBase
import com.zhuzichu.android.shared.entity.arg.ArgRepository
import com.zhuzichu.android.shared.entity.arg.ArgWeb
import com.zhuzichu.android.shared.html.showLoading
import com.zhuzichu.android.shared.html.showMarkdown
import com.zhuzichu.android.shared.route.RoutePath
import com.zhuzichu.android.shared.skin.SkinManager
import com.zhuzichu.android.shared.view.XWebView
import kotlinx.android.synthetic.main.fragment_repository_info.*
import java.lang.reflect.Field

/**
 * desc
 * author: 朱子楚
 * time: 2020/10/20 3:01 PM
 * since: v 1.0.0
 */
class FragmentRepositoryInfo :
    FragmentBase<FragmentRepositoryInfoBinding, ViewModelRepositoryInfo, ArgRepository>() {

    companion object {
        private const val PROGRESS_PROCESS = 0
        private const val PROGRESS_GONE = 1
    }

    private val share by activityViewModels<ShareViewModel>()

    private lateinit var webView: XWebView

    private lateinit var topLayout: QMUIContinuousNestedTopDelegateLayout

    private lateinit var progressHandler: ProgressHandler

    private lateinit var headerView: View

    private lateinit var fullName: TextView

    private lateinit var description: TextView

    private lateinit var info: TextView

    private lateinit var issues: TextView

    private lateinit var stargazers: TextView

    private lateinit var forks: TextView

    private lateinit var watchers: TextView

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_repository_info

    override fun initView() {
        super.initView()
        initHeader()
        initWebView()
        updateTitle(arg.name)
    }

    override fun initLazyData() {
        super.initLazyData()
        viewModel.loadMarkdown()
    }

    override fun initListener() {
        super.initListener()
        pull_to_refresh.setOnPullListener(object : QMUIPullRefreshLayout.OnPullListener {
            override fun onMoveTarget(offset: Int) {

            }

            override fun onMoveRefreshView(offset: Int) {
            }

            override fun onRefresh() {
                MainHandler.postDelayed {
                    pull_to_refresh.finishRefresh()
                }
            }
        })
    }

    override fun initViewObservable() {
        super.initViewObservable()
        share.repository.observe(viewLifecycleOwner) {
            fullName.text = it.fullName
            description.text = it.description
            info.text = getString(
                R.string.repository_language_size,
                it.language ?: getString(R.string.unknown),
                byteCountToDisplaySizeTwo(toLong(it.size))
            )
            issues.text = it.openIssuesCount.toString()
            stargazers.text = it.stargazersCount.toString()
            forks.text = it.forksCount.toString()
            watchers.text = it.watchers.toString()
        }

        viewModel.data.observe(viewLifecycleOwner) {
            webView.showMarkdown(it)
        }

        SkinManager.onSkinChangeListener.observe(viewLifecycleOwner) {
            viewModel.data.value?.let { markdown ->
                webView.showMarkdown(markdown)
            }
        }
    }

    private fun initHeader() {
        headerView = layoutInflater.inflate(R.layout.header_repository_info, coordinator, false)
        fullName = headerView.findViewById(R.id.full_name)
        description = headerView.findViewById(R.id.description)
        info = headerView.findViewById(R.id.info)
        issues = headerView.findViewById(R.id.issues)
        stargazers = headerView.findViewById(R.id.stargazers)
        forks = headerView.findViewById(R.id.forks)
        watchers = headerView.findViewById(R.id.watchers)
    }

    private fun initWebView() {
        topLayout = QMUIContinuousNestedTopDelegateLayout(requireContext())

        topLayout.headerView = headerView

        webView = XWebView(requireContext())
        webView.setBackgroundColor(0)
        topLayout.delegateView = webView

        val topLp = CoordinatorLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        topLp.behavior = QMUIContinuousNestedTopAreaBehavior(context)
        coordinator.setTopAreaView(topLayout, topLp)
        coordinator.setDraggableScrollBarEnabled(true)

        progressHandler = ProgressHandler(progress_bar)

        webView.webChromeClient = getWebViewChromeClient()
        webView.webViewClient = getWebViewClient()
        webView.requestFocus(View.FOCUS_DOWN)
        setZoomControlGone(webView)
        webView.showLoading()
    }

    private fun getWebViewChromeClient(): WebChromeClient {
        return ExplorerWebViewChromeClient(this)
    }

    private fun getWebViewClient(): QMUIWebViewClient {
        return ExplorerWebViewClient(
            this,
            false
        )
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

    fun updateTitle(title: String?) {
        share.title.value = title
    }

    private class ExplorerWebViewClient(
        private val fragment: FragmentRepositoryInfo,
        needDispatchSafeAreaInset: Boolean
    ) :
        QMUIWebViewClient(needDispatchSafeAreaInset, true) {
        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
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
        }

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            request?.url?.let {
                fragment.navigate(RoutePath.Web.ACTIVITY_WEB_MAIN, ArgWeb(it.toString(), "blank"))
                return true
            }
            return false
        }
    }

    private class ExplorerWebViewChromeClient(private val fragment: FragmentRepositoryInfo) :
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

}