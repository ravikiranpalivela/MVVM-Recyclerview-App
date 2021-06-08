package com.ravikiran.example.newsapp.presentation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ravikiran.example.newsapp.data.model.Article
import com.ravikiran.example.newsapp.databinding.ItemArticleBinding

class NewsPaginationAdapter : RecyclerView.Adapter<NewsPaginationAdapter.NewsViewHolder>() {

    var newsList = mutableListOf<Article>()
    lateinit var context: Context

    fun setNewsList(newsList: List<Article>?, applicationContext: Context) {
        this.newsList = newsList!!.toMutableList()
        this.context = applicationContext
        notifyDataSetChanged()
    }


    private val callback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

//    val newsList = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    private var onItemClickCallback: ((Article) -> Unit?)? = null

    fun setOnClickCallback(callback: (Article) -> Unit?) {
        onItemClickCallback = callback
    }

    inner class NewsViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            Log.i("MyNews", "Loaded in VH ${article.title}")
            binding.tvTitle.text = article.title
            binding.tvDescription.text = article.description
            binding.tvPublishedAt.text = article.publishedAt
            binding.tvSource.text = article.source?.name

            Glide.with(binding.ivArticleImage.context).load(article.urlToImage)
                .into(binding.ivArticleImage)

            binding.root.setOnClickListener {
                onItemClickCallback?.let { callback ->
                    callback(article)
                }
            }
        }

    }

//    fun addNews(data: List<Article>) {
//        for (d in data) {
//            newsList!!.add(d)
//        }
//        notifyDataSetChanged()
//    }
}
