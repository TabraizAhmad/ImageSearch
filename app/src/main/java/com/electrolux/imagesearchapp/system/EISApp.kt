package com.electrolux.imagesearchapp.system

import android.app.Application
import com.electrolux.imagesearchapp.network.remote.DefaultIntercepter
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class EISApp:Application() {


}