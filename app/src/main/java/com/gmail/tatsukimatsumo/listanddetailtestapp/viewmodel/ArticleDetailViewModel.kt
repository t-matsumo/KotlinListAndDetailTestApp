package com.gmail.tatsukimatsumo.listanddetailtestapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 記事詳細のViewModel
 *
 * 画面に表示するデータを保持
 * データに変更があれば、紐付いているViewへも反映される
 * Viewへの参照は持たない
 * Modelの操作もしない
 */
class ArticleDetailViewModel : ViewModel() {
    val title: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val contents: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}