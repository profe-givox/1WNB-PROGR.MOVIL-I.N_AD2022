package net.ivanvega.misrecyclersviews

import android.app.Application
import net.ivanvega.misrecyclersviews.data.DataSource
import net.ivanvega.misrecyclersviews.data.listFlowers

class MyAppication : Application() {
    override fun onCreate() {
        super.onCreate()
        DataSource.lsFlower.addAll(
            listFlowers(resources)
        )
    }

}