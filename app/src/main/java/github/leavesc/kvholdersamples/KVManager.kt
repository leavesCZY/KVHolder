package github.leavesc.kvholdersamples

import github.leavesc.kvholder.IKVHolder
import github.leavesc.kvholder.IKVHolder.Companion.getBean
import github.leavesc.kvholder.IKVHolder.Companion.getBeanOrDefault
import github.leavesc.kvholder.IKVHolder.Companion.getBeanOrNull
import github.leavesc.kvholder.MMKVKVFinalHolder
import github.leavesc.kvholder.MMKVKVHolder

/**
 * @Author: leavesC
 * @Date: 2021/2/21 0:22
 * @Desc:
 * @GitHub：https://github.com/leavesC
 */
//和用户强绑定的数据，在退出登录时需要全部清除，例如 UserId
//设置 encryptKey 以便加密存储
private val UserKVHolder: IKVHolder =
    MMKVKVHolder(keyGroup = "user", encryptKey = "encryptKeyKeyKey")

//和用户不强关联的数据，在退出登录时无需清除，例如夜间模式、字体大小等
private val PreferenceKVHolder: IKVHolder =
    MMKVKVHolder(keyGroup = "preference")

//用于存储不会二次变更只用于历史溯源的数据，例如应用首次安装的时间、版本号、版本名等
private val FinalKVHolder: IKVHolder =
    MMKVKVFinalHolder(keyGroup = "final")

object UserKV : IKVHolder by UserKVHolder {

    var name: String
        get() = get("name", "")
        set(value) = set("name", value)

    var blog: String
        get() = get("blog", "")
        set(value) = set("blog", value)

    var userBean: UserBean?
        get() = getBeanOrNull("userBean")
        set(value) = set("userBean", value)

    var userBeanOfDefault: UserBean
        get() = getBeanOrDefault(
            "userBeanOfDefault",
            UserBean("业志陈", "https://juejin.cn/user/923245496518439")
        )
        set(value) = set("userBeanOfDefault", value)

    var userBeanList: List<UserBean>
        get() = getBean("userBeanList")
        set(value) = set("userBeanList", value)

    var map: Map<Int, String>
        get() = getBean("map")
        set(value) = set("map", value)

}

object PreferenceKV : IKVHolder by PreferenceKVHolder {

    var appTheme: String
        get() = get("appTheme", "day")
        set(value) = set("appTheme", value)

    var fontSize: Int
        get() = get("fontSize", 20)
        set(value) = set("fontSize", value)

}

object FinalKV : IKVHolder by FinalKVHolder {

    //由于 object 需要等到外部主动调用了它才开始进行初始化
    //所以在应用启动时需要主动调用此方法
    fun init() {
        toString()
    }

    var firstInstallTime: Long
        get() = get("firstInstallTime", 0L)
        private set(value) {
            set("firstInstallTime", value)
        }

    var firstVersionCode: Int
        get() = get("firstVersionCode", 0)
        private set(value) = set("firstVersionCode", value)

    var firstVersionName: String
        get() = get("firstVersionName", "")
        private set(value) = set("firstVersionName", value)

    init {
        firstInstallTime = System.currentTimeMillis()
        firstVersionCode = BuildConfig.VERSION_CODE
        firstVersionName = BuildConfig.VERSION_NAME
    }

    override fun toString(): String {
        return "firstInstallTime: $firstInstallTime firstVersionCode: $firstVersionCode firstVersionName: $firstVersionName"
    }

}