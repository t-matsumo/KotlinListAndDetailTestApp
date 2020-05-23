package com.gmail.tatsukimatsumo.listanddetailtestapp.model.repository.article

import com.gmail.tatsukimatsumo.listanddetailtestapp.model.Article

interface ArticleRepository {
    fun getArticles(from: Int, count: Int, callback: (List<Article>) -> Unit)
    fun findBy(id: String, callback: (Article?) -> Unit)
}