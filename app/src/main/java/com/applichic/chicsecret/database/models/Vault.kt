package com.applichic.chicsecret.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Vault(
    @PrimaryKey val id: String,
    val name: String,
    val signature: String,
    @ColumnInfo(name = "created_at") val createdAt: Calendar,
    @ColumnInfo(name = "updated_at") val updatedAt: Calendar,
    @ColumnInfo(name = "deleted_at") val deletedAt: Calendar?
)