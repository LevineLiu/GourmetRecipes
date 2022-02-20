package com.levine.mvvm.base

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * 作    者：liulewen
 * 版    本：1.0
 * 创建日期：2022/2/16 16:33
 * 描    述：Fragment基类
 * 修订历史：
 */
abstract class BaseFragment<VDB : ViewDataBinding, VM : BaseViewModel<R>, R : BaseRepository> : Fragment(){

}