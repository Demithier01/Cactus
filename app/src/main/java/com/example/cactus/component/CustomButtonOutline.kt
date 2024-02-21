package com.example.cactus.component

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.cactus.R

class CustomButtonOutline @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {
    private var strokeColor: Int = ContextCompat.getColor(context, R.color.yellow_400)
    private var strokeWidth: Float = 5f
    private var textColor = ContextCompat.getColor(context,R.color.yellow_400)
    init {
        val drawable = GradientDrawable().apply {
            setSize(dpToPx(280), dpToPx(60))
            shape = GradientDrawable.RECTANGLE
            cornerRadius = dpToPx(15).toFloat()
            setStroke(strokeWidth.toInt(),strokeColor)
        }

        layoutParams = ConstraintLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(0, dpToPx(606), 0, dpToPx(47))
        }
        gravity = Gravity.CENTER
        background = drawable
        setTextSize(TypedValue.DENSITY_DEFAULT,textSize)
        setTextColor(textColor)

        context.obtainStyledAttributes(attrs, R.styleable.CustomButtonOutline).use { typedArray ->
            textColor = typedArray.getColor(R.styleable.CustomButtonOutline_android_textColor, textColor)
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
