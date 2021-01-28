package io.devnido.customstepper

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat


class CustomStepperLinearLayout(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    var totalSteps = 2
    var currentStep = 1

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
        initParams(attrs)
        generate()
    }


    private fun initParams(attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(attrs, R.styleable.CustomStepper, 0, 0).apply {
            try {
                totalSteps = getInteger(R.styleable.CustomStepper_totalSteps, totalSteps)
                currentStep = getInteger(R.styleable.CustomStepper_currentStep, currentStep)
            }finally {
                recycle()
            }
        }

    }



    private fun generate(){

        // falta la logica que genera los elementos a partir de los atributos entregados
        for(i in 1..totalSteps){

        }

        addView(getCircle(true))
        addView(getBar(false))
        addView(getCircle(false))
        addView(getBar(false))
        addView(getCircle(false))

    }

    private fun getCircle(isSelected: Boolean) = View(context).apply {
        background = ContextCompat.getDrawable(context, R.drawable.stepper_circle)
        val size = (7f * context.resources.displayMetrics.density).toInt()
        layoutParams = LayoutParams(size, size)
        if(isSelected)  backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.purple_700))
    }

   private fun getBar(isSelected: Boolean) = View(context).apply {
       background = ContextCompat.getDrawable(context, R.drawable.stepper_bar)
       val height = (2f * context.resources.displayMetrics.density).toInt()
       val width = (62f * context.resources.displayMetrics.density).toInt()
       layoutParams = LayoutParams(width, height)

       val margin = 1.5f
       (layoutParams as LayoutParams).setMargins((margin * context.resources.displayMetrics.density).toInt(), 0, (margin * context.resources.displayMetrics.density).toInt(), 0)
       if(isSelected)  backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.purple_700))
   }
}