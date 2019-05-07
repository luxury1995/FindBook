package com.example.findbook.model.accessInfo

data class AccessInfoResponse(val country : String,
                              val viewability : String,
                              val embeddable: Boolean,
                              val publicDomain: Boolean,
                              val textToSpeechPermission: String ,
                              val epub : EpubResponse,
                              val pdf: PdfResponse,
                              val webReaderLink: String,
                              val accessViewStatus: String,
                              val quoteSharingAllowed: Boolean)