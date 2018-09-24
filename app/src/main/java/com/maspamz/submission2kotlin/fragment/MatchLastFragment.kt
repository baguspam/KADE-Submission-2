package com.maspamz.submission2kotlin.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson
import com.maspamz.submission2kotlin.DetailActivityMatch
import com.maspamz.submission2kotlin.R
import com.maspamz.submission2kotlin.adapter.MatchAdapter
import com.maspamz.submission2kotlin.api.ApiRepository
import com.maspamz.submission2kotlin.model.League
import com.maspamz.submission2kotlin.model.match.MatchLastPresenter
import com.maspamz.submission2kotlin.model.match.MatchView
import kotlinx.android.synthetic.main.fragment_match.*
import kotlinx.android.synthetic.main.fragment_match.view.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh

class MatchLastFragment : Fragment(), MatchView {

    private var events: MutableList<League> = mutableListOf()
    private lateinit var presenter: MatchLastPresenter
    private lateinit var adapter: MatchAdapter
    private lateinit var leagueName: String
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var spinner: Spinner
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    //val context:Context
    companion object {
        fun newInstance(): MatchNextFragment {
            var fragmentMatch = MatchNextFragment()
            var args = Bundle()
            fragmentMatch.arguments = args
            return fragmentMatch
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(R.layout.fragment_match, container, false)
        val activity = activity
        //RecycleView plus Adapter
        adapter = MatchAdapter(context, events) {
            startActivity(intentFor<DetailActivityMatch>(
                    "ID_CLUB1" to it.idHomeTeam,
                    "ID_CLUB2" to it.idAwayTeam,
                    "ID_EVENT" to it.idEvent
            ))
        }

        listTeam = rootView.findViewById<RecyclerView>(R.id.lrv_view_match)
        listTeam.layoutManager = LinearLayoutManager(activity as Context?)
        listTeam.adapter = adapter


        spinner = rootView.l_spinner
        swipeRefreshLayout = rootView.l_swipe
        progressBar = rootView.l_progressBar

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerItemsId = resources.getStringArray(R.array.league_id)
        spinner.adapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val getLeague = spinner.selectedItemPosition
                leagueName = spinnerItemsId[getLeague].toString()
                presenter.getMatchLastList(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swipeRefreshLayout.onRefresh {
            presenter.getMatchLastList(leagueName)
        }
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)

        //Get data
        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchLastPresenter(this, request, gson)

        return rootView
    }


    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showMatchList(data: List<League>) {
        swipeRefreshLayout.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

}

