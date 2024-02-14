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
import androidx.core.graphics.toColor
import com.example.cactus.R
import com.google.android.material.button.MaterialButton

class CustomButtonOutline @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {

    private var backgroundColor: Int = ContextCompat.getColor(context, R.color.brown_50)
    private var strokeColor: Int = ContextCompat.getColor(context, R.color.yellow_400)
    private var strokeWidth: Float = 5f
    private var textColor: Int = R.color.yellow_400
    private var customBackgroundColor: Int = 0
        set(value) {
            field = value
            (background as GradientDrawable).setColor(value)
        }

    init {

        val drawable = GradientDrawable().apply {
            setColor(backgroundColor)
            setSize(dpToPx(280), dpToPx(60))
            shape = GradientDrawable.RECTANGLE
            cornerRadius = dpToPx(15).toFloat()
            setStroke(strokeWidth.toInt(),strokeColor)
            textSize = 20f
        }

        layoutParams = ConstraintLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(0, dpToPx(606), 0, dpToPx(47))
        }

        gravity = Gravity.CENTER
        background = drawable
        setTextColor(ContextCompat.getColor(context,textColor))

        context.obtainStyledAttributes(attrs, R.styleable.CustomButtonOutline).use { typedArray ->
            customBackgroundColor = typedArray.getColor(
                R.styleable.CustomButton_android_background, backgroundColor
            )
            setTextColor(
                typedArray.getColor(
                    R.styleable.CustomButton_android_textColor,
                    ContextCompat.getColor(context, textColor)
                )
            )

            strokeColor = typedArray.getColor(
                R.styleable.CustomButtonOutline_android_strokeColor, strokeColor
            )
            strokeWidth = typedArray.getFloat(
                R.styleable.CustomButtonOutline_android_strokeWidth, strokeWidth
            )
            drawable.setStroke(strokeWidth.toInt(), strokeColor)
        }
    }
    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }
}
