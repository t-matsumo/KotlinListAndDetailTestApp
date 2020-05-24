package com.gmail.tatsukimatsumo.listanddetailtestapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.tatsukimatsumo.listanddetailtestapp.model.Article
import com.gmail.tatsukimatsumo.listanddetailtestapp.model.repository.article.ArticleLocalRepository

/**
 * 記事詳細のViewModel
 *
 * 画面に表示するデータを保持
 * データに変更があれば、紐付いているViewへも反映される
 * Viewへの参照は持たない
 * Modelの操作もここで行う
 */
class ArticleDetailViewModel : ViewModel() {
    val title: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val contents: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun setArticleId(value: String) {
        // ViewModelが値を取りにいく
        ArticleLocalRepository().findBy(value, this::onSuccess)
    }

    private fun onSuccess(article: Article?) {
        // articleの加工が必要な場合はここかな？

        // 値を更新すると、変更がViewへ通知されて値が反映される
        title.value = article?.title ?: "値なし"
        contents.value = article?.contents ?: "値なし"
    }
}