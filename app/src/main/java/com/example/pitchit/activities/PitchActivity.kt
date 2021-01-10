package com.example.pitchit.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.pitchit.R
import com.example.pitchit.fragments.ProductsFragment
import com.example.pitchit.fragments.ProfileFragment
import com.example.pitchit.fragments.SwipeFragment
import com.google.android.material.tabs.TabLayout

class PitchActivity : AppCompatActivity() {

    private var profileFragment: ProfileFragment? = null
    private var swipeFragment: SwipeFragment? = null
    private var productsFragment: ProductsFragment? = null

    private var profileTab: TabLayout.Tab? = null
    private var swipeTab: TabLayout.Tab? = null
    private var productsTab: TabLayout.Tab? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var navigationTabs = findViewById<TabLayout>(R.id.navigationTabs)

        profileTab = navigationTabs.newTab()
        swipeTab = navigationTabs.newTab()
        productsTab = navigationTabs.newTab()

        profileTab?.icon = ContextCompat.getDrawable(this, R.drawable.tab_profile)
        swipeTab?.icon = ContextCompat.getDrawable(this, R.drawable.tab_swipe)
        productsTab?.icon = ContextCompat.getDrawable(this, R.drawable.tab_products)

        navigationTabs.addTab(profileTab!!)
        navigationTabs.addTab(swipeTab!!)
        navigationTabs.addTab(productsTab!!)

        navigationTabs.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                onTabSelected(tab)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab){
                    profileTab -> {
                        if(profileFragment == null){
                            profileFragment = ProfileFragment()
                        }
                        replaceFragment(profileFragment!!)
                    }
                    swipeTab -> {
                        if(swipeFragment == null){
                            swipeFragment = SwipeFragment()
                        }
                        replaceFragment(swipeFragment!!)
                    }
                    productsTab -> {
                        if(productsFragment == null){
                            productsFragment = ProductsFragment()
                        }
                        replaceFragment(productsFragment!!)
                    }
                }
            }
        })
        profileTab?.select()
    }

    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    companion object {
        fun newIntent(context: Context?) = Intent(context, PitchActivity::class.java)
    }
}