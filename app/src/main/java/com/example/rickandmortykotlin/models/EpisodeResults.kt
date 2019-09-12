package com.example.rickandmortykotlin.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class EpisodeResults(
    var id: Int,
    @SerializedName("air_date")
    var airdate: String?,
    var episode: String?,
    var characters: ArrayList<String>?,
    var url: String?,
    var created: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(airdate)
        parcel.writeString(episode)
        parcel.writeStringList(characters)
        parcel.writeString(url)
        parcel.writeString(created)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EpisodeResults> {
        override fun createFromParcel(parcel: Parcel): EpisodeResults {
            return EpisodeResults(parcel)
        }

        override fun newArray(size: Int): Array<EpisodeResults?> {
            return arrayOfNulls(size)
        }
    }
}