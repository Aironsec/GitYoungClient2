package ru.stplab.gityoungclient2.di.modules

import android.widget.ImageView
import dagger.Module
import dagger.Provides
import ru.stplab.gityoungclient2.mvp.model.image.IImageLoader
import ru.stplab.gityoungclient2.ui.image.GlideImageLoader

@Module
class ImageModule {

    @Provides
    fun imageLoad(): IImageLoader<ImageView> = GlideImageLoader()
}