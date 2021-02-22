# KVHolder

## 导入依赖

```groovy
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

	dependencies {
	        implementation 'com.github.leavesC:KVHolder:1.0.1'
	}
```

## 如何使用

在应用启动时先进行初始化

```kotlin
	KVHolder.init(Context)
```

根据项目实际需求来定义数据范围。例如，你可以将应用内所有需要存储的键值对数据分为三类：**用户强关联数据、应用配置数据、不可二次变更的数据**。每一类数据的存储区域各不相同，互不影响。进行数据分组的好处就在于可以根据需要来清除特定数据，例如当用户退登后我们应该只清除 UserKVHolder，PreferenceKVHolder 和 FinalKVHolder 则可以一直保留

```kotlin
//和用户强绑定的数据，在退出登录时需要全部清除，例如 UserBean
//设置 encryptKey 以便加密存储
private val UserKVHolder: IKVHolder = MMKVKVHolder("user", "加密key")

//和用户不强关联的数据，在退出登录时无需清除，例如夜间模式、字体大小等
private val PreferenceKVHolder: IKVHolder = MMKVKVHolder("preference")

//用于存储不会二次变更只用于历史溯源的数据，例如应用首次安装的时间、版本号、版本名等
private val FinalKVHolder: IKVHolder = MMKVKVFinalHolder("final")
```

之后我们就可以利用 Kotlin 强大的语法特性来定义键值对了

例如，对于和用户强关联的数据，每个键值对都定义为 UserKV 的一个属性字段，键值对的含义和作用通过属性名来进行标识，且键值对的 key 必须和属性名保持一致，这样可以避免 key 值重复。每个 getValue 操作也都支持设置默认值。IKVHolder 内部通过 Gson 来实现序列化和反序列化，这样 UserKV 就可以直接存储 JavaBean、JavaBeanList，Map 等数据结构了

```kotlin
data class UserBean(val name: String, val blog: String)

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
            UserBean("叶志陈", "https://juejin.cn/user/923245496518439")
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

}

object FinalKV : IKVHolder by FinalKVHolder {

    fun init() {
        toString()
    }

    var firstInstallTime: Long
        get() = get("firstInstallTime", 0L)
        private set(value) {
            set("firstInstallTime", value)
        }
    
    init {
        firstInstallTime = System.currentTimeMillis()
    }

}
```

此外，我们也可以在 setValue 方法中对 value 进行校验，避免无效值

```kotlin
object UserKV : IKVHolder by UserKVHolder {

    var age: Int
        get() = get("age", 0)
        set(value) {
            if (value <= 0) {
                return
            }
            set("age", value)
        }

}
```

之后我们在存取值时，就相当于在直接读写 UserKV 的属性值，也支持动态指定 Key 进行赋值取值，在易用性和可读性上相比 SharedPreferences 都有很大的提升，且对于外部来说完全屏蔽了具体的存储实现逻辑

```kotlin
        //存值
        UserKV.name = "叶志陈"
        UserKV.blog = "https://juejin.cn/user/923245496518439"

        //取值
        val name = UserKV.name
        val blog = UserKV.blog

        //动态指定 Key 进行赋值和取值
        UserKV.set("name", "叶志陈")
        val name = UserKV.get("name", "")
```



更多介绍请看这里：[一文读懂 SharedPreferences 的缺陷及一点点思考](https://github.com/leavesC/KVHolder/wiki/%E4%B8%80%E6%96%87%E8%AF%BB%E6%87%82-SharedPreferences-%E7%9A%84%E7%BC%BA%E9%99%B7%E5%8F%8A%E4%B8%80%E7%82%B9%E7%82%B9%E6%80%9D%E8%80%83)
