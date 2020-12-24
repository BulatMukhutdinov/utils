package tat.mukhutdinov.android.utils

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
data class NullableInt(val value: Int?) : Parcelable