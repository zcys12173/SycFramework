package com.syc.framework.sycframework.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by shiyucheng on 2018/1/16.
 */
@RealmClass
open class User(@PrimaryKey var userName:String?=null, var password:String ? = null):RealmObject()