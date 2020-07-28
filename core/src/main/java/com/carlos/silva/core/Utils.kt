package com.carlos.silva.core

import com.google.gson.Gson
import java.lang.reflect.Type

object Utils {
    fun <T> uncodedScriptText(s: String, type: Type): T {
        val r = s.replace("\\s".toRegex(), "")
        val gson = Gson()
        val index = r.indexOf("sources:")
        val lastIndex = r.lastIndexOf("],")
        val result = r.substring(index, lastIndex)
        val nowIndex = result.indexOf("[")
        val nowLastIndex = result.lastIndexOf("]")
        val endless = result.substring(nowIndex, nowLastIndex + 1)
        return gson.fromJson(endless, type)
    }
    fun getDigitFromString(s: String) = "\\d+".toRegex().find(s)!!.groupValues.last().toInt()
}