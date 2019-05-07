package com.example.findbook.view.favorite

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.findbook.R
import com.example.findbook.extentions.inflate
import com.example.findbook.model.IModel
import com.example.findbook.utils.NoteDiffCallBack
import com.example.findbook.view.BookUIModel
import kotlinx.android.synthetic.main.item_book.view.*
import java.util.concurrent.Executors

class FavoriteAdapter(private val mListener: FavoriteAdapterListener?) :
    ListAdapter<IModel, FavoriteAdapter.ViewHolder>(
        AsyncDifferConfig.Builder<IModel>(NoteDiffCallBack())
            .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
            .build()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_book))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = getItem(position) as? BookUIModel
        holder.item = model
        holder.delete.visibility = View.VISIBLE
        holder.name.text = model?.name
        holder.description.text = model?.des
        Glide.with(holder.itemView.context)
            .load(model?.image)
            .into(holder.image)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView = view.image
        var name: TextView = view.name
        var description: TextView = view.description
        var delete: ImageView = view.delete
        var item: BookUIModel? = null

        init {
            itemView.setOnClickListener {
                mListener?.onClickItem(item?.detail)
            }
            delete.setOnClickListener {
                mListener?.deleteBook(item?.id?:"")
            }
        }
    }

    interface FavoriteAdapterListener {
        fun onClickItem(url: String?)
        fun deleteBook(id: String)
    }
}