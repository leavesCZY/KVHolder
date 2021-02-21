package github.leavesc.kvholdersamples

import android.app.Application
import github.leavesc.kvholder.KVHolder

/**
 * @Author: leavesC
 * @Date: 2021/2/21 0:22
 * @Desc:
 * @GitHub：https://github.com/leavesC
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        KVHolder.init(this)
        FinalKV.init()
    }

}