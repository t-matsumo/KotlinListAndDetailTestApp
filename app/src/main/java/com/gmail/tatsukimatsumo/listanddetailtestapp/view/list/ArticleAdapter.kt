package com.gmail.tatsukimatsumo.listanddetailtestapp.view.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.tatsukimatsumo.listanddetailtestapp.R
import com.gmail.tatsukimatsumo.listanddetailtestapp.model.Article
import kotlinx.android.synthetic.main.article_list_item_view.view.*

/**
 * @param articles 一覧表示する記事データの配列
 * @param articleListListener タップを処理するリスナ
 */
class ArticleAdapter(private val articles: List<Article>, private var articleListListener: ArticleListListener) :
    RecyclerView.Adapter<ArticleAdapter.ItemViewHolder>() {

    class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_list_item_view, parent, false)
        return ItemViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.view.articleTitle.text = articles[position].title
        holder.view.articleTitle.setOnClickListener { articleListListener.onTapTitle(position) }
    }

    override fun getItemCount() = articles.size

    /**
     * タップを処理するリスナのインターフェース
     */
    interface ArticleListListener {
        /**
         * 記事タイトルをタップしたときに呼び出す
         * @param position 何番目の要素のタイトルがタップされたのか
         */
        fun onTapTitle(position: Int)
    }
}
