package github.leavesc.kvholder

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * @Author: leavesC
 * @Date: 2021/2/21 0:05
 * @Desc:
 * @GitHub：https://github.com/leavesC
 */
object JsonHolder {

    private val gson = Gson()

    fun <T> toBean(json: String?, clazz: Class<T>): T {
        return gson.fromJson(json, clazz)
    }

    fun <T> toBean(json: String?, type: Type): T {
        return gson.fromJson(json, type)
    }

    /**
     * 用于反序列化 JavaBean、List<JavaBean>、Map 等各种对象
     * @param json Json
     * @sample JavaBean:     JsonHolder.toBean<XXXBean>(json)
     *         JavaBeanList: JsonHolder.toBean<List<XXXBean>>(listJson)
     *         Map:          JsonHolder.toBean<Map<String, Int>>(mapJson)
     */
    inline fun <reified T> toBean(json: String?): T {
        return toBean(json, type<T>())
    }

    inline fun <reified T> toBeanOrNull(json: String?): T? {
        try {
            return toBean<T>(json)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return null
    }

    inline fun <reified T> toBeanOrDefault(json: String?, defaultValue: T): T {
        try {
            return toBean(json) ?: defaultValue
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return defaultValue
    }

    fun toJson(ob: Any?): String {
        try {
            return gson.toJson(ob)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return ""
    }

    inline fun <reified T> type(): Type {
        return object : TypeToken<T>() {}.type
    }

}