package com.maspamz.submission2kotlin.model.club

import com.google.gson.Gson
import com.maspamz.submission2kotlin.api.ApiRepository
import com.maspamz.submission2kotlin.api.ApiTheSportDB
import com.maspamz.submission2kotlin.model.LeagueResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Maspamz on 07/09/2018.
 */

class ClubPresenter(private val view: ClubView,
                    private val apiRespository: ApiRepository,
                    private val gson: Gson){

    fun getClubList(league: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRespository
                    .doRequest(ApiTheSportDB.getClub(league)),
                    LeagueResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showClubList(data.teams)
            }
        }
    }
}