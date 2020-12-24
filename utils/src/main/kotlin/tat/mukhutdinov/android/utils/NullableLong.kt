package tat.mukhutdinov.android.utils

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
class NullableLong(val value: Long?) : Parcelable