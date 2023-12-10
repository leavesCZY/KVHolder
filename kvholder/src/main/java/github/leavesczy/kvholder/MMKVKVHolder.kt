package github.leavesczy.kvholder

import android.os.Parcelable
import com.tencent.mmkv.MMKV

/**
 * @Author: leavesCZY
 * @Date: 2023/12/10 16:38
 * @Desc:
 */
/**
 * @param keyGroup 用于指定数据分组，不同分组下的数据互不关联
 * @param encryptKey 加密 key，如果为空则表示不进行加密
 */
class MMKVKVHolder(
    private val keyGroup: String,
    encryptKey: String? = null
) : IKVHolder {

    private val kv: MMKV = if (encryptKey.isNullOrBlank()) {
        MMKV.mmkvWithID(
            keyGroup,
            MMKV.MULTI_PROCESS_MODE
        )
    } else {
        MMKV.mmkvWithID(
            keyGroup,
            MMKV.MULTI_PROCESS_MODE,
            encryptKey
        )
    }

    override fun get(key: String, defaultValue: Int): Int {
        return kv.decodeInt(key, defaultValue)
    }

    override fun get(key: String, defaultValue: Long): Long {
        return kv.decodeLong(key, defaultValue)
    }

    override fun get(key: String, defaultValue: Float): Float {
        return kv.decodeFloat(key, defaultValue)
    }

    override fun get(key: String, defaultValue: Double): Double {
        return kv.decodeDouble(key, defaultValue)
    }

    override fun get(key: String, defaultValue: Boolean): Boolean {
        return kv.decodeBool(key, defaultValue)
    }

    override fun get(key: String, defaultValue: String): String {
        return kv.decodeString(key, defaultValue) ?: defaultValue
    }

    override fun <T : Parcelable> get(key: String, clazz: Class<T>): T? {
        return kv.decodeParcelable(key, clazz)
    }

    override fun set(key: String, value: Int) {
        kv.encode(key, value)
    }

    override fun set(key: String, value: Long) {
        kv.encode(key, value)
    }

    override fun set(key: String, value: Float) {
        kv.encode(key, value)
    }

    override fun set(key: String, value: Double) {
        kv.encode(key, value)
    }

    override fun set(key: String, value: Boolean) {
        kv.encode(key, value)
    }

    override fun set(key: String, value: String) {
        kv.encode(key, value)
    }

    override fun <T : Parcelable> set(key: String, value: T?) {
        kv.encode(key, value)
    }

    override fun removeKey(vararg keys: String) {
        kv.removeValuesForKeys(keys)
    }

    override fun clear() {
        kv.clearAll()
    }

}