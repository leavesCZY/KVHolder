package github.leavesc.kvholder

import android.text.TextUtils
import com.tencent.mmkv.MMKV
import github.leavesc.kvholder.IKVHolder.Companion.toJson

/**
 * @Author: leavesC
 * @Date: 2021/2/21 0:09
 * @Desc:
 * @GitHub：https://github.com/leavesC
 */
/**
 * @param selfGroup 用于指定数据分组，不同分组下的数据互不关联
 * @param encryptKey 加密 key，如果为空则表示不进行加密
 */
sealed class BaseMMKVKVHolder constructor(
    selfGroup: String,
    encryptKey: String
) : IKVHolder {

    final override val keyGroup: String = selfGroup

    override fun verifyBeforePut(key: String, value: Any?): Boolean {
        return true
    }

    private val kv: MMKV? = if (encryptKey.isBlank()) MMKV.mmkvWithID(
        keyGroup,
        MMKV.MULTI_PROCESS_MODE
    ) else MMKV.mmkvWithID(keyGroup, MMKV.MULTI_PROCESS_MODE, encryptKey)

    override fun get(key: String, default: Int): Int {
        return kv?.getInt(key, default) ?: default
    }

    override fun get(key: String, default: Long): Long {
        return kv?.getLong(key, default) ?: default
    }

    override fun get(key: String, default: Float): Float {
        return kv?.getFloat(key, default) ?: default
    }

    override fun get(key: String, default: Double): Double {
        return kv?.getString(key, "")?.toDoubleOrNull() ?: default
    }

    override fun get(key: String, default: Boolean): Boolean {
        return kv?.getBoolean(key, default) ?: default
    }

    override fun get(key: String, default: String): String {
        return kv?.getString(key, default) ?: default
    }

    override fun set(key: String, value: Int) {
        if (verifyBeforePut(key, value)) {
            kv?.putInt(key, value)
        }
    }

    override fun set(key: String, value: Long) {
        if (verifyBeforePut(key, value)) {
            kv?.putLong(key, value)
        }
    }

    override fun set(key: String, value: Float) {
        if (verifyBeforePut(key, value)) {
            kv?.putFloat(key, value)
        }
    }

    override fun set(key: String, value: Double) {
        if (verifyBeforePut(key, value)) {
            kv?.putString(key, value.toString())
        }
    }

    override fun set(key: String, value: Boolean) {
        if (verifyBeforePut(key, value)) {
            kv?.putBoolean(key, value)
        }
    }

    override fun set(key: String, value: String) {
        if (verifyBeforePut(key, value)) {
            kv?.putString(key, value)
        }
    }

    override fun <T> set(key: String, value: T?) {
        if (verifyBeforePut(key, value)) {
            if (value == null) {
                removeKey(key)
            } else {
                set(key, toJson(value))
            }
        }
    }

    override fun containsKey(key: String): Boolean {
        return kv?.containsKey(key) ?: false
    }

    override fun removeKey(vararg keys: String) {
        kv?.removeValuesForKeys(keys)
    }

    override fun allKeyValue(): Map<String, Any?> {
        val map = mutableMapOf<String, Any?>()
        kv?.allKeys()?.forEach {
            map[it] = getObjectValue(kv, it)
        }
        return map
    }

    override fun clear() {
        kv?.clearAll()
    }

    private fun getObjectValue(
        mmkv: MMKV,
        key: String
    ): Any? { // 因为其他基础类型value会读成空字符串,所以不是空字符串即为string or string-set类型
        try {
            val value = mmkv.decodeString(key)
            if (!TextUtils.isEmpty(value)) { // 判断 string or string-set
                return if (value?.getOrNull(0)?.toInt() == 0x01) {
                    mmkv.decodeStringSet(key)
                } else {
                    value
                }
            }
            // float double类型可通过string-set配合判断
            // 通过数据分析可以看到类型为float或double时string类型为空字符串且string-set类型读出空数组
            // 最后判断float为0或NAN的时候可以直接读成double类型,否则读float类型
            // 该判断方法对于非常小的double类型数据 (0d < value <= 1.0569021313E-314) 不生效
            val set = mmkv.decodeStringSet(key)
            if (set != null && set.size == 0) {
                val valueFloat = mmkv.decodeFloat(key)
                val valueDouble = mmkv.decodeDouble(key)
                return if (valueFloat.compareTo(0f) == 0 || valueFloat.compareTo(Float.NaN) == 0) {
                    valueDouble
                } else {
                    valueFloat
                }
            }
            // int long bool 类型的处理放在一起, int类型1和0等价于bool类型true和false
            // 判断long或int类型时, 如果数据长度超出int的最大长度, 则long与int读出的数据不等, 可确定为long类型
            val valueInt = mmkv.decodeInt(key)
            val valueLong = mmkv.decodeLong(key)
            return if (valueInt.toLong() != valueLong) {
                valueLong
            } else {
                valueInt
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return "failed get"
    }

}

/**
 * @param selfGroup 用于指定数据分组，不同分组下的数据互不关联
 * @param encryptKey 加密 key，如果为空则表示不进行加密
 */
class MMKVKVHolder constructor(selfGroup: String, encryptKey: String = "") :
    BaseMMKVKVHolder(selfGroup, encryptKey)

/**
 * 存储后值无法二次变更
 * @param selfGroup 用于指定数据分组，不同分组下的数据互不关联
 * @param encryptKey 加密 key，如果为空则表示不进行加密
 */
class MMKVKVFinalHolder constructor(selfGroup: String, encryptKey: String = "") :
    BaseMMKVKVHolder(selfGroup, encryptKey) {

    override fun verifyBeforePut(key: String, value: Any?): Boolean {
        return !containsKey(key)
    }

}