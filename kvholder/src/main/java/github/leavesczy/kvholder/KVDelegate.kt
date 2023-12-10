package github.leavesczy.kvholder

import android.os.Parcelable
import com.google.gson.Gson
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @Author: leavesCZY
 * @Date: 2023/12/5 14:20
 * @Desc:
 */
abstract class KVDelegate<T : Any?>(
    protected val kvHolder: IKVHolder,
    protected val defaultValue: T
) : ReadWriteProperty<IKVHolder, T> {

    override fun getValue(thisRef: IKVHolder, property: KProperty<*>): T {
        return getValue(key = buildKey(property = property), defaultValue = defaultValue)
    }

    override fun setValue(thisRef: IKVHolder, property: KProperty<*>, value: T) {
        setValue(key = buildKey(property = property), value = value)
    }

    abstract fun getValue(key: String, defaultValue: T): T

    abstract fun setValue(key: String, value: T)

    private fun buildKey(property: KProperty<*>): String {
        return property.name
    }

}

abstract class KVNullEnabledDelegate<T>(kvHolder: IKVHolder) :
    KVDelegate<T?>(kvHolder = kvHolder, defaultValue = null)

class IntDelegate(kvHolder: IKVHolder, defaultValue: Int) :
    KVDelegate<Int>(kvHolder = kvHolder, defaultValue = defaultValue) {

    override fun getValue(key: String, defaultValue: Int): Int {
        return kvHolder.get(key = key, defaultValue = defaultValue)
    }

    override fun setValue(key: String, value: Int) {
        kvHolder.set(key = key, value = value)
    }

}

class LongDelegate(kvHolder: IKVHolder, defaultValue: Long) :
    KVDelegate<Long>(kvHolder = kvHolder, defaultValue = defaultValue) {

    override fun getValue(key: String, defaultValue: Long): Long {
        return kvHolder.get(key = key, defaultValue = defaultValue)
    }

    override fun setValue(key: String, value: Long) {
        kvHolder.set(key = key, value = value)
    }

}

class FloatDelegate(kvHolder: IKVHolder, defaultValue: Float) :
    KVDelegate<Float>(kvHolder = kvHolder, defaultValue = defaultValue) {

    override fun getValue(key: String, defaultValue: Float): Float {
        return kvHolder.get(key = key, defaultValue = defaultValue)
    }

    override fun setValue(key: String, value: Float) {
        kvHolder.set(key = key, value = value)
    }

}

class DoubleDelegate(kvHolder: IKVHolder, defaultValue: Double) :
    KVDelegate<Double>(kvHolder = kvHolder, defaultValue = defaultValue) {

    override fun getValue(key: String, defaultValue: Double): Double {
        return kvHolder.get(key = key, defaultValue = defaultValue)
    }

    override fun setValue(key: String, value: Double) {
        kvHolder.set(key = key, value = value)
    }

}

class BooleanDelegate(kvHolder: IKVHolder, defaultValue: Boolean) :
    KVDelegate<Boolean>(kvHolder = kvHolder, defaultValue = defaultValue) {

    override fun getValue(key: String, defaultValue: Boolean): Boolean {
        return kvHolder.get(key = key, defaultValue = defaultValue)
    }

    override fun setValue(key: String, value: Boolean) {
        kvHolder.set(key = key, value = value)
    }

}

class StringDelegate(kvHolder: IKVHolder, defaultValue: String) :
    KVDelegate<String>(kvHolder = kvHolder, defaultValue = defaultValue) {

    override fun getValue(key: String, defaultValue: String): String {
        return kvHolder.get(key = key, defaultValue = defaultValue)
    }

    override fun setValue(key: String, value: String) {
        kvHolder.set(key = key, value = value)
    }

}

class ParcelableDelegate<T : Parcelable>(
    kvHolder: IKVHolder,
    private val clazz: Class<T>,
    defaultValue: T
) : KVDelegate<T>(kvHolder = kvHolder, defaultValue = defaultValue) {

    override fun getValue(key: String, defaultValue: T): T {
        return kvHolder.get(key = key, clazz = clazz) ?: defaultValue
    }

    override fun setValue(key: String, value: T) {
        kvHolder.set(key = key, value = value)
    }

}

class ParcelableNullEnabledDelegate<T : Parcelable>(
    kvHolder: IKVHolder,
    private val clazz: Class<T>
) : KVNullEnabledDelegate<T>(kvHolder = kvHolder) {

    override fun getValue(key: String, defaultValue: T?): T? {
        return kvHolder.get(key = key, clazz = clazz)
    }

    override fun setValue(key: String, value: T?) {
        kvHolder.set(key = key, value = value)
    }

}

class JsonDelegate<T>(
    kvHolder: IKVHolder,
    private val clazz: Class<T>,
    defaultValue: T
) : KVDelegate<T>(kvHolder = kvHolder, defaultValue = defaultValue) {

    override fun getValue(key: String, defaultValue: T): T {
        val json = kvHolder.get(key = key, defaultValue = "")
        if (json.isBlank()) {
            return defaultValue
        }
        return try {
            Gson().fromJson(json, clazz)
        } catch (e: Throwable) {
            null
        } ?: defaultValue
    }

    override fun setValue(key: String, value: T) {
        if (value == null) {
            kvHolder.removeKey(key)
        } else {
            val json = Gson().toJson(value)
            kvHolder.set(key = key, value = json)
        }
    }

}

class JsonNullEnabledDelegate<T>(
    kvHolder: IKVHolder,
    private val clazz: Class<T>
) : KVNullEnabledDelegate<T>(kvHolder = kvHolder) {

    override fun getValue(key: String, defaultValue: T?): T? {
        val json = kvHolder.get(key = key, defaultValue = "")
        if (json.isBlank()) {
            return defaultValue
        }
        return try {
            Gson().fromJson(json, clazz)
        } catch (e: Throwable) {
            null
        } ?: defaultValue
    }

    override fun setValue(key: String, value: T?) {
        if (value == null) {
            kvHolder.removeKey(key)
        } else {
            val json = Gson().toJson(value)
            kvHolder.set(key = key, value = json)
        }
    }

}