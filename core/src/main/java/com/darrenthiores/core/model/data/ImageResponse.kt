package com.darrenthiores.core.model.data

import com.google.gson.annotations.SerializedName

data class ImageResponse(

    @SerializedName("data")
    var data:Data

)

data class Data(

    @SerializedName("memes")
    var memes:List<Memes>

)

data class Memes(

    @SerializedName("url")
    var image:String

)
