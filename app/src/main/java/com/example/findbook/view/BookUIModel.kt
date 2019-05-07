package com.example.findbook.view

import androidx.room.Entity
import com.example.findbook.model.IModel
import java.io.Serializable

@Entity(tableName = "BookDetail", primaryKeys = ["id"])
data class BookUIModel(
    var id: String,
    var name: String?,
    var des: String?,
    var image: String?,
    var detail: String?
) : IModel, Serializable