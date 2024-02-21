package com.example.cactus.component

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.example.cactus.R
import com.google.android.material.textfield.TextInputLayout

class TextFieldCustom@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatEditText(context, attrs, defStyleAttr)  {
    private val context: Context = context
    private val attrs: AttributeSet? = attrs

    private val strokeColor: Int = ContextCompat.getColor(context,R.color.dark_blue_900)
    init {
      init()
    }
    private fun init() {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTextFieldEdit)
        // ดึงค่า attribute ที่ต้องการจาก xml
        val boxStrokeColor = typedArray.getColor(R.styleable.CustomButtonOutline_android_strokeColor,strokeColor)
        typedArray.recycle()
        // ตั้งค่า textInputLayout
        val textInputLayout = TextInputLayout(context)
        textInputLayout.addView(this)
        textInputLayout.boxStrokeColor = boxStrokeColor
        // ตั้งค่า textInputEditText
        this.id = R.id.add_name
        this.hint = "Enter text"

        // ตั้งค่า style
        this.setTextAppearance(com.google.android.material.R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox)
    }
}