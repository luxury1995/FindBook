package com.example.findbook.model

data class BookResponse (val kind: String,
                         val totalItems: Int,
                         val items: List<ItemResponse>): IModel