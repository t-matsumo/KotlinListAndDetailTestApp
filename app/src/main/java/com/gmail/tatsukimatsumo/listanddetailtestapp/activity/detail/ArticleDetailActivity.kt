package com.gmail.tatsukimatsumo.listanddetailtestapp.activity.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.gmail.tatsukimatsumo.listanddetailtestapp.R
import com.gmail.tatsukimatsumo.listanddetailtestapp.databinding.ActivityArticleDetailBinding
import com.gmail.tatsukimatsumo.listanddetailtestapp.viewmodel.ArticleDetailViewModel
import androidx.activity.viewModels
import com.gmail.tatsukimatsumo.listanddetailtestapp.presenter.ArticleDetailPresenter

/**
 * 記事詳細の画面
 *
 * ViewとViewModelを紐付けるだけ
 * ViewやModelへの参照は持たない
 */
class ArticleDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_KEY_ARTICLE_ID = "article_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewModelをViewに紐付ける
        val binding: ActivityArticleDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_article_detail)
        binding.lifecycleOwner = this // LiveDataのスコープをこのアクティビティのスコープに指定
        val articleViewModel: ArticleDetailViewModel by viewModels()
        binding.articleViewModel = articleViewModel

        val articleId = intent.getStringExtra(EXTRA_KEY_ARTICLE_ID)!!
        // presenterを生成
        val presenter = ArticleDetailPresenter(articleId, articleViewModel)
        presenter.onCreate()
    }
}
