package github.leavesc.kvholder

import com.tencent.mmkv.MMKVLogLevel

/**
 * @Author: leavesC
 * @Date: 2021/2/21 0:39
 * @Desc:
 * @GitHub：https://github.com/leavesC
 */
enum class KVLogLevel {

    LevelDebug, LevelInfo, LevelWarning, LevelError, LevelNone;

    internal fun toMMKVLogLevel(): MMKVLogLevel {
        return when (this) {
            LevelDebug -> MMKVLogLevel.LevelDebug
            LevelInfo -> MMKVLogLevel.LevelInfo
            LevelWarning -> MMKVLogLevel.LevelWarning
            LevelError -> MMKVLogLevel.LevelError
            LevelNone -> MMKVLogLevel.LevelNone
        }
    }

}