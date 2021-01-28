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


class CustomStepper(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    var totalSteps = 2
    var currentStep = 1

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
        initParams(attrs)
        drawCanvas()
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

    private fun drawCanvas(){
        val borderPaint = Paint()
        borderPaint.style = Paint.Style.FILL
        borderPaint.color = Color.CYAN
        borderPaint.isAntiAlias = true
        borderPaint.isDither = true

        val fillPaint = Paint()
        fillPaint.style = Paint.Style.FILL
        fillPaint.color = Color.GREEN
        fillPaint.isAntiAlias = true
        fillPaint.isDither = true

        val canvas = Canvas()
        canvas.drawCircle(0f, 0f, 5f, fillPaint)


    }

    private fun generate(){

        for(i in 1..totalSteps){

        }

        val circle = ContextCompat.getDrawable(context, R.drawable.stepper_circle)
        val bar = ContextCompat.getDrawable(context, R.drawable.stepper_bar)

        fun Int.toPx() = (this * context.resources.displayMetrics.density).toInt()

        val verticalInset: Int = (((circle?.intrinsicHeight ?: 0) - (bar?.intrinsicHeight ?: 0)) / 2)

        val finalDrawable = LayerDrawable(arrayOf(circle, bar))
        finalDrawable.setLayerInset(0, 20, 20, (bar?.intrinsicWidth ?: 0)+60,20 )
        finalDrawable.setLayerInset(1,60,verticalInset+20,0, verticalInset+20)

        background = finalDrawable
    }

    private fun getCircle(isSelected: Boolean) = View(context).apply {
        background = ContextCompat.getDrawable(context, R.drawable.stepper_circle)
        val size = (11f * context.resources.displayMetrics.density).toInt()
        layoutParams = LayoutParams(size, size)
        if(isSelected)  backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.purple_700))
    }

   private fun getBar(isSelected: Boolean) = View(context).apply {
       background = ContextCompat.getDrawable(context, R.drawable.stepper_bar)
       val height = (3f * context.resources.displayMetrics.density).toInt()
       val width = (50f * context.resources.displayMetrics.density).toInt()
       layoutParams = LayoutParams(width, height)

       val margin = 1.5f
       (layoutParams as LayoutParams).setMargins((margin * context.resources.displayMetrics.density).toInt(), 0, (margin * context.resources.displayMetrics.density).toInt(), 0)
       if(isSelected)  backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.purple_700))
   }
}