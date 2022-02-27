package com.levine.gourmetrecipes.ui.home

import com.levine.gourmetrecipes.BR
import com.levine.gourmetrecipes.R
import com.levine.gourmetrecipes.ViewModelFactory
import com.levine.gourmetrecipes.databinding.FragmentHomeBinding
import com.levine.mvvm.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun createViewModel(): HomeViewModel {
        return ViewModelFactory(HomeRepository()).create(HomeViewModel::class.java)
    }

    override fun getViewModelId(): Int {
        return BR.viewModel
    }

    override fun isAttachToParent(): Boolean {
        return false
    }
}