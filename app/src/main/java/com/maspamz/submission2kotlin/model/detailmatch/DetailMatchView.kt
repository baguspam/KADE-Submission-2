package com.maspamz.submission2kotlin.model.club

import com.maspamz.submission2kotlin.model.League

/**
 * Created by Maspamz on 07/09/2018.
 */

interface DetailMatchView {

    fun showLoading()

    fun showDetailMatchList(data: List<League>)

    fun showDetailClub(data1: List<League>, data2: List<League>)

    fun hideLoading()

}