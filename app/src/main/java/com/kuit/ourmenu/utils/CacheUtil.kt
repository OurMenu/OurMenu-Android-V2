package com.kuit.ourmenu.utils

import android.content.Context
import coil3.ImageLoader
import coil3.request.ImageRequest
import com.kuit.ourmenu.ui.onboarding.state.CacheState

object CacheUtil {

    fun preloadData(
        context: Context,
        imageLoader: ImageLoader,
        cacheState: CacheState,
    ) {
        loadUrls(context, imageLoader, cacheState.menuFolderIcons)
        loadUrls(context, imageLoader, cacheState.menuPinMaps)
        loadUrls(context, imageLoader, cacheState.menuAdds)
        loadUrls(context, imageLoader, cacheState.menuPinAddDisables)
        loadUrls(context, imageLoader, cacheState.homeImgs)
        loadUrls(context, imageLoader, cacheState.orangeTags)
        loadUrls(context, imageLoader, cacheState.whiteTags)
    }

    private fun loadUrls(
        context: Context,
        imageLoader: ImageLoader,
        urls: List<String>,
    ) {
        urls.map {
            val request = ImageRequest.Builder(context)
                .data(it)
                .build()

            imageLoader.enqueue(request)
        }
    }
}