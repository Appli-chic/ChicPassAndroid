package com.applichic.chicsecret.ui.categories

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.GridView
import com.applichic.chicsecret.R

class NewCategoryActivity : AppCompatActivity() {
    private lateinit var adapter: IconAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_category)

        // Set the icons grid
        val iconGrid = findViewById<GridView>(R.id.new_category_icon_grid)
        adapter = IconAdapter(this, arrayListOf("", "", "", "", "", "", ""))
        iconGrid.adapter = adapter
        iconGrid.setOnItemClickListener { _, _, position, _ ->
            adapter.selectedIcon = position
            adapter.notifyDataSetChanged()
        }

        // Set the colors grid

        // Bind the toolbar from the xml
        setSupportActionBar(findViewById(R.id.new_category_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.new_category_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.new_category_action_save -> {
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}