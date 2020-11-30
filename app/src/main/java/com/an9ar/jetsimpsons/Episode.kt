package com.an9ar.jetsimpsons

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Episode(
    val id: Long,
    val image_url: String,
    val imdb_rating: Double,
    val imdb_votes: Long,
    val number_in_season: Int,
    val number_in_series: Int,
    val original_air_date: String,
    val original_air_year: Int,
    val production_code: String,
    val season: Int,
    val title: String,
    val us_viewers_in_millions: String,
    val video_url: String,
    val views: Long
) : Parcelable