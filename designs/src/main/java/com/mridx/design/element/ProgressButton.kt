package com.mridx.design.element

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.core.view.isVisible
import com.mridx.design.R
import com.mridx.design.databinding.ProgressButtonBinding
import com.mridx.design.utils.CommonUtils.Companion.convertPixelsToDp
import com.mridx.design.utils.CommonUtils.Companion.setSize
import kotlin.math.roundToInt

class ProgressButton : LinearLayoutCompat {

    private val binding = ProgressButtonBinding.inflate(LayoutInflater.from(context), this, true)

    private var text = "Hello World"

    var clickListener: OnClickListener? = null


    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        render(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        render(context, attrs, defStyleAttr)
    }

    private fun render(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressButton)
        try {
            for (i in 0 until typedArray.indexCount) {
                when (val attr = typedArray.getIndex(i)) {
                    R.styleable.ProgressButton_buttonHeight -> binding.button.layoutParams.height =
                        typedArray.getDimension(attr, 20f).roundToInt()
                    R.styleable.ProgressButton_android_text -> binding.button.text =
                        typedArray.getString(attr).also {
                            text = it ?: text
                        }
                    R.styleable.ProgressButton_android_textSize -> binding.button.textSize =
                        convertPixelsToDp(typedArray.getDimension(attr, 20f), context)
                    R.styleable.ProgressButton_android_textStyle -> binding.button.setTypeface(
                        null,
                        typedArray.getInt(attr, 0)
                    )
                    R.styleable.ProgressButton_backgroundTint -> binding.button.setBackgroundColor(
                        typedArray.getColor(attr, Color.BLUE)
                    )
                    R.styleable.ProgressButton_cornerRadius -> binding.button.cornerRadius =
                        typedArray.getDimension(attr, 20f).roundToInt()
                    R.styleable.ProgressButton_android_elevation -> binding.button.elevation =
                        typedArray.getDimension(attr, 0f).convertPixelsToDp(context)
                    R.styleable.ProgressButton_elevation -> binding.button.elevation =
                        typedArray.getDimension(attr, 0f).convertPixelsToDp(context)
                    R.styleable.CommonTextAttributes_progressBarElevation -> binding.circleProgressbar.elevation =
                        typedArray.getDimension(attr, 5f).convertPixelsToDp(context)
                    R.styleable.ProgressButton_android_indeterminate -> binding.circleProgressbar.isIndeterminate =
                        typedArray.getBoolean(attr, true)
                    R.styleable.ProgressButton_showProgressbar -> binding.circleProgressbar.isVisible =
                        typedArray.getBoolean(attr, true)
                    R.styleable.ProgressButton_progressColor -> binding.circleProgressbar.indeterminateDrawable?.colorFilter =
                        BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                            typedArray.getColor(
                                attr,
                                Color.WHITE
                            ), BlendModeCompat.SRC_ATOP
                        )
                    R.styleable.ProgressButton_rippleColor -> binding.button.rippleColor =
                        ColorStateList.valueOf(typedArray.getColor(attr, Color.WHITE))
                }
            }

        } finally {
            typedArray.recycle()
        }

        binding.button.setOnClickListener {
            clickListener?.onClick(it)
        }


    }


    fun showProgressbar(show: Boolean) {
        binding.apply {
            if (show) {
                this.button.text = ""
                this.circleProgressbar.isVisible = true
            } else {
                this.button.text = text
                this.circleProgressbar.isVisible = false
            }
        }
    }


}