package com.maspamz.submission2kotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.maspamz.submission2kotlin.R
import com.maspamz.submission2kotlin.model.League
import kotlinx.android.synthetic.main.item_club.view.*
/**
 * Created by PU on 9/6/2018.
 */

class ClubAdapter (private val context: Context?, private val myClub:List<League>, private val listener: (League) -> Unit)
    :RecyclerView.Adapter<ClubViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_club, parent, false)
        return ClubViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        holder.bindItem(myClub[position],listener)
    }

    override fun getItemCount(): Int = myClub.size




}
class ClubViewHolder(view:View): RecyclerView.ViewHolder(view) {
    private var view : View = view

    fun bindItem(myClub: League,  listener: (League) -> Unit){
        view.tv_name_club.text = myClub.teamName
        view.tv_description.text = myClub.teamDescription
        Glide.with(itemView.context).load(myClub.teamBadge).into(view.img_club)
        itemView.setOnClickListener { listener(myClub) }


    }

}

