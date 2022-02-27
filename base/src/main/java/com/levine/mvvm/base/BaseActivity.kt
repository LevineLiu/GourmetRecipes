package com.levine.mvvm.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

/**
 * 作    者：liulewen
 * 版    本：1.0
 * 创建日期：2022/2/21 18:21
 * 描    述：Activity基类
 * 修订历史：
 */
abstract class BaseActivity<VDB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(){
    protected lateinit var dataBinding: VDB
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        initView()
        initLiveDataObserve()
    }

    override fun onDestroy() {
        super.onDestroy()
        dataBinding?.unbind()
    }

    private fun initView() {
        dataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewModel = createViewModel().apply {
            if (viewModel == null) {
                val modelClass: Class<VM>
                val superClassType = javaClass.genericSuperclass
                if (superClassType is ParameterizedType) {
                    //有传泛型参数
                    modelClass = superClassType.actualTypeArguments[1] as Class<VM>
                    viewModel = createActivityViewModel(modelClass)
                } else {
                    //没传泛型参数
                    viewModel = createActivityViewModel(BaseViewModel::class.java) as VM
                }
            }
        }
        if (viewModel != null) {
            dataBinding.setVariable(getViewModelId(), viewModel)
        }

        dataBinding.setLifecycleOwner(this)
    }

    /**
     * LiveData的监听
     */
    protected fun initLiveDataObserve() {

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

    protected fun <M : ViewModel> createActivityViewModel(viewModelClass: Class<M>, factory: ViewModelProvider.Factory): M {
        return ViewModelProvider(this, factory).get(viewModelClass)
    }

    protected fun <M : ViewModel> createActivityViewModel(viewModelClass: Class<M>): M {
        return ViewModelProvider(this).get(viewModelClass)
    }
}