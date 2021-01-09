package com.applichic.chicsecret.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.applichic.chicsecret.database.dao.VaultDao
import com.applichic.chicsecret.database.models.Vault

@Database(entities = [Vault::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vaultDao(): VaultDao

    companion object {
        var db: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (db == null) {
                synchronized(AppDatabase::class) {
                    db = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "chic_secret"
                    ).build()
                }
            }
            return db
        }

        fun destroyDataBase() {
            db = null
        }
    }
}