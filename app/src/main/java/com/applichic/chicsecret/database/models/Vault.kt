package com.applichic.chicsecret.database.models

import android.os.Parcel
import android.os.Parcelable
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
) : Parcelable {
    private constructor(parcel: Parcel) : this(
        id = parcel.readString()!!,
        name = parcel.readString()!!,
        signature = parcel.readString()!!,
        createdAt = parcel.readValue(Calendar::class.java.classLoader) as Calendar,
        updatedAt = parcel.readValue(Calendar::class.java.classLoader) as Calendar,
        deletedAt = parcel.readValue(Calendar::class.java.classLoader) as Calendar?
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(signature)
        parcel.writeValue(createdAt)
        parcel.writeValue(updatedAt)
        parcel.writeValue(deletedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Vault> {
        override fun createFromParcel(parcel: Parcel): Vault {
            return Vault(parcel)
        }

        override fun newArray(size: Int): Array<Vault?> {
            return arrayOfNulls(size)
        }
    }
}