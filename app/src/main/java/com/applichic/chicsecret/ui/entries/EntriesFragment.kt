package com.applichic.chicsecret.ui.entries

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.applichic.chicsecret.R


class EntriesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_entries, container, false)

        // Bind the toolbar from the xml
        setHasOptionsMenu(true)

        val compatActivity = activity as AppCompatActivity
        compatActivity.setSupportActionBar(rootView?.findViewById(R.id.entries_toolbar))
        compatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.entries_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.finish()
                true
            }
            R.id.entries_action_add_entry -> {
                startNewEntryActivity()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    /**
     * Start an activity to create a new Password
     */
    private fun startNewEntryActivity() {

    }
}