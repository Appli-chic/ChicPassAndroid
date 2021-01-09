package com.applichic.chicsecret.ui.vaults

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.applichic.chicsecret.R
import com.applichic.chicsecret.database.AppDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton


class VaultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaults)

        // Bind the floating action button to create a new vault
        val addVaultFab = findViewById<FloatingActionButton>(R.id.vaults_add_fab)
        addVaultFab.setOnClickListener { goToAddVaultActivity() }

        // Bind the toolbar from the xml
        setSupportActionBar(findViewById(R.id.vaults_toolbar))
        supportActionBar?.title = getString(R.string.vaults)

        Thread {
            val vaultDao = AppDatabase.db?.vaultDao()
            val vaults = vaultDao?.getAll()

            runOnUiThread {
            }
        }.start()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.vaults_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.vaults_action_add_vault -> {
            goToAddVaultActivity()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    /**
     * Start the [NewVaultActivity] to create a new vault
     */
    private fun goToAddVaultActivity() {
        intent = Intent(this, NewVaultActivity::class.java)
        startActivity(intent)
    }
}