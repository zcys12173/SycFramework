package com.syc.acount.api

import com.syc.common.network.BaseResponse
import com.syc.framework.sycframework.model.User
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by shiyucheng on 2018/6/25.
 */
interface RequestService {
    @POST("user/user")
    @FormUrlEncoded
    fun register(@Field("userName") userName: String, @Field("password") password: String): Observable<BaseResponse<String>>

    @POST("user/login")
    @FormUrlEncoded
    fun login(@Field("userName") userName: String, @Field("password") password: String): Observable<BaseResponse<User>>
}