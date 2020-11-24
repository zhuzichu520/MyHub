package com.zhuzichu.android.me.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.hiwitech.android.mvvm.base.ArgDefault
import com.qmuiteam.qmui.skin.QMUISkinManager
import com.qmuiteam.qmui.widget.dialog.QMUIDialog
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction
import com.qmuiteam.qmui.widget.pullLayout.QMUIPullLayout
import com.zhuzichu.android.me.R
import com.zhuzichu.android.me.BR
import com.zhuzichu.android.me.databinding.FragmentTraceBinding
import com.zhuzichu.android.me.viewmodel.ViewModelTrace
import com.zhuzichu.android.shared.base.FragmentBase
import com.zhuzichu.android.shared.route.RoutePath
import kotlinx.android.synthetic.main.fragment_trace.*

@Route(path = RoutePath.Me.FRAGMENT_ME_TRACE)
class FragmentTrace : FragmentBase<FragmentTraceBinding, ViewModelTrace, ArgDefault>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_trace

    override fun initView() {
        super.initView()
        initTopBar()
    }

    private fun initTopBar() {
        topbar.addLeftImageButton(R.drawable.ic_topbar_back, R.id.topbar_left_back_button)
            .setOnClickListener {
                back()
            }

        topbar.addRightImageButton(R.drawable.ic_delete, R.id.topbar_right_delete_button)
            .setOnClickListener {
                showDeleteDialog()
            }

    }

    private fun showDeleteDialog() {
        QMUIDialog.MessageDialogBuilder(requireContext())
            .setTitle(R.string.tips)
            .setMessage(R.string.delete_all_trace_tips)
            .setSkinManager(QMUISkinManager.defaultInstance(context))
            .addAction(R.string.cancel) { dialog, _ -> dialog.dismiss() }
            .addAction(0, R.string.ok, QMUIDialogAction.ACTION_PROP_NEGATIVE) { dialog, _ ->
                viewModel.deleteAllTrace()
                dialog.dismiss()
            }.create(R.style.MyTheme_QMUI_Dialog).show()
    }

    override fun initListener() {
        super.initListener()
        content.setActionListener { action ->
            when (action.pullEdge) {
                QMUIPullLayout.PULL_EDGE_TOP -> {
                    viewModel.page = 1
                    viewModel.loadData {
                        content.finishActionRun(action)
                    }
                }
                QMUIPullLayout.PULL_EDGE_BOTTOM -> {
                    viewModel.loadData {
                        content.finishActionRun(action)
                    }
                }
            }
        }
    }

    override fun initLazyData() {
        super.initLazyData()
        viewModel.loadData()
    }

}
