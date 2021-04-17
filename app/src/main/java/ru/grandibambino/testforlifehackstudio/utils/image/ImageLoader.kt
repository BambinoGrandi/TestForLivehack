package ru.grandibambino.testforlifehackstudio.utils.image

import android.widget.ImageView
import androidx.constraintlayout.widget.Placeholder
import ru.grandibambino.testforlifehackstudio.R

interface ImageLoader {
    companion object{
        const val PLACEHOLDER = R.drawable.placeholder
    }

    fun loadImage(
        imageUrl: String?,
        container: ImageView,
        placeholder: Int = PLACEHOLDER
    )

}