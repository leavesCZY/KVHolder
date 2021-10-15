package github.leavesc.kvholder

import android.content.Context
import com.tencent.mmkv.MMKV

/**
 * @Author: leavesC
 * @Date: 2021/2/21 0:18
 * @Desc:
 * @GitHub：https://github.com/leavesC
 */
object KVHolder {

    fun init(
        context: Context,
        rootDir: String = context.filesDir.absolutePath + "/mmkv",
        logLevel: KVLogLevel = KVLogLevel.LevelWarning
    ) {
        MMKV.initialize(
            context.applicationContext,
            rootDir,
            logLevel.toMMKVLogLevel()
        )
    }

}