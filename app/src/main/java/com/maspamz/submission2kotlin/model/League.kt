package com.maspamz.submission2kotlin.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Maspamz on 07/09/2018.
 */
data class League(

        //Pojo dari Club//
        @SerializedName("idTeam")
        var teamId: String? = null,

        @SerializedName("strTeam")
        var teamName: String? = null,

        @SerializedName("strTeamBadge")
        var teamBadge: String? = null,

        @SerializedName("strDescriptionEN")
        var teamDescription: String? = null,

        //Pojo dari Event Next dan Last//
        @field:SerializedName("idEvent")
        val idEvent: String? = null,

        @field:SerializedName("dateEvent")
        val dateEvent: String? = null,

        @field:SerializedName("strTime")
        val strTime: String? = null,

        @field:SerializedName("strLeague")
        val strLeague: String? = null,

        //Club Home
        @field:SerializedName("idHomeTeam")
        val idHomeTeam: String? = null,

        @field:SerializedName("strHomeTeam")
        val strHomeTeam: String? = null,

        @field:SerializedName("intHomeScore")
        val intHomeScore: String? = null,

        @field:SerializedName("intHomeShots")
        val intHomeShots: String? = null,

        @field:SerializedName("strHomeFormation")
        val strHomeFormation: String? = null,

        @field:SerializedName("strHomeGoalDetails")
        val strHomeGoalDetails: String? = null,

        @field:SerializedName("strHomeLineupGoalkeeper")
        val strHomeLineupGoalkeeper: String? = null,

        @field:SerializedName("strHomeLineupDefense")
        val strHomeLineupDefense: String? = null,

        @field:SerializedName("strHomeLineupMidfield")
        val strHomeLineupMidfield: String? = null,

        @field:SerializedName("strHomeLineupForward")
        val strHomeLineupForward: String? = null,

        @field:SerializedName("strHomeLineupSubstitutes")
        val strHomeLineupSubstitutes: String? = null,

        //Club Lawan
        @field:SerializedName("idAwayTeam")
        val idAwayTeam: String? = null,

        @field:SerializedName("strAwayTeam")
        val strAwayTeam: String? = null,

        @field:SerializedName("intAwayScore")
        val intAwayScore: String? = null,

        @field:SerializedName("intAwayShots")
        val intAwayShots: String? = null,

        @field:SerializedName("strAwayFormation")
        val strAwayFormation: String? = null,

        @field:SerializedName("strAwayGoalDetails")
        val strAwayGoalDetails: String? = null,

        @field:SerializedName("strAwayLineupGoalkeeper")
        val strAwayLineupGoalkeeper: String? = null,

        @field:SerializedName("strAwayLineupDefense")
        val strAwayLineupDefense: String? = null,

        @field:SerializedName("strAwayLineupMidfield")
        val strAwayLineupMidfield: String? = null,

        @field:SerializedName("strAwayLineupForward")
        val strAwayLineupForward: String? = null,

        @field:SerializedName("strAwayLineupSubstitutes")
        val strAwayLineupSubstitutes: String? = null

)