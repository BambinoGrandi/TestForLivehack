package ru.grandibambino.testforlifehackstudio.domain.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Company(
    @SerializedName("id")
    @Expose
    val id: String = "",
    @SerializedName("name")
    @Expose
    val name: String = "",
    @SerializedName("img")
    @Expose
    val image: String = "",
    @SerializedName("description")
    @Expose
    val description: String = "",
    @SerializedName("lat")
    @Expose
    val lat: String = "",
    @SerializedName("lon")
    @Expose
    val lon: String = "",
    @SerializedName("www")
    @Expose
    val www: String = "",
    @SerializedName("phone")
    @Expose
    val phone: String = ""
) : Parcelable