package com.example.cactus.component

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
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

    private val defaultBackgroundColor = R.color.yellow_400
    private val defaultTextColor = R.color.dark_blue_900
    private var customBackgroundColor: Int = 0
        set(value) {
            field = value
            (background as GradientDrawable).setColor(value)
        }

    init {
        // Create GradientDrawable for button appearance
        val drawable = GradientDrawable().apply {
            setColor(ContextCompat.getColor(context, defaultBackgroundColor))
            setSize(dpToPx(280), dpToPx(60))
            cornerRadius = dpToPx(15).toFloat()
        }

        // Set layout params with margins
        layoutParams = ConstraintLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(0, dpToPx(10), 0, dpToPx(47))
        }

        gravity = Gravity.CENTER
        background = drawable
        setTextColor(ContextCompat.getColor(context, defaultTextColor))

        // Apply attributes from XML if available
        context.obtainStyledAttributes(attrs, R.styleable.CustomButton).use { typedArray ->
            customBackgroundColor = typedArray.getColor(
                R.styleable.CustomButton_android_background,
                ContextCompat.getColor(context, defaultBackgroundColor)
            )
            setTextColor(
                typedArray.getColor(
                    R.styleable.CustomButton_android_textColor,
                    ContextCompat.getColor(context, defaultTextColor)
                )
            )
        }
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }
}
