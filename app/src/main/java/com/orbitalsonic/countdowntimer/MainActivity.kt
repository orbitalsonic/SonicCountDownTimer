package com.orbitalsonic.countdowntimer

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.orbitalsonic.sonictimer.SonicCountDownTimer

class MainActivity : AppCompatActivity() {

    private lateinit var countDownTimerWithPause: SonicCountDownTimer
    private lateinit var progressIndicator: CircularProgressIndicator
    private lateinit var tvTimerCountFinished: TextView
    private lateinit var tvTimerCountTotal: TextView
    private lateinit var tvTitleSec:TextView
    private lateinit var btnAddSec: Button
    private lateinit var btnReset: Button
    private lateinit var btnPlayPause: FloatingActionButton

    private var timeSec: Int = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        clickMethod()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        progressIndicator = findViewById(R.id.progress_indicator)
        tvTimerCountFinished = findViewById(R.id.tv_timer_count_finished)
        tvTimerCountTotal = findViewById(R.id.tv_timer_count_total)
        tvTitleSec = findViewById(R.id.tv_title_sec)
        btnAddSec = findViewById(R.id.btn_add_sec)
        btnReset = findViewById(R.id.btn_reset)
        btnPlayPause = findViewById(R.id.btn_playpause_timer)

        initCountdownTimer()

        tvTimerCountFinished.text = "\"00"
        tvTimerCountTotal.text = "|\"$timeSec"
        tvTitleSec.text = "$timeSec"

    }

    @SuppressLint("SetTextI18n")
    private fun clickMethod() {
        btnPlayPause.setOnClickListener {
            if (countDownTimerWithPause.isTimerRunning()){
                if (countDownTimerWithPause.isTimerPaused()) {
                    countDownTimerWithPause.resumeCountDownTimer()
                    btnPlayPause.setImageResource(R.drawable.ic_baseline_pause_24)
                } else {
                    countDownTimerWithPause.pauseCountDownTimer()
                    btnPlayPause.setImageResource(R.drawable.ic_baseline_play_24)
                }
            }else{
                countDownTimerWithPause.startCountDownTimer()
                btnPlayPause.setImageResource(R.drawable.ic_baseline_pause_24)

            }

        }
        btnAddSec.setOnClickListener {
            if (!countDownTimerWithPause.isTimerRunning()){
                timeSec+= 10
                progressIndicator.max = timeSec
                countDownTimerWithPause.setCountDownTime((timeSec*1000).toLong())
                tvTimerCountTotal.text = "|\"$timeSec"
                tvTitleSec.text = "$timeSec"
            }

        }

        btnReset.setOnClickListener {
            countDownTimerWithPause.stopCountDownTimer()
            timeSec = 10
            progressIndicator.progress = 0
            tvTimerCountFinished.text = "\"00"
            tvTimerCountTotal.text = "|\"$timeSec"
            tvTitleSec.text = "$timeSec"
        }

    }

    @SuppressLint("SetTextI18n")
    private fun initCountdownTimer() {
        var mCount: Int
        progressIndicator.max = timeSec
        countDownTimerWithPause =
            object : SonicCountDownTimer((timeSec * 1000).toLong(), 1000) {

                override fun onTimerTick(timeRemaining: Long) {
                    mCount = timeSec - (timeRemaining / 1000).toInt()
                    progressIndicator.progress = mCount
                    tvTimerCountFinished.text = "\"$mCount"
                    tvTimerCountTotal.text = "|\"$timeSec"

                }

                override fun onTimerFinish() {
                    btnPlayPause.setImageResource(R.drawable.ic_baseline_play_24)

                }


            }


    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimerWithPause.cancelCountDownTimer()
    }
}