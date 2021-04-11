package com.example.kotlinserver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.kotlinserver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initServiceWithBroadcastButton()
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(testReceiver, IntentFilter(TEST_BROADCAST_INTENT_FILTER))
    }

    private val testReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            //Достаем данные из Интента
            intent.getStringExtra(THREADS_FRAGMENT_BROADCAST_EXTRA)?.let {
               binding.textView.text = it
            }
        }
    }


    private fun initServiceWithBroadcastButton() {
        binding.textView.setOnClickListener {
            this?.let {
                it.startService(Intent(it, MainService::class.java).apply {
                    putExtra(
                            MAIN_SERVICE_INT_EXTRA,
                            binding.editText.text.toString().toInt()
                    )
                })
            }
        }
    }
}