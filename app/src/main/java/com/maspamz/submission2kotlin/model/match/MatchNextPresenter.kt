package com.maspamz.submission2kotlin.model.match

import com.google.gson.Gson
import com.maspamz.submission2kotlin.api.ApiRepository
import com.maspamz.submission2kotlin.api.ApiTheSportDB
import com.maspamz.submission2kotlin.model.LeagueResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Maspamz on 07/09/2018.
 */

class MatchNextPresenter(private val view: MatchView,
                         private val apiRespository: ApiRepository,
                         private val gson: Gson){

    fun getMatchNextList(league: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRespository
                    .doRequest(ApiTheSportDB.getMatchNext(league)),
                    LeagueResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)
            }
        }
    }
}