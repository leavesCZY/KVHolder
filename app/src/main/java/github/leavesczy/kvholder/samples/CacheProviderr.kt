package github.leavesczy.kvholder.samples

import github.leavesczy.kvholder.IKVHolder
import github.leavesczy.kvholder.MMKVKVHolder

/**
 * @Author: leavesCZY
 * @Date: 2023/12/5 14:20
 * @Desc:
 */
//设置 encryptKey 以进行加密存储
//该值可以自由定义，但设置后不能再次修改，否则数据将丢失
private val UserKVHolder = MMKVKVHolder(
    keyGroup = "user",
    encryptKey = "encryptKey"
)

private val PreferenceKVHolder = MMKVKVHolder(
    keyGroup = "preference"
)

object UserKV : IKVHolder by UserKVHolder {

    var intValue by int(defaultValue = -1)

    var longValue by long(defaultValue = -1L)

    var floatValue by float(defaultValue = -1f)

    var doubleValue by double(defaultValue = -1.0)

    var booleanValue by boolean(defaultValue = false)

    var stringValue by string(defaultValue = "")

    var parcelableValue by parcelable(
        clazz = ParcelizeModel::class.java,
        defaultValue = ParcelizeModel(
            name = "default",
            age = 0
        )
    )

    var parcelableValueNullEnabled by parcelable(clazz = ParcelizeModel::class.java)

    var jsonValue by json(
        clazz = JsonModel::class.java,
        defaultValue = JsonModel(
            name = "default",
            age = 0
        )
    )

    var jsonValueNullEnabled by json(clazz = JsonModel::class.java)

}

object PreferenceKV : IKVHolder by PreferenceKVHolder {

    var parcelableValue by parcelable(
        clazz = ParcelizeModel::class.java,
        defaultValue = ParcelizeModel(
            name = "default",
            age = 0
        )
    )

    var parcelableValueNullEnabled by parcelable(clazz = ParcelizeModel::class.java)

}