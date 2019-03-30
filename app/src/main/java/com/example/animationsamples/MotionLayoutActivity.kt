package com.example.animationsamples

import android.os.Build
import android.os.Bundle
import android.transition.TransitionManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.motion_layout_1.*

class MotionLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.motion_layout_1)
        addAnimationOperations()

    }

    private fun addAnimationOperations() {
        var set = false
        val constraint1 = ConstraintSet()
        constraint1.clone(root)
        val constraint2 = ConstraintSet()
        constraint2.clone(this, R.layout.motion_layout_2)

        findViewById<TextView>(R.id.playAnimationButton).setOnClickListener{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                TransitionManager.beginDelayedTransition(root)
                val constraint = if(set) constraint1 else constraint2
                constraint.applyTo(root)
                set = !set
            }
        }

    }

}
