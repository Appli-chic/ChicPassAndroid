package com.applichic.chicsecret.utils

import android.util.Log
import java.lang.Exception
import java.nio.charset.StandardCharsets
import java.security.spec.KeySpec
import javax.crypto.Cipher
import javax.crypto.Cipher.DECRYPT_MODE
import javax.crypto.Cipher.ENCRYPT_MODE
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

const val SIGNATURE = "~ChicPass/signature"
const val IV = "RMZ#K<u'tjq26Y-i"
const val SALT = "Xx2VzeJRr7R5sloGZQoPcNdhC803e97o"

class Security {
    companion object {
        /**
         * Initialize the cipher object to encrypt and decrypt
         *
         * @param keyString The key used to encrypt and decrypt a message
         * @param mode ENCRYPT_MODE to encrypt and DECRYPT_MODE to decrypt
         *
         */
        private fun init(keyString: String, mode: Int): Cipher {
            val keygen = KeyGenerator.getInstance("AES")
            keygen.init(256)
            val factory: SecretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
            val spec: KeySpec = PBEKeySpec(
                keyString.toCharArray(),
                SALT.toByteArray(StandardCharsets.UTF_8),
                4096,
                256,
            )
            val tmp: SecretKey = factory.generateSecret(spec)
            val key: SecretKey = SecretKeySpec(tmp.encoded, "AES")
            val cipher = Cipher.getInstance("AES/CBC/PKCS7PADDING")
            val ivSpec = IvParameterSpec(IV.toByteArray(StandardCharsets.UTF_8))
            cipher.init(mode, key, ivSpec)
            return cipher
        }

        /**
         * Encrypt a password using the vault's password
         *
         * @param keyString The password from the vault
         * @param password The password to encrypt
         */
        fun encrypt(keyString: String, password: String): ByteArray {
            return try {
                val cipher = init(keyString, ENCRYPT_MODE)
                cipher.doFinal(password.toByteArray(StandardCharsets.UTF_8))
            } catch (e: Exception) {
                Log.e("Security", "Error while encrypting: " + e.message)
                ByteArray(0)
            }
        }

        /**
         * Decrypt a password using the vault's password
         *
         * @param keyString The password from the vault
         * @param password The password to encrypt
         */
        fun decrypt(keyString: String, password: ByteArray): String {
            return try {
                val cipher = init(keyString, DECRYPT_MODE)
                val decryptedPassword: ByteArray = cipher.doFinal(password)

                String(decryptedPassword, StandardCharsets.UTF_8)
            } catch (e: Exception) {
                Log.e("Security", "Error while decrypting: " + e.message)
                ""
            }
        }
    }
}