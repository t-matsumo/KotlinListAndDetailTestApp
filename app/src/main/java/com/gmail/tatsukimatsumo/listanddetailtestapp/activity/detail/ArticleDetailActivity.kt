package com.gmail.tatsukimatsumo.listanddetailtestapp.activity.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gmail.tatsukimatsumo.listanddetailtestapp.R
import com.gmail.tatsukimatsumo.listanddetailtestapp.model.Article
import com.gmail.tatsukimatsumo.listanddetailtestapp.model.repository.article.ArticleLocalRepository
import kotlinx.android.synthetic.main.activity_article_detail.*

class ArticleDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        val article = intent.getParcelableExtra<Article>(Article::class.java.canonicalName)!!
        ArticleLocalRepository().findBy(article.id, this::onSuccess)
    }

    private fun onSuccess(article: Article?) {
        // articleの加工が必要な場合はここかな？
        articleTitle.text = article?.title ?: "値なし"
        articleContents.text = article?.contents ?: "値なし"
    }
}
