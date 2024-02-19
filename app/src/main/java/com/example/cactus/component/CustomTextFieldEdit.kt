package com.example.cactus.component

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.graphics.text.LineBreaker
import android.text.InputType
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
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
    private var textColor: Int =  ContextCompat.getColor(context, R.color.black_50)
    private var borderWidth: Float = 2f
    private var borderColor: Int = ContextCompat.getColor(context, R.color.dark_blue)
    private var hintTextColor: Int = ContextCompat.getColor(context, R.color.black_50)

    init {
        context.obtainStyledAttributes(attrs, R.styleable.CustomTextFieldEdit).use { typedArray ->
            backgroundColor = typedArray.getColor(R.styleable.CustomTextFieldEdit_android_background, backgroundColor)
            textColor = typedArray.getColor(R.styleable.CustomTextFieldEdit_android_textColor, textColor)
            borderWidth = typedArray.getFloat(R.styleable.CustomTextFieldEdit_android_strokeWidth, borderWidth)
            borderColor = typedArray.getColor(R.styleable.CustomTextFieldEdit_android_strokeColor, borderColor)
            inputType = typedArray.getInt(R.styleable.CustomTextFieldEdit_android_inputType, InputType.TYPE_CLASS_TEXT)
            hintTextColor = typedArray.getColor(R.styleable.CustomTextFieldEdit_hintTextColor, hintTextColor)
        }
        layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(0, dpToPx(0), 0, dpToPx(0))
            setWidth(dpToPx(325))
            setHeight(dpToPx(60))
            setPadding( dpToPx(8))
        }
        gravity = Gravity.CENTER
        setBorder(borderWidth, borderColor)
        setupEditText()
    }

    private fun setupEditText() {
        inputType = this.inputType
        isFocusableInTouchMode = true
        textAlignment = TEXT_ALIGNMENT_VIEW_START
        setTextSize(TypedValue.DENSITY_DEFAULT, textSize)
        setTextColor(textColor)
        setHintTextColor(hintTextColor)
        setSingleLine(false)

            
    }

    private fun setBorder(borderWidth: Float, borderColor: Int) {
        val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.cornerRadius = 10f
        shape.setSize(dpToPx(325), dpToPx(60))
        shape.setStroke(borderWidth.toInt(), borderColor)
        background = shape
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }
}
