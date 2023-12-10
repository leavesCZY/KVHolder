package github.leavesczy.kvholder

import android.content.Context
import com.tencent.mmkv.MMKV
import java.io.File

/**
 * @Author: leavesCZY
 * @Date: 2023/12/10 16:38
 * @Desc:
 */
object KVHolder {

    fun init(
        context: Context,
        rootDir: String = context.filesDir.absolutePath + "/mmkv",
        logLevel: KVLogLevel = KVLogLevel.LevelWarning
    ) {
        val file = File(rootDir)
        if (!file.exists()) {
            file.mkdirs()
        }
        MMKV.initialize(
            context.applicationContext,
            rootDir,
            logLevel.toMMKVLogLevel
        )
    }

}