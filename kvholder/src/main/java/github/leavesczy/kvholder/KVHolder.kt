package github.leavesczy.kvholder

import android.content.Context
import com.tencent.mmkv.MMKV

/**
 * @Author: leavesCZY
 * @Date: 2021/2/21 0:18
 * @Desc:
 * @GitHub：https://github.com/leavesCZY
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