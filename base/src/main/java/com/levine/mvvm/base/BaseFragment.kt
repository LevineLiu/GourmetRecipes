package com.levine.mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import java.lang.reflect.ParameterizedType

/**
 * 作    者：liulewen
 * 版    本：1.0
 * 创建日期：2022/2/16 16:33
 * 描    述：Fragment基类
 * 修订历史：
 */
abstract class BaseFragment<VDB : ViewDataBinding, VM : BaseViewModel> : Fragment(){
    protected lateinit var dataBinding: VDB
    protected lateinit var viewModel: VM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, isAttachToParent())
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        dataBinding?.unbind()
    }

    private fun initView() {
        viewModel = createViewModel()
        if (viewModel == null) {
            val modelClass: Class<VM>
            val superClassType = javaClass.genericSuperclass
            if (superClassType is ParameterizedType) {
                //有传泛型参数
                modelClass = superClassType.actualTypeArguments[1] as Class<VM>
                viewModel = createFragmentViewModel(modelClass)
            } else {
                //没传泛型参数
                viewModel = createFragmentViewModel(BaseViewModel::class.java) as VM
            }
        }

        if (viewModel != null) {
            dataBinding.setVariable(getViewModelId(), viewModel)
        }

        dataBinding.setLifecycleOwner(this)

    }

    protected fun <M : ViewModel> createFragmentViewModel(viewModelClass: Class<M>, factory: ViewModelProvider.Factory): M {
        return ViewModelProvider(this, factory).get(viewModelClass)
    }

    private fun <M : ViewModel> createFragmentViewModel(viewModelClass: Class<M>): M {
        return ViewModelProvider(this).get(viewModelClass)
    }

    open fun isAttachToParent(): Boolean {
        return true
    }

    /**
     * 获取根布局layout id
     * @return 根布局layout id
     */
    abstract fun getLayoutId(): Int
    /**
     * 初始化ViewModel
     * @return ViewModel 如果返回null，则使用默认创建的无参ViewModel
     */
    abstract fun createViewModel(): VM
    /**
     * 获取ViewModel id，用于DataBinding
     * @return ViewModel id
     */
    abstract fun getViewModelId(): Int
}