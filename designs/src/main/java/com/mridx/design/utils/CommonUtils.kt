package com.mridx.design.utils

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import androidx.core.widget.ImageViewCompat
import com.google.android.material.imageview.ShapeableImageView
import kotlin.math.roundToInt

class CommonUtils {

    companion object {
        fun convertPixelsToDp(px: Float, context: Context): Float {
            return px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }

        fun View.setSize(size: Int) {
            val indp = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                size.toFloat(),
                context.resources.displayMetrics
            ).roundToInt()
            this.layoutParams.width = indp
            this.layoutParams.height = indp
        }

        fun ShapeableImageView.setTint(color: Int) {
            this.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }


    }

}