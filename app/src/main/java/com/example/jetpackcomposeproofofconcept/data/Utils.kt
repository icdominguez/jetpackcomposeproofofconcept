package com.example.jetpackcomposeproofofconcept.data

import java.math.BigInteger
import java.security.MessageDigest

object Utils {

    fun md5(stringToHash: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(stringToHash.toByteArray())).toString(16).padStart(32, '0')
    }

}