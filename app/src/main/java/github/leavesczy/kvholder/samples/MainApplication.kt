package github.leavesczy.kvholder.samples

import android.app.Application
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
        KVHolder.init(
            context = this,
            rootDir = filesDir.absolutePath + "/mmkv",
            logLevel = KVLogLevel.LevelWarning
        )
    }

}