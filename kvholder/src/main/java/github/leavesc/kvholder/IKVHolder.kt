package github.leavesc.kvholder

/**
 * @Author: leavesC
 * @Date: 2021/2/21 0:07
 * @Desc:
 * @GitHub：https://github.com/leavesC
 */
interface IKVHolder {

    companion object {

        inline fun <reified T> IKVHolder.getBean(key: String): T {
            return JsonHolder.toBean(get(key, ""))
        }

        inline fun <reified T> IKVHolder.getBeanOrNull(key: String): T? {
            return JsonHolder.toBeanOrNull(get(key, ""))
        }

        inline fun <reified T> IKVHolder.getBeanOrDefault(key: String, defaultValue: T): T {
            return JsonHolder.toBeanOrDefault(get(key, ""), defaultValue)
        }

        fun toJson(ob: Any?): String {
            return JsonHolder.toJson(ob)
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