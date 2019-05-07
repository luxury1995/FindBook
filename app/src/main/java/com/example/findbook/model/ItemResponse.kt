package com.example.findbook.model

import com.example.findbook.model.accessInfo.AccessInfoResponse
import com.example.findbook.model.saleInfo.SaleInfoResponse
import com.example.findbook.model.searchinfo.SearchInfoResponse
import com.example.findbook.model.volumeinfo.VolumeInfoResponse

data class ItemResponse(val kind: String,
                        val id: String,
                        val etag: String,
                        val selfLink: String,
                        val volumeInfo: VolumeInfoResponse,
                        val saleInfo: SaleInfoResponse,
                        val accessInfo: AccessInfoResponse,
                        val searchInfo: SearchInfoResponse): IModel