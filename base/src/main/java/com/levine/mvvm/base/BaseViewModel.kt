package com.levine.mvvm.base

import androidx.lifecycle.ViewModel
import com.levine.mvvm.event.SingleLiveEvent

class BaseViewModel<R : BaseRepository>(var repository: R) : ViewModel() {
    var uiLiveData: UIChangeLiveData? = null
        get() {
            if (field == null) {
                field = UIChangeLiveData()
            }
            return field
        }

    fun showDialog(content: String = "加载中...") {
        uiLiveData?.showDialogEvent?.postValue(content)
    }

    fun dismissDialog() {
        uiLiveData?.dismissDialogEvent?.call()
    }

    /**
     * 通用界面变化事件
     */
    class UIChangeLiveData : SingleLiveEvent<Any>() {
        var showDialogEvent: SingleLiveEvent<String>? = null
            get() = createLiveData(field)
        var dismissDialogEvent: SingleLiveEvent<Void>? = null
            get() = createLiveData(field)

        private fun <T> createLiveData(event: SingleLiveEvent<T>?): SingleLiveEvent<T> {
            var resultEvent: SingleLiveEvent<T>? = event;
            if (resultEvent == null) {
                resultEvent = SingleLiveEvent()
            }
            return resultEvent
        }
    }
}