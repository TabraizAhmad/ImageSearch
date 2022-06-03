package com.electrolux.imagesearchapp.network.model

import com.google.gson.annotations.SerializedName


data class Photo (

    @SerializedName("id"       ) var id       : String? = null,
    @SerializedName("owner"    ) var owner    : String? = null,
    @SerializedName("secret"   ) var secret   : String? = null,
    @SerializedName("server"   ) var server   : String? = null,
    @SerializedName("farm"     ) var farm     : Int?    = null,
    @SerializedName("title"    ) var title    : String? = null,
    @SerializedName("ispublic" ) var ispublic : Int?    = null,
    @SerializedName("isfriend" ) var isfriend : Int?    = null,
    @SerializedName("isfamily" ) var isfamily : Int?    = null,
    @SerializedName("url_m"    ) var urlM     : String? = null,
    @SerializedName("height_m" ) var heightM  : Int?    = null,
    @SerializedName("width_m"  ) var widthM   : Int?    = null

)