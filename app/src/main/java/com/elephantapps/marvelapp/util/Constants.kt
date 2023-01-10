package com.elephantapps.marvelapp.util

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constants {

    companion object{
        const val BASE_URL = "https://gateway.marvel.com"
        val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()
        const val API_KEY = "d322c507df0b7e983cdc805d61620cab"
        const val PRIVATE_KEY = "dcf39a6e58714768a01c66f8a6e02effba53b633"
        const val limit = 20
        fun hash(): String{
            val input = "$timeStamp$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1,md.digest(input.toByteArray())).toString(16).padStart(32,'0')
        }
    }
}