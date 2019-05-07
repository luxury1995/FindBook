package com.example.findbook.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.findbook.model.IModel

class NoteDiffCallBack : DiffUtil.ItemCallback<IModel>() {
    override fun areItemsTheSame(oldItem: IModel, newItem: IModel): Boolean {
        return oldItem== newItem
    }

    override fun areContentsTheSame(oldItem: IModel, newItem: IModel): Boolean {
        return oldItem == newItem
    }
}