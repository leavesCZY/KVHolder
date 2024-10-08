package github.leavesczy.kvholder.samples

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

/**
 * @Author: leavesCZY
 * @Date: 2023/12/5 14:20
 * @Desc:
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
            UserKV.intValue = randomInt()
            UserKV.longValue = randomInt().toLong()
            UserKV.floatValue = randomInt().toFloat()
            UserKV.doubleValue = randomInt().toDouble()
            UserKV.booleanValue = randomBoolean()
            UserKV.stringValue = randomWord()
            UserKV.parcelableValue = ParcelizeModel(
                name = randomWord(),
                age = randomInt()
            )
            UserKV.parcelableValueNullEnabled = if (randomBoolean()) {
                ParcelizeModel(
                    name = randomWord(),
                    age = randomInt()
                )
            } else {
                null
            }
            UserKV.jsonValue = JsonModel(
                name = randomWord(),
                age = randomInt()
            )
            UserKV.jsonValueNullEnabled = if (randomBoolean()) {
                JsonModel(
                    name = randomWord(),
                    age = randomInt()
                )
            } else {
                null
            }
        }
        btnPrintUserGroup.setOnClickListener {
            val string = buildString {
                append("intValue: " + UserKV.intValue)
                append("\n")
                append("\n")
                append("longValue: " + UserKV.longValue)
                append("\n")
                append("\n")
                append("floatValue: " + UserKV.floatValue)
                append("\n")
                append("\n")
                append("doubleValue: " + UserKV.doubleValue)
                append("\n")
                append("\n")
                append("booleanValue: " + UserKV.booleanValue)
                append("\n")
                append("\n")
                append("stringValue: " + UserKV.stringValue)
                append("\n")
                append("\n")
                append("parcelableValue: " + UserKV.parcelableValue)
                append("\n")
                append("\n")
                append("parcelableValueNullEnabled: " + UserKV.parcelableValueNullEnabled)
                append("\n")
                append("\n")
                append("jsonValue: " + UserKV.jsonValue)
                append("\n")
                append("\n")
                append("jsonValueNullEnabled: " + UserKV.jsonValueNullEnabled)
            }
            log(log = string)
        }
        btnCleanUserGroup.setOnClickListener {
            UserKV.clear()
        }
        btnSetPreferenceGroup.setOnClickListener {
            PreferenceKV.parcelableValue = ParcelizeModel(
                name = randomWord(),
                age = randomInt()
            )
            PreferenceKV.parcelableValueNullEnabled = if (randomBoolean()) {
                ParcelizeModel(
                    name = if (randomBoolean()) {
                        null
                    } else {
                        randomWord()
                    },
                    age = randomInt()
                )
            } else {
                null
            }
        }
        btnPrintPreferenceGroup.setOnClickListener {
            val string = buildString {
                append("parcelableValue: " + PreferenceKV.parcelableValue)
                append("\n")
                append("\n")
                append("parcelableValueNullEnabled: " + PreferenceKV.parcelableValueNullEnabled)
            }
            log(log = string)
        }
        btnCleanPreferenceGroup.setOnClickListener {
            PreferenceKV.clear()
        }
        btnClearLog.setOnClickListener {
            tvLog.text = ""
        }
    }

    private fun randomInt(): Int {
        return Random.nextInt(1, 500)
    }

    private fun randomBoolean(): Boolean {
        return Random.nextBoolean()
    }

    private fun randomWord(): String {
        return "leavesCZY" + "_" + randomInt()
    }

    private fun log(log: String) {
        Log.e("TAG", log)
        tvLog.append(log + "\n\n")
    }

}