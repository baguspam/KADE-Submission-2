package com.maspamz.submission2kotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.maspamz.submission2kotlin.R
import com.maspamz.submission2kotlin.model.League
import kotlinx.android.synthetic.main.item_match_list.view.*
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Created by PU on 9/6/2018.
 */

class MatchAdapter (private val context: Context?, private val myMatchNext:List<League>, private val listener: (League) -> Unit)
    :RecyclerView.Adapter<MatchNextViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchNextViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_match_list, parent, false)
        return MatchNextViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchNextViewHolder, position: Int) {
        holder.bindItem(myMatchNext[position],listener)
    }

    override fun getItemCount(): Int = myMatchNext.size




}
class MatchNextViewHolder(view:View): RecyclerView.ViewHolder(view) {
    private var view : View = view

    fun bindItem(myMatchNext: League,  listener: (League) -> Unit){
        view.tv_name_match1.text = myMatchNext.strHomeTeam
        view.tv_name_match2.text = myMatchNext.strAwayTeam

        //Convert Tanggal
        var tanggal = myMatchNext.dateEvent
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

        view.tv_date_match.text = tanggal
        view.tv_id_event.text = myMatchNext.idEvent
        if (myMatchNext.intHomeScore != null) {
           view.tv_score.text = myMatchNext.intHomeScore + " VS " + myMatchNext.intAwayScore
        } else {
            view.tv_score.text = "  VS  "
        }
        itemView.setOnClickListener { listener(myMatchNext) }


    }

}

