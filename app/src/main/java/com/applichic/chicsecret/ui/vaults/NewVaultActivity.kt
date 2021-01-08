package com.applichic.chicsecret.ui.vaults

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.applichic.chicsecret.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class NewVaultActivity : AppCompatActivity() {
    private lateinit var nameTextField: TextInputEditText
    private lateinit var nameTextFieldLayout: TextInputLayout
    private lateinit var passwordTextField: TextInputEditText
    private lateinit var passwordTextFieldLayout: TextInputLayout
    private lateinit var verifyPasswordTextField: TextInputEditText
    private lateinit var verifyPasswordTextFieldLayout: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_vault)

        // Bind form
        nameTextField = findViewById(R.id.new_vault_name_input)
        nameTextFieldLayout = findViewById(R.id.new_vault_name_input_layout)
        passwordTextField = findViewById(R.id.new_vault_password_input)
        passwordTextFieldLayout = findViewById(R.id.new_vault_password_input_layout)
        verifyPasswordTextField = findViewById(R.id.new_vault_verify_password_input)
        verifyPasswordTextFieldLayout = findViewById(R.id.new_vault_verify_password_input_layout)
        val saveButton = findViewById<Button>(R.id.new_vault_save_button)
        
        // Listeners
        saveButton.setOnClickListener { save() }

        // Bind the toolbar from the xml
        setSupportActionBar(findViewById(R.id.new_vault_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.new_vault)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.new_vault_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        R.id.new_vault_action_save -> {
            save()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    /**
     * Check if all the information are filled, then encrypt the vault's signature
     * and save the vault in the local database.
     */
    private fun save() {
        // Error: the name must be defined
        if (nameTextField.text == null || nameTextField.text!!.isBlank()) {
            nameTextFieldLayout.error = getString(R.string.new_vault_error_name_empty)
        }

        // Error: the password must be defined and have more than 6 characters
        if (passwordTextField.text == null ||
            passwordTextField.text!!.isBlank() ||
            passwordTextField.text!!.length < 6
        ) {
            passwordTextFieldLayout.error = getString(R.string.new_vault_error_password_too_small)
        }

        // Error: both passwords must be the same
        if (verifyPasswordTextField.text == null ||
            verifyPasswordTextField.text!!.toString() != passwordTextField.text.toString()
        ) {
            verifyPasswordTextFieldLayout.error = getString(R.string.new_vault_error_password_not_identical)
        }

        // Encrypt the signature


        // Save the vault in the local database


        // Send back the information to the VaultsActivity

    }
}