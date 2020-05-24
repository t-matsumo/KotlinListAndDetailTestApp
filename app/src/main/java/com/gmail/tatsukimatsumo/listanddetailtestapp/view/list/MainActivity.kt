package com.gmail.tatsukimatsumo.listanddetailtestapp.view.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.tatsukimatsumo.listanddetailtestapp.R
import com.gmail.tatsukimatsumo.listanddetailtestapp.view.detail.ArticleDetailActivity
import com.gmail.tatsukimatsumo.listanddetailtestapp.databinding.ActivityMainBinding
import com.gmail.tatsukimatsumo.listanddetailtestapp.viewmodel.ArticleListViewModel
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 記事のタイトルを一覧表示する
 *
 * ViewとViewModelを紐付けるだけ
 * Modelへの参照は持たない
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewModelをViewに紐付ける
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this // LiveDataのスコープをこのアクティビティのスコープに指定
        val articleListViewModel: ArticleListViewModel by viewModels()
        binding.articleListViewModel = articleListViewModel

        // recyclerViewをViewModelに紐付ける
        val viewManager = LinearLayoutManager(this)
        val viewAdapter = ArticleAdapter(articleListViewModel)
        this.articleList.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        articleListViewModel.onTapTitleEvent.observe(this, Observer { articleId ->
            // 画面遷移はActivityでやる
            val intent = Intent(this, ArticleDetailActivity::class.java)
            intent.putExtra(ArticleDetailActivity.EXTRA_KEY_ARTICLE_ID, articleId)
            startActivity(intent)
        })
    }
}
