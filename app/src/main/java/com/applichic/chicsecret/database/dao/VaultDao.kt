package com.applichic.chicsecret.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.applichic.chicsecret.database.models.Vault

@Dao
interface VaultDao {
    @Query("SELECT * FROM vault")
    fun getAll(): List<Vault>

    @Insert
    fun insert(vault: Vault)

    @Delete
    fun delete(vault: Vault)
}