package com.example.android.gamesredo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dates (

  @SerializedName("date"                 ) var date                 : String?           = null,
  @SerializedName("totalItems"           ) var totalItems           : Int?              = null,
  @SerializedName("totalEvents"          ) var totalEvents          : Int?              = null,
  @SerializedName("totalGames"           ) var totalGames           : Int?              = null,
  @SerializedName("totalGamesInProgress" ) var totalGamesInProgress : Int?              = null,
  @SerializedName("games"                ) var games                : ArrayList<Games>  = arrayListOf(),
  @SerializedName("events"               ) var events               : ArrayList<String> = arrayListOf()

): Parcelable