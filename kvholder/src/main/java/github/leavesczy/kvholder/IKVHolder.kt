package github.leavesczy.kvholder

import com.google.gson.Gson

/**
 * @Author: leavesCZY
 * @Date: 2021/2/21 0:07
 * @Desc:
 * @GitHub：https://github.com/leavesCZY
 */
interface IKVHolder {

    companion object {

        val gson = Gson()

        fun toJson(ob: Any?): String {
            try {
                return gson.toJson(ob)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
            return ""
        }

        inline fun <reified T> toBean(json: String?): T {
            return gson.fromJson(json, T::class.java)
        }

        inline fun <reified T> IKVHolder.getBean(key: String): T {
            return toBean(json = get(key = key, default = ""))
        }

        inline fun <reified T> IKVHolder.getBeanOrNull(key: String): T? {
            try {
                return getBean(key = key)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
            return null
        }

        inline fun <reified T> IKVHolder.getBeanOrDefault(key: String, defaultValue: T): T {
            return getBeanOrNull(key = key) ?: defaultValue
        }

    }

    //数据分组，用于标明不同范围内的数据缓存
    val keyGroup: String

    fun verifyBeforePut(key: String, value: Any?): Boolean

    fun get(key: String, default: Int): Int

    fun get(key: String, default: Long): Long

    fun get(key: String, default: Float): Float

    fun get(key: String, default: Double): Double

    fun get(key: String, default: Boolean): Boolean

    fun get(key: String, default: String): String

    fun set(key: String, value: Int)

    fun set(key: String, value: Long)

    fun set(key: String, value: Float)

    fun set(key: String, value: Double)

    fun set(key: String, value: Boolean)

    fun set(key: String, value: String)

    fun <T> set(key: String, value: T?)

    fun containsKey(key: String): Boolean

    fun removeKey(vararg keys: String)

    fun allKeyValue(): Map<String, Any?>

    fun clear()

}