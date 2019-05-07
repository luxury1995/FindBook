package com.example.findbook.model.volumeinfo

import com.example.findbook.model.volumeinfo.ImageLinksResponse
import com.example.findbook.model.volumeinfo.PanelizationSummaryResponse
import com.example.findbook.model.volumeinfo.ReadingResponse
import java.util.*

data class VolumeInfoResponse(
    val title: String,
    val authors: List<String>,
    val publisher: String,
//    val publishedDate: Date,
    val description: String,
    val readingModes: ReadingResponse,
    val pageCount: Int,
    val printType: String,
    val maturityRating: String,
    val allowAnonLogging: Boolean,
    val contentVersion: String,
    val panelizationSummary: PanelizationSummaryResponse,
    val imageLinks: ImageLinksResponse,
    val language: String,
    val previewLink: String,
    val infoLink: String,
    val canonicalVolumeLink: String
    )