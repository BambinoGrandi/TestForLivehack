package ru.grandibambino.testforlifehackstudio.utils.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import ru.grandibambino.testforlifehackstudio.utils.BASE_URL

class ImageLoaderImpl : ImageLoader {
    override fun loadImage(imageUrl: String?, container: ImageView, placeholder: Int) {

        if (imageUrl != null) {
            Glide.with(container.context)
                .load(BASE_URL + imageUrl)
                .placeholder(placeholder)
                .error(placeholder)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(container)
        } else {
            Glide.with(container.context)
                .load(placeholder)
                .into(container)
        }
    }
}