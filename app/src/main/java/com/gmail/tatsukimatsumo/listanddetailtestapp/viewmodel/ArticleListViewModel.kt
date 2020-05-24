package com.gmail.tatsukimatsumo.listanddetailtestapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.tatsukimatsumo.listanddetailtestapp.model.Article

/**
 * 記事一覧のViewModel
 *
 * 画面に表示するデータを保持
 * データに変更があれば、紐付いているViewへも反映される
 * Viewへの参照は持たない
 * Modelの操作もしない
 */
class ArticleListViewModel : ViewModel() {
    val articleList: MutableLiveData<List<Article>> by lazy {
        MutableLiveData<List<Article>>()
    }

    /** タップイベントをActivityではなくPresenterへ渡すためのプロパティ(手抜き)
     *  記事IDを渡す
     *  これを用意しなくてもpresenterの参照を持っておけば直接Presenterの処理を呼ぶだけにできる
     */
    val onTapTitleEvent = MutableLiveData<String>()

    /**
     * タイトルをタップしたときの処理(xmlで紐付け)
     */
    fun onTapTitle(articleId: String) {
        onTapTitleEvent.value = articleId
    }
}