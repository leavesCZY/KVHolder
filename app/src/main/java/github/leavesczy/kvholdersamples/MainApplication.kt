package github.leavesczy.kvholdersamples

import android.app.Application
import github.leavesczy.kvholder.KVHolder

/**
 * @Author: leavesCZY
 * @Date: 2021/2/21 0:22
 * @Desc:
 * @GitHub：https://github.com/leavesCZY
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        KVHolder.init(this)
        FinalKV.init()
    }

}