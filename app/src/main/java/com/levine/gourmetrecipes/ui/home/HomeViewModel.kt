package com.levine.gourmetrecipes.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.levine.mvvm.base.BaseViewModel
import com.levine.mvvm.base.IBaseRepository

class HomeViewModel(repository: IBaseRepository) : BaseViewModel(repository) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}

