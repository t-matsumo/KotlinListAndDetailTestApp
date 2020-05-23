package com.gmail.tatsukimatsumo.listanddetailtestapp.model.repository.article

import com.gmail.tatsukimatsumo.listanddetailtestapp.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticleLocalRepository : ArticleRepository {
    /** 擬似的なローカルデータ */
    private val articles = List(100) { Article(it.toString(), "title$it", "contents$it") }

    override fun getArticles(from: Int, count: Int, callback: (List<Article>) -> Unit) {
        /** データ取得は非同期で行う(下記の書き方は手抜き) */
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.Default) {
                articles.subList(from.coerceAtLeast(0), (from + count).coerceAtMost(articles.size))
            }.let {
                callback(it)
            }
        }
    }
    override fun findBy(id: String, callback: (Article?) -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.Default) {
                articles.find { it.id == id }
            }.let {
                callback(it)
            }
        }
    }
}