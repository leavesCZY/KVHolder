package github.leavesczy.kvholdersamples

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

/**
 * @Author: leavesCZY
 * @Date: 2021/2/21 0:22
 * @Desc:
 * @GitHub：https://github.com/leavesCZY
 */
class MainActivity : AppCompatActivity() {

    private val btnSetUserGroup by lazy {
        findViewById<View>(R.id.btnSetUserGroup)
    }
    private val btnPrintUserGroup by lazy {
        findViewById<View>(R.id.btnPrintUserGroup)
    }
    private val btnCleanUserGroup by lazy {
        findViewById<View>(R.id.btnCleanUserGroup)
    }
    private val btnSetPreferenceGroup by lazy {
        findViewById<View>(R.id.btnSetPreferenceGroup)
    }
    private val btnPrintPreferenceGroup by lazy {
        findViewById<View>(R.id.btnPrintPreferenceGroup)
    }
    private val btnCleanPreferenceGroup by lazy {
        findViewById<View>(R.id.btnCleanPreferenceGroup)
    }
    private val btnPrintFinalKVGroup by lazy {
        findViewById<View>(R.id.btnPrintFinalKVGroup)
    }
    private val btnClearLog by lazy {
        findViewById<View>(R.id.btnClearLog)
    }
    private val tvLog by lazy {
        findViewById<TextView>(R.id.tvLog)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSetUserGroup.setOnClickListener {
            UserKV.name = Random.nextInt(1, 300).toString()
            UserKV.blog = Random.nextInt(1, 300).toString()
            UserKV.userBean = UserBean("leavesCZY", "https://juejin.cn/user/923245496518439")
            UserKV.userBeanList = listOf(
                UserBean("业志陈", "https://juejin.cn/user/923245496518439"),
                UserBean("公众号", "字节数组"),
                UserBean("GitHub", "https://github.com/leavesCZY")
            )
            UserKV.map = mutableMapOf(1 to "hello", 2 to "hi")
        }
        btnPrintUserGroup.setOnClickListener {
            val log = "UserKV.name: " + UserKV.name + "\n" +
                    "UserKV.blog: " + UserKV.blog + "\n" +
                    "UserKV.userBean: " + UserKV.userBean + "\n" +
                    "UserKV.userBeanOfDefault: " + UserKV.userBeanOfDefault + "\n" +
                    "UserKV.userBeanList: " + UserKV.userBeanList + "\n" +
                    "UserKV.map: " + UserKV.map
            log(log)
        }
        btnCleanUserGroup.setOnClickListener {
            UserKV.clear()
        }
        btnSetPreferenceGroup.setOnClickListener {
            PreferenceKV.appTheme = Random.nextInt(1, 300).toString()
            PreferenceKV.fontSize = Random.nextInt(1, 300)
        }
        btnPrintPreferenceGroup.setOnClickListener {
            val log =
                "PreferenceKV.appTheme: " + PreferenceKV.appTheme + "\n" + "PreferenceKV.fontSize: " + PreferenceKV.fontSize
            log(log)
        }
        btnCleanPreferenceGroup.setOnClickListener {
            PreferenceKV.clear()
        }
        btnPrintFinalKVGroup.setOnClickListener {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
            val log =
                "FinalKV.firstInstallTime: " + sdf.format(Date(FinalKV.firstInstallTime)) + "\n" + "FinalKV.firstVersionCode: " + FinalKV.firstVersionCode + "\n" + "FinalKV.firstVersionName: " + FinalKV.firstVersionName
            log(log)
        }
        btnClearLog.setOnClickListener {
            tvLog.text = ""
        }
    }

    private fun log(log: String) {
        Log.e("TAG", log)
        tvLog.append("\n\n" + log)
    }

}