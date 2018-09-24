package com.maspamz.submission2kotlin.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson
import com.maspamz.submission2kotlin.DetailActivityClub

import com.maspamz.submission2kotlin.R
import com.maspamz.submission2kotlin.adapter.ClubAdapter
import com.maspamz.submission2kotlin.api.ApiRepository
import com.maspamz.submission2kotlin.model.League
import com.maspamz.submission2kotlin.model.club.ClubPresenter
import com.maspamz.submission2kotlin.model.club.ClubView
import kotlinx.android.synthetic.main.fragment_club.view.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh

class ClubFragment : Fragment(),ClubView {

    private var teams: MutableList<League> = mutableListOf()
    private lateinit var presenter: ClubPresenter
    private lateinit var adapter: ClubAdapter
    private lateinit var leagueName: String
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var spinner: Spinner
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    //val context:Context
    companion object {
        fun newInstance(): ClubFragment {
            var fragmentClub = ClubFragment()
            var args = Bundle()
            fragmentClub.arguments = args
            return fragmentClub
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(R.layout.fragment_club, container, false)
        val activity = activity
        //RecycleView plus Adapter
        adapter = ClubAdapter(context, teams){
            startActivity(intentFor<DetailActivityClub>(
                    "NAME_ITEM" to it.teamName,
                    "DESC_ITEM" to it.teamDescription,
                    "IMG_ITEM"  to it.teamBadge
            ))
        }

        listTeam = rootView.findViewById<RecyclerView>(R.id.lrv_view_club)
        listTeam.layoutManager = LinearLayoutManager(activity)
        listTeam.adapter = adapter


        spinner = rootView.l_spinner
        swipeRefreshLayout = rootView.l_swipe
        progressBar = rootView.l_progressBar

        val spinnerItems = resources.getStringArray(R.array.league)
        spinner.adapter =  ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                presenter.getClubList(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swipeRefreshLayout.onRefresh {
            presenter.getClubList(leagueName)
        }
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)

        //Get data
        val request = ApiRepository()
        val gson = Gson()
        presenter = ClubPresenter(this, request, gson)
        return rootView
    }


    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
       progressBar.visibility = View.INVISIBLE
    }

    override fun showClubList(data: List<League>) {
        swipeRefreshLayout.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }


}// Required empty public constructor
