package github.leavesczy.kvholder

import android.os.Parcelable

/**
 * @Author: leavesCZY
 * @Date: 2023/12/10 16:38
 * @Desc:
 */
interface IKVHolder {

    fun int(defaultValue: Int): IntDelegate {
        return IntDelegate(kvHolder = this, defaultValue = defaultValue)
    }

    fun long(defaultValue: Long): LongDelegate {
        return LongDelegate(kvHolder = this, defaultValue = defaultValue)
    }

    fun float(defaultValue: Float): FloatDelegate {
        return FloatDelegate(kvHolder = this, defaultValue = defaultValue)
    }

    fun double(defaultValue: Double): DoubleDelegate {
        return DoubleDelegate(kvHolder = this, defaultValue = defaultValue)
    }

    fun boolean(defaultValue: Boolean): BooleanDelegate {
        return BooleanDelegate(kvHolder = this, defaultValue = defaultValue)
    }

    fun string(defaultValue: String): StringDelegate {
        return StringDelegate(kvHolder = this, defaultValue = defaultValue)
    }

    fun <T : Parcelable> parcelable(clazz: Class<T>, defaultValue: T): ParcelableDelegate<T> {
        return ParcelableDelegate(kvHolder = this, clazz = clazz, defaultValue = defaultValue)
    }

    fun <T : Parcelable> parcelable(clazz: Class<T>): ParcelableNullEnabledDelegate<T> {
        return ParcelableNullEnabledDelegate(kvHolder = this, clazz = clazz)
    }

    fun <T : Any> json(clazz: Class<T>, defaultValue: T): JsonDelegate<T> {
        return JsonDelegate(kvHolder = this, clazz = clazz, defaultValue = defaultValue)
    }

    fun <T : Any> json(clazz: Class<T>): JsonNullEnabledDelegate<T> {
        return JsonNullEnabledDelegate(kvHolder = this, clazz = clazz)
    }

    fun get(key: String, defaultValue: Int): Int

    fun get(key: String, defaultValue: Long): Long

    fun get(key: String, defaultValue: Float): Float

    fun get(key: String, defaultValue: Double): Double

    fun get(key: String, defaultValue: Boolean): Boolean

    fun get(key: String, defaultValue: String): String

    fun <T : Parcelable> get(key: String, clazz: Class<T>): T?

    fun set(key: String, value: Int)

    fun set(key: String, value: Long)

    fun set(key: String, value: Float)

    fun set(key: String, value: Double)

    fun set(key: String, value: Boolean)

    fun set(key: String, value: String)

    fun <T : Parcelable> set(key: String, value: T?)

    fun removeKey(vararg keys: String)

    fun clear()

}