package com.dianaq.curso

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    var tts: TextToSpeech?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts = TextToSpeech(this,this)

        findViewById<Button>(R.id.btnPlay).setOnClickListener {
            speak()
        }

    }
    private fun speak(){
        var message: String = findViewById<TextView>(R.id.etMessage).text.toString()

        if (message.isEmpty()){
            findViewById<TextView>(R.id.tvStatus).text = getString(R.string.tts_escribeTexto)
            message =getString(R.string.tts_seguro)
        }

        tts!!.speak(message, TextToSpeech.QUEUE_FLUSH,null, "")
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS){
            findViewById<TextView>(R.id.tvStatus).text = getString(R.string.tts_Active)
            tts!!.language = Locale("ES")
        } else {
            findViewById<TextView>(R.id.tvStatus).text = getString(R.string.tts_No_active)
        }
    }

    override fun onDestroy() {
        if (tts !=null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}