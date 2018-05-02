package com.syc.framework.sycframework.model

import io.realm.RealmObject
import io.realm.annotations.RealmClass


/**
 * Created by shiyucheng on 2018/1/11.
 */
@RealmClass
open class Account(open var name:String ? =null,open var userName:String ? =null,open var password:String ? =null):RealmObject()