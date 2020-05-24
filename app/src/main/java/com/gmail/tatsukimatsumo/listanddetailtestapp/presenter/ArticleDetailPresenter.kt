package com.gmail.tatsukimatsumo.listanddetailtestapp.presenter

import com.gmail.tatsukimatsumo.listanddetailtestapp.model.Article
import com.gmail.tatsukimatsumo.listanddetailtestapp.model.repository.article.ArticleLocalRepository
import com.gmail.tatsukimatsumo.listanddetailtestapp.viewmodel.ArticleDetailViewModel

/**
 * Presenter
 * MVVMのときにViewModelに記載されていたModelに対する操作を行う
 * ViewModel経由でViewを更新する
 */
class ArticleDetailPresenter(private val articleId: String, private val articleDetailViewModel: ArticleDetailViewModel) {
    fun onCreate() {
        // Presenterが値を取りにいく
        ArticleLocalRepository().findBy(articleId, this::onSuccess)
    }

    private fun onSuccess(article: Article?) {
        // articleの加工が必要な場合はここかな？

        // ViewModel経由でViewを更新する
        articleDetailViewModel.title.value = article?.title ?: "値なし"
        articleDetailViewModel.contents.value = article?.contents ?: "値なし"
    }
}