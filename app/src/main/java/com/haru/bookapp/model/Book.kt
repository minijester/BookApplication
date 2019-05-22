package com.haru.bookapp.model

import com.google.gson.annotations.SerializedName

data class Book(@SerializedName("ISBN") var isbn:String,
                @SerializedName("title")var title:String,
                @SerializedName("subtitle")var subtitle:String,
                @SerializedName("publisher") var publisher:String,
                @SerializedName("pageCount") var page:Int
)
