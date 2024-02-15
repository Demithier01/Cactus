package com.example.cactus.component

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.text.InputType
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import com.example.cactus.R

class CustomTextFieldEdit @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatEditText(context, attrs, defStyleAttr) {

    private var backgroundColor: Int = ContextCompat.getColor(context, R.color.white)
    private var textColor: Int =  ContextCompat.getColor(context, R.color.black)
    private var borderWidth: Float = 3f
    private var borderColor: Int = ContextCompat.getColor(context, R.color.dark_blue_400)

    init {
        layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(0, dpToPx(606), 0, dpToPx(47))
            setPadding( dpToPx(10))
        }
        context.obtainStyledAttributes(attrs, R.styleable.CustomTextFieldEdit).use { typedArray ->
            backgroundColor = typedArray.getColor(R.styleable.CustomTextFieldEdit_backgroundColor, backgroundColor)
            textColor = typedArray.getColor(R.styleable.CustomTextFieldEdit_textColor, textColor)
            borderWidth = typedArray.getFloat(R.styleable.CustomTextFieldEdit_borderWidth, borderWidth)
            borderColor = typedArray.getColor(R.styleable.CustomTextFieldEdit_borderColor, borderColor)
        }
        setBorder(borderWidth, borderColor)
        setupEditText()
    }

    private fun setupEditText() {
        inputType = InputType.TYPE_CLASS_TEXT
        isFocusableInTouchMode = true
    }

    private fun setBorder(borderWidth: Float, borderColor: Int) {
        val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.cornerRadius = 10f
        shape.setSize(dpToPx(325), dpToPx(60))
        shape.setStroke(borderWidth.toInt(), borderColor)
        background = shape
//        setBackgroundColor(backgroundColor)
//        setTextColor(textColor)
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }
}
