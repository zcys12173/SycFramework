package com.syc.framework.sycframework.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by shiyucheng on 2018/1/16.
 */
@Parcelize
data class User(var userName: String? = null,
                var password: String? = null, var role: Int,
                var email: String, var phone: String) : Parcelable