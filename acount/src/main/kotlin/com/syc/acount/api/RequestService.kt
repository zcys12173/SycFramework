package com.syc.acount.api

import com.syc.common.network.BaseResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by shiyucheng on 2018/6/25.
 */
interface RequestService{
    @GET("baidu.com")
    fun request(@Query("key") key:String): Observable<BaseResponse<String>>
}