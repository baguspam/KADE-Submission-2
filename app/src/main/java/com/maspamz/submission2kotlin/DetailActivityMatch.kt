package com.maspamz.submission2kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.maspamz.submission2kotlin.api.ApiRepository
import com.maspamz.submission2kotlin.model.League
import com.maspamz.submission2kotlin.model.club.DetailMatchPresenter
import com.maspamz.submission2kotlin.model.club.DetailMatchView
import kotlinx.android.synthetic.main.activity_detail_match.*
import android.view.View
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Maspamz on 10/09/2018.
 */

class DetailActivityMatch: AppCompatActivity(), DetailMatchView {

    private var idClub1: String = ""
    private var idClub2: String = ""
    private var idEvent: String = ""

    private lateinit var team: League
    private lateinit var events: League
    private lateinit var presenter: DetailMatchPresenter

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        title = "Detail Match"

        val intent = intent
        idClub1 = intent.getStringExtra("ID_CLUB1")
        idClub2 = intent.getStringExtra("ID_CLUB2")
        idEvent = intent.getStringExtra("ID_EVENT")
        id_match.text = idEvent
        Log.d("Cek id Event", idEvent)
        progressBar = l_progressBar

        //Get data
        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailMatchPresenter(this, request, gson)
        presenter.getDetailMatchView(idEvent)
        presenter.getDetailClubView(idClub1, idClub2)

    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showDetailMatchList(data: List<League>) {
        if (data.get(0).intHomeScore != null) {
            //Convert Tanggal
            var tanggal = data.get(0).dateEvent
            var locale = Locale("ID")
            var spfku = SimpleDateFormat("yyyy-M-d", locale)
            val newDate: Date
            try {
                newDate = spfku.parse(tanggal)
                spfku = SimpleDateFormat("E, d MMM  yyyy", locale)
                tanggal = spfku.format(newDate)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            //Set to textview
            l_match_date.text = tanggal

            //Club Home
            l_score1.text = data.get(0).intHomeScore
            l_formation_club1.text = data.get(0).strHomeFormation
            l_shots1.text = data.get(0).intHomeShots.toString()
            l_goalkeeper1.text = data.get(0).strHomeLineupGoalkeeper
            l_name_goals1.text = data.get(0).strHomeGoalDetails
            l_midfield1.text = data.get(0).strHomeLineupMidfield
            l_defense1.text = data.get(0).strHomeLineupDefense
            l_forward1.text = data.get(0).strHomeLineupForward
            l_substitutes1.text = data.get(0).strHomeLineupSubstitutes

            //Club Lawan
            l_score2.text = data.get(0).intAwayScore
            l_formation_club2.text = data.get(0).strAwayFormation
            l_shots2.text = data.get(0).intAwayShots
            l_goalkeeper2.text = data.get(0).strAwayLineupGoalkeeper
            l_name_goals2.text = data.get(0).strAwayGoalDetails
            l_midfield2.text = data.get(0).strAwayLineupMidfield
            l_defense2.text = data.get(0).strAwayLineupDefense
            l_forward2.text = data.get(0).strAwayLineupForward
            l_substitutes2.text = data.get(0).strAwayLineupSubstitutes

        }else{

        }

    }

    override fun showDetailClub(data1: List<League>, data2: List<League>) {

        l_name_club1.text = data1.get(0).teamName
        l_name_club2.text = data2.get(0).teamName
        Glide.with(this).load(data1.get(0).teamBadge).into(l_img_club1)
        Glide.with(this).load(data2.get(0).teamBadge).into(l_img_club2)
    }


}
