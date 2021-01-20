package com.applichic.chicsecret.database.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity
@Parcelize
data class Vault(
    @PrimaryKey val id: String,
    val name: String,
    val signature: ByteArray,
    @ColumnInfo(name = "created_at") val createdAt: Calendar,
    @ColumnInfo(name = "updated_at") val updatedAt: Calendar,
    @ColumnInfo(name = "deleted_at") val deletedAt: Calendar?
) : Parcelable {

    companion object : Parceler<Vault> {
        override fun Vault.write(parcel: Parcel, flags: Int) {
            parcel.writeString(id)
            parcel.writeString(name)
            parcel.writeInt(signature.size)
            parcel.writeByteArray(signature)
            parcel.writeValue(createdAt)
            parcel.writeValue(updatedAt)
            parcel.writeValue(deletedAt)
        }

        override fun create(parcel: Parcel): Vault {
            val id = parcel.readString()!!
            val name = parcel.readString()!!
            val signature = ByteArray(parcel.readInt())
            parcel.readByteArray(signature)
            val createdAt = parcel.readValue(Calendar::class.java.classLoader) as Calendar
            val updatedAt = parcel.readValue(Calendar::class.java.classLoader) as Calendar
            val deletedAt = parcel.readValue(Calendar::class.java.classLoader) as Calendar?

            return Vault(id, name, signature, createdAt, updatedAt, deletedAt)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vault

        if (id != other.id) return false
        if (name != other.name) return false
        if (!signature.contentEquals(other.signature)) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false
        if (deletedAt != other.deletedAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + signature.contentHashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + updatedAt.hashCode()
        result = 31 * result + (deletedAt?.hashCode() ?: 0)
        return result
    }
}