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

class DetailMatchPresenter(private val view: DetailMatchView,
                           private val apiRespository: ApiRepository,
                           private val gson: Gson){

    fun getDetailMatchView(league: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRespository
                    .doRequest(ApiTheSportDB.getDetailMatch(league)),
                    LeagueResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showDetailMatchList(data.events)
            }
        }
    }

    fun getDetailClubView(league1: String?, league2: String?) {
        view.showLoading()
        doAsync {
            val data1 = gson.fromJson(apiRespository
                    .doRequest(ApiTheSportDB.getDetailClub(league1)),
                    LeagueResponse::class.java
            )
            val data2 = gson.fromJson(apiRespository
                    .doRequest(ApiTheSportDB.getDetailClub(league2)),
                    LeagueResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showDetailClub(data1.teams, data2.teams)
            }
        }
    }
}