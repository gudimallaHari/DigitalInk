package com.sunayanpradhan.digitalinkrecognition

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sunayanpradhan.digitalinkrecognition.StrokeManager.clear
import com.sunayanpradhan.digitalinkrecognition.StrokeManager.download
import com.sunayanpradhan.digitalinkrecognition.StrokeManager.recognize


class MainActivity : AppCompatActivity() {
    private lateinit var btnRecognize: Button
    private lateinit var btnClear: Button
    private lateinit var drawView: DrawView
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnRecognize = findViewById(R.id.buttonRecognize)
        btnClear = findViewById(R.id.buttonClear)
        drawView = findViewById(R.id.draw_view)
        textView = findViewById(R.id.textResult)

        hideTitleBar()

        download()

        btnRecognize.setOnClickListener {
            recognize(
                textView
            )
        }


        textView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                 recognize(
                     textView
                 )
            }})



        btnClear.setOnClickListener {
            drawView.clear()
            clear()
            textView.text="";
        }
    }

    private fun hideTitleBar() {
        this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }

    override fun onResume() {
        super.onResume()
        hideTitleBar()
    }

    override fun onPause() {
        super.onPause()
        hideTitleBar()
    }

    override fun onStop() {
        super.onStop()

        hideTitleBar()
    }
}