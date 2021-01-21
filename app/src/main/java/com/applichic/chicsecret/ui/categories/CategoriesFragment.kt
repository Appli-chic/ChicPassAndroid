package com.applichic.chicsecret.ui.categories

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.applichic.chicsecret.R

class CategoriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_categories, container, false)

        // Bind the toolbar from the xml
        setHasOptionsMenu(true)

        val compatActivity = activity as AppCompatActivity
        compatActivity.setSupportActionBar(rootView?.findViewById(R.id.categories_toolbar))
        compatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.categories_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.finish()
                true
            }
            R.id.categories_action_add_category -> {
                startNewCategoryActivity()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    /**
     * Start an activity to create a new category
     */
    private fun startNewCategoryActivity() {

    }
}