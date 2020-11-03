package com.zhuzichu.android.viewer.fragment

import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ZoomButtonsController
import com.alibaba.android.arouter.facade.annotation.Route
import com.qmuiteam.qmui.widget.webview.QMUIWebViewClient
import com.zhuzichu.android.shared.base.FragmentBase
import com.zhuzichu.android.shared.entity.arg.ArgViewer
import com.zhuzichu.android.shared.html.showCode
import com.zhuzichu.android.shared.html.showLoading
import com.zhuzichu.android.shared.route.RoutePath
import com.zhuzichu.android.shared.skin.SkinManager
import com.zhuzichu.android.shared.view.XWebView
import com.zhuzichu.android.viewer.BR
import com.zhuzichu.android.viewer.R
import com.zhuzichu.android.viewer.databinding.FragmentViewerBinding
import com.zhuzichu.android.viewer.viewmodel.ViewModelViewer
import kotlinx.android.synthetic.main.fragment_viewer.*
import java.lang.reflect.Field

@Route(path = RoutePath.Viewer.FRAGMENT_VIEWER_MAIN)
class FragmentViewer : FragmentBase<FragmentViewerBinding, ViewModelViewer, ArgViewer>() {

    companion object {
        val SUPPORTED_CODE_FILE_EXTENSIONS = arrayOf(
            "bsh", "c", "cc", "cpp", "cs", "csh", "cyc", "cv", "htm", "html", "java",
            "js", "m", "mxml", "perl", "pl", "pm", "py", "rb", "sh", "xhtml", "xml",
            "xsl"
        )
    }

    private lateinit var webView: XWebView

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_viewer

    override fun initView() {
        super.initView()
        initTopBar()
        initWebView()
    }

    override fun initLazyData() {
        super.initLazyData()
        viewModel.loadData()
    }

    override fun initViewObservable() {
        super.initViewObservable()

        viewModel.data.observe(viewLifecycleOwner) {
            webView.showCode(it, getExtension())
        }

        SkinManager.onSkinChangeListener.observe(viewLifecycleOwner) {
            viewModel.data.value?.let { code ->
                webView.showCode(code, getExtension())
            }
        }

    }

    private fun initTopBar() {
        topbar.addLeftImageButton(R.drawable.ic_topbar_back, R.id.topbar_left_back_button)
            .setOnClickListener {
                back()
            }
        updateTitle(arg.title)
    }

    private fun initWebView() {
        webView = XWebView(requireContext())
        webView.setBackgroundColor(0)
        webview_container.addWebView(webView, false)
        webView.webChromeClient = getWebViewChromeClient()
        webView.webViewClient = getWebViewClient()
        webView.requestFocus(View.FOCUS_DOWN)
        setZoomControlGone(webView)
        webView.showLoading()
    }

    private fun getWebViewChromeClient(): WebChromeClient {
        return ExplorerWebViewChromeClient()
    }

    private fun getWebViewClient(): QMUIWebViewClient {
        return ExplorerWebViewClient(false)
    }


    private fun updateTitle(title: String?) {
        viewModel.title.value = title
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


    private class ExplorerWebViewClient(needDispatchSafeAreaInset: Boolean) :
        QMUIWebViewClient(needDispatchSafeAreaInset, true)

    private class ExplorerWebViewChromeClient : WebChromeClient() {

        override fun onShowCustomView(view: View, callback: CustomViewCallback) {
            callback.onCustomViewHidden()
        }

        override fun onHideCustomView() {}
    }

    private fun getExtension(): String {
        return try {
            val fileName = arg.title.toString()
            val extension = fileName.substring(fileName.lastIndexOf(".") + 1)
            if (SUPPORTED_CODE_FILE_EXTENSIONS.contains(extension)) extension else ""
        } catch (e: Exception) {
            ""
        }
    }

}
