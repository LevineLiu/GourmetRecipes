package com.levine.gourmetrecipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.levine.mvvm.base.BaseRepository
import com.levine.mvvm.base.BaseViewModel
import com.levine.mvvm.base.IBaseRepository
import java.lang.Exception

/**
 * 作    者：liulewen
 * 版    本：1.0
 * 创建日期：2022/2/25 17:02
 * 描    述：ViewModel工厂类，每增加一个ViewModel都需要在这里修改
 * 修订历史：
 */
class ViewModelFactory(repository: IBaseRepository) : ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        try {
            if (modelClass.isAssignableFrom(BaseViewModel::class.java)) {
                return BaseViewModel(BaseRepository()) as T
            }
        } catch(e :Exception){
            e.printStackTrace()
        }
        return super.create(modelClass)
    }
}