package com.carlos.silva.inanimewetrust.presentation

import android.content.res.Resources
import android.util.TypedValue

object Utils {
    fun getAllDigitFromString(s: String) = s.replace("[^0-9]".toRegex(), "")

    fun dpToPx(dp: Float, r: Resources) = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        r.displayMetrics
    )
}