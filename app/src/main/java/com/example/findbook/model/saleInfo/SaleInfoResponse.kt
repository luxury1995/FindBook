package com.example.findbook.model.saleInfo


data class SaleInfoResponse(val country: String,
                            val saleability: String,
                            val isEbook: Boolean,
                            val listPrice: ListPriceResponse,
                            val retailPrice : RetailPriceResponse,
                            val buyLink : String
)