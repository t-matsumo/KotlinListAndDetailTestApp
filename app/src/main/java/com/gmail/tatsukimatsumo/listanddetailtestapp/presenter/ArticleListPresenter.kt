package com.gmail.tatsukimatsumo.listanddetailtestapp.presenter

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.gmail.tatsukimatsumo.listanddetailtestapp.model.Article
import com.gmail.tatsukimatsumo.listanddetailtestapp.model.repository.article.ArticleLocalRepository
import com.gmail.tatsukimatsumo.listanddetailtestapp.viewmodel.ArticleListViewModel

/**
 * 記事一覧のpresenter
 * MVVMのときにViewModelに記載されていたModelに対する操作を行う
 * ViewModel経由でViewを更新する
 * contextやAndroidのAPIに強く依存したくない（インターフェースなら許す。個人の意見です）ので、
 * AndroidのAPIを使いたいときはviewのインターフェース経由で行う
 */
class ArticleListPresenter(val articleListViewModel: ArticleListViewModel, private val view: ArticleListContract) {
    interface ArticleListContract : LifecycleOwner {
        // contextに依存したくないため作成
        fun startActivityToArticleDetail(articleId: String)
    }

    /** データを取得する最初のindex(追加読み込みは実装しない) */
    private var from = 0
    /** データを取得数 */
    private var count = 20

    fun onCreate() {
        // タップイベントに対するハンドラを設定
        articleListViewModel.onTapTitleEvent.observe(view, Observer(this::onTapTitle))

        // Presenterが値を取りにいく
        ArticleLocalRepository().getArticles(from, count, this::onSuccessGetArticles)
    }

    private fun onSuccessGetArticles(articleList: List<Article>) {
        // articleを加工するなら、ここかな？
        from += count

        val values = articleListViewModel.articleList.value
        articleListViewModel.articleList.value = if (values == null) articleList else values + articleList
    }

    private fun onTapTitle(articleId: String) {
        // contextが必要な処理はViewに委譲したい
        // 必要なら、委譲する前に何かしらの処理を書く
        view.startActivityToArticleDetail(articleId)
    }
}