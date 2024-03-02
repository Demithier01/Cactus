package com.example.cactus.component

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import com.example.cactus.R
import com.example.cactus.databinding.WidgetTextfieldBinding
import com.google.android.material.textfield.TextInputLayout

class CustomTextFieldEdit @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextInputLayout(context, attrs, defStyleAttr) {

    private var textColor: Int = ContextCompat.getColor(context,R.color.black)
    private var hintTextColor: Int = ContextCompat.getColor(context,R.color.black_50)

    private val binding = WidgetTextfieldBinding.inflate(LayoutInflater.from(context)).also { addView(it.root) }
    private val editText = binding.editTextField

    init {
        editText.apply {
            setHintTextColor(hintTextColor)
            setTextColor(textColor)
            setPadding(dpToPx(8))
            isSingleLine = false
        }
        binding.root.layoutParams = LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        gravity = Gravity.CENTER
        initAttrs(attrs)
    }

    private fun initAttrs (attrs: AttributeSet?){
        context.obtainStyledAttributes(attrs,R.styleable.CustomTextFieldEdit).use { typedArray ->
            textColor = typedArray.getColor(R.styleable.CustomTextFieldEdit_android_textColor,textColor)
            hintTextColor = typedArray.getColor(R.styleable.CustomTextFieldEdit_android_textColorHint,hintTextColor)
            val inputType = typedArray.getInt(R.styleable.CustomTextFieldEdit_android_inputType, EditorInfo.TYPE_CLASS_TEXT)
            editText.inputType = inputType
            val fieldHint = typedArray.getString(R.styleable.CustomTextFieldEdit_android_hint)
            binding.editTextField.hint = fieldHint
        }
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }
}

