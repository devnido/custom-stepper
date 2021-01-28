package io.devnido.customstepper

import android.content.Context
import android.graphics.drawable.LayerDrawable
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

@RequiresApi(Build.VERSION_CODES.M)
class CustomStepperView (context: Context?, attrs: AttributeSet?) : View(context, attrs){

    var totalSteps = 2
    var currentStep = 1

    init {
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


    @RequiresApi(Build.VERSION_CODES.M)
    private fun generate(){

        // falta la logica que genera los elementos a partir de los atributos entregados
        for(i in 1..totalSteps){

        }

        val circle = ContextCompat.getDrawable(context, R.drawable.stepper_circle)
        val bar = ContextCompat.getDrawable(context, R.drawable.stepper_bar)

        fun Int.toPx() = (this * context.resources.displayMetrics.density).toInt()

        val verticalInset: Int = (((circle?.intrinsicHeight ?: 0) - (bar?.intrinsicHeight ?: 0)) / 2)

        val finalDrawable = LayerDrawable(arrayOf(circle, bar))
        finalDrawable.setLayerInset(0, 0, 0, (bar?.intrinsicWidth ?: 0)+60, 0)
        finalDrawable.setLayerSize(0,(circle?.intrinsicWidth ?: 0),(circle?.intrinsicHeight ?: 0))
        finalDrawable.setLayerInset(1,60,verticalInset,0, verticalInset)
        finalDrawable.setLayerSize(1,(bar?.intrinsicWidth ?: 0),(bar?.intrinsicHeight ?: 0))

        background = finalDrawable
    }
}