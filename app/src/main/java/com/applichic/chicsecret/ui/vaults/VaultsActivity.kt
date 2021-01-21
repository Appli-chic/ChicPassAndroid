package com.applichic.chicsecret.ui.vaults

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.applichic.chicsecret.R
import com.applichic.chicsecret.database.AppDatabase
import com.applichic.chicsecret.database.models.Vault
import com.applichic.chicsecret.ui.HomeActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

const val NEW_VAULT_ACTIVITY_RESULT = 0
const val UNLOCK_ACTIVITY_RESULT = 1

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
        vaultAdapter = VaultAdapter(this, vaults)
        recyclerView = findViewById(R.id.vaults_recycler_view)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = vaultAdapter

        manageListSwipe()
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
        startActivityForResult(intent, NEW_VAULT_ACTIVITY_RESULT)
    }

    /**
     * Manage the swipe on each element of the list.
     * Displays a red background under the swipe to delete an element.
     */
    private fun manageListSwipe() {
        val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val position = viewHolder.adapterPosition
                vaults.removeAt(position)
                vaultAdapter.notifyDataSetChanged()
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )

                // Displays background red during the delete swipe
                val itemView: View = viewHolder.itemView

                val background = ColorDrawable(Color.RED)
                background.setBounds(
                    itemView.right + dX.toInt(),
                    itemView.top, itemView.right, itemView.bottom
                )
                background.draw(c)
            }
        }

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            if (requestCode == NEW_VAULT_ACTIVITY_RESULT) {
                // We add the freshly created vault to the list
                val vault = data?.extras?.get(NEW_VAULT_RESULT_INTENT) as Vault?

                if (vault != null) {
                    vaults.add(vault)
                    vaultAdapter.setVaults(vaults)
                    vaultAdapter.notifyDataSetChanged()
                }
            } else if (requestCode == UNLOCK_ACTIVITY_RESULT) {
                intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}