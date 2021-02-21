package github.leavesc.kvholdersamples

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

/**
 * @Author: leavesC
 * @Date: 2021/2/21 0:22
 * @Desc:
 * @GitHub：https://github.com/leavesC
 */
class MainActivity : AppCompatActivity() {

    private fun log(log: String) {
        Log.e("TAG", log)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_setUserGroup.setOnClickListener {
            UserKV.name = Random.nextInt(1, 300).toString()
            UserKV.blog = Random.nextInt(1, 300).toString()
            UserKV.userBean = UserBean("leavesC", "https://juejin.cn/user/923245496518439")
            UserKV.userBeanList = listOf(
                UserBean("叶志陈", "https://juejin.cn/user/923245496518439"),
                UserBean("公众号", "字节数组"),
                UserBean("GitHub", "https://github.com/leavesC")
            )
            UserKV.map = hashMapOf(1 to "hello", 2 to "hi")
        }
        btn_printUserGroup.setOnClickListener {
            log("UserKV.name: " + UserKV.name)
            log("UserKV.blog: " + UserKV.blog)
            log("UserKV.userBean: " + UserKV.userBean)
            log("UserKV.userBeanOfDefault: " + UserKV.userBeanOfDefault)
            log("UserKV.userBeanList: " + UserKV.userBeanList)
            log("UserKV.map: " + UserKV.map)
        }
        btn_cleanUserGroup.setOnClickListener {
            UserKV.clear()
        }
        btn_setPreferenceGroup.setOnClickListener {
            PreferenceKV.appTheme = Random.nextInt(1, 300).toString()
            PreferenceKV.fontSize = Random.nextInt(1, 300)
        }
        btn_printPreferenceGroup.setOnClickListener {
            log("PreferenceKV.appTheme: " + PreferenceKV.appTheme)
            log("PreferenceKV.fontSize: " + PreferenceKV.fontSize)
        }
        btn_cleanPreferenceGroup.setOnClickListener {
            PreferenceKV.clear()
        }
        btn_printFinalKVGroup.setOnClickListener {
            log("FinalKV.firstInstallTime: " + FinalKV.firstInstallTime)
            log("FinalKV.firstVersionCode: " + FinalKV.firstVersionCode)
            log("FinalKV.firstVersionName: " + FinalKV.firstVersionName)
        }
    }

}