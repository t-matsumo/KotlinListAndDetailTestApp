package com.gmail.tatsukimatsumo.listanddetailtestapp.activity.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gmail.tatsukimatsumo.listanddetailtestapp.databinding.ArticleListItemViewBinding
import com.gmail.tatsukimatsumo.listanddetailtestapp.model.Article
import com.gmail.tatsukimatsumo.listanddetailtestapp.viewmodel.ArticleListViewModel

/**
 * @param articleListViewModel 記事一覧画面のViewModel
 */
class ArticleAdapter(private val articleListViewModel: ArticleListViewModel) :
    RecyclerView.Adapter<ArticleAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: ArticleListItemViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ArticleListItemViewBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val article = articleListViewModel.articleList.value!![position]

        // viewとViewData,modelを紐付ける
        holder.binding.article = article
        holder.binding.articleListViewModel = articleListViewModel
    }

    override fun getItemCount() = articleListViewModel.articleList.value?.size ?: 0

    companion object {
        @JvmStatic
        @BindingAdapter("items")
        // ViewModelとRecyclerViewをxmlで紐付けるためのBindingAdapter
        fun RecyclerView.bindItems(items: List<Article>?) {
            if (items == null) {
                return
            }

            val adapter = adapter as ArticleAdapter
            adapter.notifyDataSetChanged()
        }
    }
}
