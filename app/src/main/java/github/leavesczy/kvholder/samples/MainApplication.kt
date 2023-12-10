package github.leavesczy.kvholder.samples

import android.app.Application
import android.content.Context
import github.leavesczy.kvholder.KVHolder
import github.leavesczy.kvholder.KVLogLevel

/**
 * @Author: leavesCZY
 * @Date: 2023/12/5 14:20
 * @Desc:
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKVHolder(context = this)
    }

    private fun initKVHolder(context: Context) {
        KVHolder.init(
            context = context,
            rootDir = context.filesDir.absolutePath + "/mmkv",
            logLevel = KVLogLevel.LevelWarning
        )
    }

}