package com.gmail.tatsukimatsumo.listanddetailtestapp.view.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.tatsukimatsumo.listanddetailtestapp.R
import com.gmail.tatsukimatsumo.listanddetailtestapp.view.detail.ArticleDetailActivity
import com.gmail.tatsukimatsumo.listanddetailtestapp.model.Article
import com.gmail.tatsukimatsumo.listanddetailtestapp.model.repository.article.ArticleLocalRepository
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 記事のタイトルを一覧表示する
 */
class MainActivity : AppCompatActivity(), ArticleAdapter.ArticleListListener {
    private val articles = mutableListOf<Article>()
    /** データを取得する最初のindex(追加読み込みは実装しない) */
    private var from = 0
    /** データを取得数 */
    private var count = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ArticleLocalRepository().getArticles(from, count, this::onSuccessGetArticles)
    }

    private fun onSuccessGetArticles(articles: List<Article>) {
        this.articles += articles
        from += count

        // articleを加工するなら、ここかな？

        val viewManager = LinearLayoutManager(this)
        val viewAdapter = ArticleAdapter(articles, this)

        articleList.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    /**
     * ArticleAdapter.ArticleListListenerの実装
     * タイトルをタップしたときの処理
     */
    override fun onTapTitle(position: Int) {
        val intent = Intent(this, ArticleDetailActivity::class.java)
        intent.putExtra(ArticleDetailActivity.EXTRA_KEY_ARTICLE_ID, articles[position].id)
        startActivity(intent)
    }
}
