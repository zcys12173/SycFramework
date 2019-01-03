package com.syc.acount.viewmodel

import android.os.Bundle
import android.speech.tts.TextToSpeech
import com.syc.common.RxBaseViewModel
import com.syc.common.utils.LogUtil
import java.util.*


/**
 * Created by shiyucheng on 2018/10/11.
 */
class FirstFragmentViewModel : RxBaseViewModel() {
    private lateinit var tts: TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tts = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
            if (status == TextToSpeech.SUCCESS) {
                //默认设定语言为中文，原生的android貌似不支持中文。
                val result = tts.setLanguage(Locale.CHINESE)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast("数据丢失或不支持")
                }
            }
        })
    }

    fun onSpeechClick() {
        val result = tts.speak("这是一个测  试，Lucy", TextToSpeech.QUEUE_FLUSH, null)
        LogUtil.d("aaa", "result:$result")
    }

    override fun onDestroy() {
        tts.shutdown()
        super.onDestroy()
    }
}