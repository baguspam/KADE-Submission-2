package com.maspamz.submission2kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.maspamz.submission2kotlin.R.id.*
import com.maspamz.submission2kotlin.fragment.ClubFragment
import com.maspamz.submission2kotlin.fragment.MatchLastFragment
import com.maspamz.submission2kotlin.fragment.MatchNextFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        l_navigation.setOnNavigationItemSelectedListener({
            item -> when(item.itemId){
                navigation_match_next ->{
                    loadMatchNextFragment(savedInstanceState)
                }
                navigation_match_last ->{
                    loadMatchLastFragment(savedInstanceState)
                }
                navigation_club ->{
                    loadClubFragment(savedInstanceState)
                }
             }
            true
        })

        l_navigation.selectedItemId = navigation_match_next

    }

    private fun loadMatchNextFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.layout_main, MatchNextFragment(), MatchNextFragment::class.simpleName)
                    .commit()
        }
    }

    private fun loadMatchLastFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.layout_main, MatchLastFragment(), MatchLastFragment::class.simpleName)
                    .commit()
        }
    }

    private fun loadClubFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.layout_main, ClubFragment(), ClubFragment::class.simpleName)
                    .commit()
        }
    }


}
