package com.gmail.tatsukimatsumo.listanddetailtestapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.tatsukimatsumo.listanddetailtestapp.model.Article
import com.gmail.tatsukimatsumo.listanddetailtestapp.model.repository.article.ArticleLocalRepository

/**
 * 記事一覧のViewModel
 *
 * 画面に表示するデータを保持
 * データに変更があれば、紐付いているViewへも反映される
 * Viewへの参照は持たない
 * Modelの操作もここで行う
 */
class ArticleListViewModel : ViewModel() {
    val articleList: MutableLiveData<List<Article>> by lazy {
        MutableLiveData<List<Article>>()
    }

    /** タップイベントをActivityへ渡すためのプロパティ(手抜き)
     *  記事IDを渡す
     */
    val onTapTitleEvent = MutableLiveData<String>()

    /** データを取得する最初のindex(追加読み込みは実装しない) */
    private var from = 0
    /** データを取得数 */
    private var count = 20

    init {
        // ViewModelが値を取りにいく
        ArticleLocalRepository().getArticles(from, count, this::onSuccessGetArticles)
    }

    private fun onSuccessGetArticles(articleList: List<Article>) {
        // articleを加工するなら、ここかな？
        from += count
        val values = this.articleList.value
        this.articleList.value = if (values == null) articleList else values + articleList
    }

    /**
     * タイトルをタップしたときの処理(xmlで紐付け)
     */
    fun onTapTitle(articleId: String) {
        onTapTitleEvent.value = articleId
    }
}