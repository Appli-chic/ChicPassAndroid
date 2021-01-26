package com.applichic.chicsecret.ui.categories

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.view.inputmethod.InputMethodManager
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.applichic.chicsecret.R
import com.applichic.chicsecret.utils.colors
import com.applichic.chicsecret.utils.icons


class NewCategoryActivity : AppCompatActivity() {
    private lateinit var iconAdapter: IconAdapter
    private lateinit var colorAdapter: ColorAdapter

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_category)

        // Set the icons grid
        val iconGrid = findViewById<GridView>(R.id.new_category_icon_grid)
        iconAdapter = IconAdapter(this, icons)
        iconGrid.adapter = iconAdapter
        iconGrid.setOnItemClickListener { _, _, position, _ ->
            iconAdapter.selectedIcon = position
            iconAdapter.notifyDataSetChanged()
        }

        iconGrid.setOnTouchListener(OnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_MOVE) {
                return@OnTouchListener true
            }
            false
        })

        // Set the colors grid
        val colorGrid = findViewById<GridView>(R.id.new_category_color_grid)
        colorAdapter = ColorAdapter(this, colors)
        colorGrid.adapter = colorAdapter
        colorGrid.setOnItemClickListener { _, _, position, _ ->
            colorAdapter.selectedColor = position
            iconAdapter.selectedColor = ContextCompat.getColor(this, colors[position])
            colorAdapter.notifyDataSetChanged()
            iconAdapter.notifyDataSetChanged()
        }

        colorGrid.setOnTouchListener(OnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_MOVE) {
                return@OnTouchListener true
            }
            false
        })

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