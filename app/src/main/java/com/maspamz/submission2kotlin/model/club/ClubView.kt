package com.maspamz.submission2kotlin.model.club

import com.maspamz.submission2kotlin.model.League

/**
 * Created by Maspamz on 07/09/2018.
 */

interface ClubView {

    fun showLoading()

    fun showClubList(data: List<League>)

    fun hideLoading()

}