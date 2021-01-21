package com.applichic.chicsecret.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.applichic.chicsecret.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val bottomNavBar: BottomNavigationView = findViewById(R.id.home_bottom_navbar)
        val addEntryFloatingButton: FloatingActionButton = findViewById(R.id.home_add_floating_button)

        bottomNavBar.menu.getItem(2).isEnabled = false
    }
}