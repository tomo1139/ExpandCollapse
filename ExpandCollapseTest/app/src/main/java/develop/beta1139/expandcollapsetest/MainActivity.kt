package develop.beta1139.expandcollapsetest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.widget.LinearLayout
import android.view.ViewTreeObserver



class MainActivity : AppCompatActivity() {

    private lateinit var container: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById(R.id.expand).setOnClickListener {
            expand(findViewById(R.id.container))
        }
        findViewById(R.id.collapse).setOnClickListener {
            collapse(findViewById(R.id.container))
        }

        container = findViewById(R.id.container) as LinearLayout

        container.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val params = findViewById(R.id.container).layoutParams
                params.height = getInitialHeight()
                container.layoutParams = params
                container.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    private fun getInitialHeight(): Int = findViewById(R.id.text0).height + findViewById(R.id.text1).height + findViewById(R.id.text2).height

    private fun expand(v: View) {
        v.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val maxHeight = v.measuredHeight
        val initialHeight = getInitialHeight()

        val animation = ResizeAnimation(v, maxHeight - initialHeight, initialHeight)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) { }
            override fun onAnimationEnd(animation: Animation) { }
            override fun onAnimationRepeat(animation: Animation) {}
        })
        animation.duration = 200
        v.startAnimation(animation)
    }

    private fun collapse(v: View) {
        v.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val maxHeight = v.measuredHeight
        val initialHeight = getInitialHeight()

        val animation = ResizeAnimation(v, initialHeight - maxHeight, maxHeight)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) { }
            override fun onAnimationEnd(animation: Animation) { }
            override fun onAnimationRepeat(animation: Animation) { }
        })
        animation.duration = 200
        v.startAnimation(animation)
    }
}
