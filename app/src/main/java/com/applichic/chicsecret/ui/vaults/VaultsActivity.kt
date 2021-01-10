package com.applichic.chicsecret.ui.vaults

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.applichic.chicsecret.R
import com.applichic.chicsecret.database.AppDatabase
import com.applichic.chicsecret.database.models.Vault
import com.google.android.material.floatingactionbutton.FloatingActionButton


class VaultsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var vaultAdapter: VaultAdapter
    private var vaults = arrayListOf<Vault>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaults)

        // Bind the floating action button to create a new vault
        val addVaultFab = findViewById<FloatingActionButton>(R.id.vaults_add_fab)
        addVaultFab.setOnClickListener { goToNewVaultActivity() }

        // Bind the toolbar from the xml
        setSupportActionBar(findViewById(R.id.vaults_toolbar))

        // Bind the recycler view
        vaultAdapter = VaultAdapter(vaults)
        recyclerView = findViewById(R.id.vaults_recycler_view)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = vaultAdapter

        loadVaults()
    }

    /**
     * Load the vaults from the local database and display the vault list
     */
    private fun loadVaults() {
        Thread {
            val vaultDao = AppDatabase.db?.vaultDao()
            vaults = vaultDao?.getAll() as ArrayList<Vault>

            // Display the vaults
            runOnUiThread {
                vaultAdapter.setVaults(vaults)
                vaultAdapter.notifyDataSetChanged()
            }
        }.start()
    }

    /**
     * Start the [NewVaultActivity] to create a new vault
     */
    private fun goToNewVaultActivity() {
        intent = Intent(this, NewVaultActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.vaults_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.vaults_action_add_vault -> {
            goToNewVaultActivity()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}