package com.example.cactus.component

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.text.LineBreaker
import android.text.InputType
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatEditText
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.core.view.size
import com.example.cactus.R
import com.example.cactus.ui.addData.AddDataFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.collection.LLRBNode

class CustomTextFieldEdit @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextInputLayout(context, attrs, defStyleAttr) {

    private var backgroundColor: Int =  ContextCompat.getColor(context, R.color.dark_blue_900)
    private var textColor: Int =  ContextCompat.getColor(context, R.color.black_50)
    private var hintTextColor: Int = ContextCompat.getColor(context, R.color.black_50)

    init {
        boxBackgroundMode = BOX_BACKGROUND_OUTLINE
        val editText = TextInputEditText(context)
        addView(editText)
        context.obtainStyledAttributes(attrs, R.styleable.CustomTextFieldEdit).use { typedArray ->
            backgroundColor = typedArray.getColor(R.styleable.CustomTextFieldEdit_android_background,backgroundColor)
            textColor = typedArray.getColor(R.styleable.CustomTextFieldEdit_android_textColor, textColor)
            hintTextColor = typedArray.getColor(R.styleable.CustomTextFieldEdit_hintTextColor, hintTextColor)
            val inputType = typedArray.getInt(R.styleable.CustomTextFieldEdit_android_inputType, EditorInfo.TYPE_CLASS_TEXT)
            editText.inputType = inputType
        }
        editText.apply {
            setTextColor(textColor)
            setHintTextColor(hintTextColor)
            setSingleLine(false)
            setPadding(dpToPx(8))
            background = getBackground(dpToPx(10))
        }
        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        gravity = Gravity.CENTER

    }
   private fun getBackground(radius: Int): Drawable {
    val background = GradientDrawable()
    background.apply {
        shape = GradientDrawable.RECTANGLE
        cornerRadius = radius.toFloat()
        setSize(dpToPx(325), dpToPx(60))
        setStroke(dpToPx(1), backgroundColor)
    }
    return background
}

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }
}
