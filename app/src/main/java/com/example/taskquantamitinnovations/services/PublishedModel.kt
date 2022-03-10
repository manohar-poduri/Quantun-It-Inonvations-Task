package com.example.taskquantamitinnovations.services

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class PublishedModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var url: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String? = null,
    var content: String? = null
)

