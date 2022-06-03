package com.electrolux.imagesearchapp.network.model

import com.google.gson.annotations.SerializedName

data class FlickrImageSearchData(
    @SerializedName("photos" ) var photos : Photos? = Photos(),
    @SerializedName("stat"   ) var stat   : String? = null


)
