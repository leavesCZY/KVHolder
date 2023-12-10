package github.leavesczy.kvholder.samples

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @Author: leavesCZY
 * @Date: 2023/12/5 14:22
 * @Desc:
 */
data class JsonModel(
    val name: String,
    val age: Int
)

@Parcelize
data class ParcelizeModel(
    val name: String?,
    val age: Int
) : Parcelable