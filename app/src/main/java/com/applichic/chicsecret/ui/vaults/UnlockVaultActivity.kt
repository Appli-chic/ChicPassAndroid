package com.applichic.chicsecret.ui.vaults

import android.content.Intent
import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.applichic.chicsecret.R
import com.applichic.chicsecret.database.models.Vault
import com.applichic.chicsecret.utils.Security
import com.applichic.chicsecret.utils.SIGNATURE
import com.applichic.chicsecret.utils.currentPassword
import com.applichic.chicsecret.utils.currentVault
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

const val VAULT_TO_UNLOCK_KEY = "vaultToUnlockKey"

class UnlockVaultActivity : AppCompatActivity() {
    private lateinit var passwordTextField: TextInputEditText
    private lateinit var passwordTextFieldLayout: TextInputLayout
    private var vault: Vault? = null

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelable(VAULT_TO_UNLOCK_KEY, vault)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unlock_vault)

        passwordTextField = findViewById(R.id.unlock_vault_password_input)
        passwordTextFieldLayout = findViewById(R.id.unlock_vault_password_input_layout)
        val saveButton = findViewById<Button>(R.id.unlock_vault_unlock_button)

        // Retrieve the information from the extra or the save instance
        if (savedInstanceState == null) {
            val extras = intent.extras
            if (extras != null) {
                vault = extras.getParcelable(VAULT_TO_UNLOCK_KEY)
            }
        } else {
            vault = savedInstanceState.getParcelable(VAULT_TO_UNLOCK_KEY)
        }

        // Listeners
        saveButton.setOnClickListener { unlockVault() }

        // Bind the toolbar from the xml
        setSupportActionBar(findViewById(R.id.unlock_vault_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.unlock_vault_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        R.id.unlock_vault_action_unlock -> {
            unlockVault()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    /**
     * Unlock the selected vault with the password the user wrote
     */
    private fun unlockVault() {
        var hasErrors = false

        // Error: the password must be defined
        if (passwordTextField.text == null || passwordTextField.text!!.isBlank()) {
            passwordTextFieldLayout.error = getString(R.string.new_vault_error_name_empty)
            hasErrors = true
        } else {
            passwordTextFieldLayout.error = null
        }

        // Try to unlock the vault
        if (!hasErrors && vault != null) {
            Thread {
                val signatureDecrypted =
                    Security.decrypt(passwordTextField.text.toString(), vault!!.signature)

                if (signatureDecrypted == SIGNATURE) {
                    // The password is correct
                    currentVault = vault
                    currentPassword = passwordTextField.text.toString()
                    setResult(RESULT_OK, null)
                    finish()
                } else {
                    // The password is wrong
                    runOnUiThread {
                        AlertDialog.Builder(this)
                            .setTitle(getString(R.string.error))
                            .setMessage(getString(R.string.wrong_password))
                            .setPositiveButton(android.R.string.ok, null)
                            .show()
                    }
                }
            }.start()
        }
    }
}