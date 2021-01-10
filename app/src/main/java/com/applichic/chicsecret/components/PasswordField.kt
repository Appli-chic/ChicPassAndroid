package com.applichic.chicsecret.components

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.applichic.chicsecret.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class PasswordField : ConstraintLayout {
    private lateinit var passwordTextField: TextInputEditText
    private lateinit var passwordTextFieldLayout: TextInputLayout
    private lateinit var progressBar: ProgressBar

    constructor(context: Context) : super(context) {
        inflateXml()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        inflateXml()
    }

    private fun inflateXml() {
        val rootView: View = inflate(context, R.layout.custom_password_field, this)
        passwordTextField = rootView.findViewById(R.id.custom_password_field_input)
        passwordTextFieldLayout = rootView.findViewById(R.id.custom_password_field_input_layout)
        progressBar = rootView.findViewById(R.id.custom_password_field_progressbar)

        // Listen to the text changing
        passwordTextField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s.isNullOrBlank()) {
                    // Hide the progress bar
                    progressBar.visibility = View.GONE
                } else {
                    // Change the color and progress toward the security of the password
                    setPasswordStrength(s.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    /**
     * Displays the progressbar to show the password's strength.
     * Set a color and a progress to show how strong is a password
     *
     * @param [password] The password of the field
     */
    private fun setPasswordStrength(password: String) {
        progressBar.visibility = View.VISIBLE

        val bgDrawable: Drawable = progressBar.progressDrawable

        when {
            password.length < 6 -> {
                progressBar.progress = 1
                bgDrawable.setTint(ResourcesCompat.getColor(resources, R.color.red, null))
            }
            password.length < 12 -> {
                progressBar.progress = 2
                bgDrawable.setTint(ResourcesCompat.getColor(resources, R.color.orange, null))
            }
            password.length < 17 -> {
                progressBar.progress = 3
                bgDrawable.setTint(ResourcesCompat.getColor(resources, R.color.green, null))
            }
            else -> {
                progressBar.progress = 4
                bgDrawable.setTint(ResourcesCompat.getColor(resources, R.color.deep_green, null))
            }
        }

        progressBar.progressDrawable = bgDrawable
    }

    /**
     * Get the password text
     * @return the password text as an [Editable]
     */
    fun getText(): Editable? {
        return passwordTextField.text
    }

    /**
     * Displays the error message in the input layout
     * @param [error] The error message
     */
    fun setError(error: String?) {
        passwordTextFieldLayout.error = error
    }
}