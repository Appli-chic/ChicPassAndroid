package com.applichic.chicsecret

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.applichic.chicsecret.database.AppDatabase
import com.applichic.chicsecret.ui.vaults.VaultsActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppDatabase.getAppDataBase(this)

        intent = Intent(this, VaultsActivity::class.java)
        startActivity(intent)
        finish()
    }
}