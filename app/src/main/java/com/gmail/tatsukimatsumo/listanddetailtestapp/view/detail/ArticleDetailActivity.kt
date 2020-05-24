package com.gmail.tatsukimatsumo.listanddetailtestapp.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gmail.tatsukimatsumo.listanddetailtestapp.R
import com.gmail.tatsukimatsumo.listanddetailtestapp.model.Article
import com.gmail.tatsukimatsumo.listanddetailtestapp.model.repository.article.ArticleLocalRepository
import kotlinx.android.synthetic.main.activity_article_detail.*

class ArticleDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_KEY_ARTICLE_ID = "article_id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        val articleId = intent.getStringExtra(EXTRA_KEY_ARTICLE_ID)!!
        ArticleLocalRepository().findBy(articleId, this::onSuccess)
    }

    private fun onSuccess(article: Article?) {
        // articleの加工が必要な場合はここかな？
        articleTitle.text = article?.title ?: "値なし"
        articleContents.text = article?.contents ?: "値なし"
    }
}
