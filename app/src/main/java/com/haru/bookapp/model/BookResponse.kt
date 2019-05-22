package com.haru.bookapp.model

import com.google.gson.annotations.SerializedName

data class BookResponse(@SerializedName("kind") val kind: String,
                        @SerializedName("totalItems") val totalItems: Int,
                        @SerializedName("incomplete_results") val incomplete_results: Boolean,
                        @SerializedName("items") val books: ArrayList<Book>
)