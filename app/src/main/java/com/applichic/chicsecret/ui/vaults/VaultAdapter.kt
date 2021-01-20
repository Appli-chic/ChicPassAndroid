package com.applichic.chicsecret.ui.vaults

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.applichic.chicsecret.R
import com.applichic.chicsecret.database.models.Vault


class VaultAdapter(private val context: Context, private var vaults: ArrayList<Vault>) :
    RecyclerView.Adapter<VaultAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vault, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vault = vaults[position]
        holder.nameTextView.text = vault.name

        // Go to the page to unlock the password
        holder.itemView.setOnClickListener {
            val intent = Intent(context, UnlockVaultActivity::class.java)
            intent.putExtra(vaultToUnlockKey, vault)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return vaults.size
    }

    /**
     * Update the list of vaults
     */
    fun setVaults(vaults: ArrayList<Vault>) {
        this.vaults = vaults
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.item_vault_name)
    }
}