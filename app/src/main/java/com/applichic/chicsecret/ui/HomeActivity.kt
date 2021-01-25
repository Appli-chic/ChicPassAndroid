package com.applichic.chicsecret.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.applichic.chicsecret.R
import com.applichic.chicsecret.ui.categories.CategoriesFragment
import com.applichic.chicsecret.ui.entries.EntriesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val bottomNavBar: BottomNavigationView = findViewById(R.id.home_bottom_navbar)

        bottomNavBar.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment

            when (item.itemId) {
                R.id.home_navigation_passwords -> {
                    selectedFragment = EntriesFragment()
                }
                R.id.home_navigation_categories -> {
                    selectedFragment = CategoriesFragment()
                }
                R.id.home_navigation_health -> {
                    selectedFragment = Fragment()
                }
                R.id.home_navigation_settings -> {
                    selectedFragment = Fragment()
                }
                else -> {
                    selectedFragment = EntriesFragment()
                }
            }

            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.home_host_fragment, selectedFragment)
            transaction.commit()

            true
        }

        bottomNavBar.selectedItemId = R.id.home_navigation_passwords
    }
}