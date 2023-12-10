package github.leavesczy.kvholder

import com.tencent.mmkv.MMKVLogLevel

/**
 * @Author: leavesCZY
 * @Date: 2023/12/10 16:38
 * @Desc:
 */
enum class KVLogLevel {

    LevelDebug,
    LevelInfo,
    LevelWarning,
    LevelError,
    LevelNone;

    internal val toMMKVLogLevel: MMKVLogLevel
        get() {
            return when (this) {
                LevelDebug -> MMKVLogLevel.LevelDebug
                LevelInfo -> MMKVLogLevel.LevelInfo
                LevelWarning -> MMKVLogLevel.LevelWarning
                LevelError -> MMKVLogLevel.LevelError
                LevelNone -> MMKVLogLevel.LevelNone
            }
        }

}