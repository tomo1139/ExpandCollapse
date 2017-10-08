package develop.beta1139.expandcollapsetest

import android.view.View
import android.view.animation.Transformation
import android.view.animation.Animation



/**
 * Created by tomo on 2017/10/08.
 */
class ResizeAnimation(private var view: View, private val addHeight: Int, private var startHeight: Int) : Animation() {

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        val newHeight = (startHeight + addHeight * interpolatedTime).toInt()
        view.layoutParams.height = newHeight
        view.requestLayout()
    }

    override fun willChangeBounds(): Boolean = true
}