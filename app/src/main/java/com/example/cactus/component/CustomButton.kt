package com.example.cactus.component

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.cactus.R
class CustomButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {

    private val defaultBackgroundColor = ContextCompat.getColor(context,R.color.dark_blue_600)
    private val defaultTextColor = ContextCompat.getColor(context,R.color.white)

    private var customBackgroundColor: Int = 0
        set(value) {
            field = value
            (background as GradientDrawable).setColor(value)
        }

    init {
        val drawable = GradientDrawable().apply {
            setColor(defaultBackgroundColor)
            setSize(dpToPx(280), dpToPx(60))
            cornerRadius = dpToPx(15).toFloat()
        }

        layoutParams = ConstraintLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(0, dpToPx(10), 0, dpToPx(47))
        }

        gravity = Gravity.CENTER
        background = drawable
        setTextColor(defaultTextColor)
        setTextSize(TypedValue.DENSITY_DEFAULT, textSize)

        context.obtainStyledAttributes(attrs, R.styleable.CustomButton).use { typedArray ->
            customBackgroundColor = typedArray.getColor(
                R.styleable.CustomButton_android_background, defaultBackgroundColor)
            setTextColor(
                typedArray.getColor(
                    R.styleable.CustomButton_android_textColor,defaultTextColor
                )
            )
        }
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }
}

