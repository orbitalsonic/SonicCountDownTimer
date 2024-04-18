package com.orbitalsonic.sonictimer

import android.os.Handler
import android.os.Looper
import android.os.Message

/**
 * @Author: Muhammad Yaqoob
 * @Date: 18,April,2024.
 * @Accounts
 *      -> https://github.com/orbitalsonic
 *      -> https://www.linkedin.com/in/myaqoob7
 */
abstract class SonicCountDownTimer : SonicCountDownTimerListener {
    /**
     * To maintain Timer start and stop status.
     */
    private var isTimerRunning = false

    /**
     * To maintain Timer resume and pause status.
     */
    private var isTimerPaused = false

    /**
     * Timer time.
     */
    private var mTime: Long = 0
    private var localTime: Long = 0
    private var timeInterval: Long = 0
    private var mHandler: Handler? = null

    constructor() {
        init(0, INTERVAL.toLong())
    }

    constructor(timeInMillis: Long) {
        init(timeInMillis, INTERVAL.toLong())
    }

    constructor(timeInMillis: Long, intervalInMillis: Long) {
        init(timeInMillis, intervalInMillis)
    }

    /**
     * Method to initialize [SonicCountDownTimer].
     *
     * @param time:     Time in milliseconds.
     * @param interval: in milliseconds.
     */
    private fun init(time: Long, interval: Long) {
        setCountDownTime(time)
        setTimeInterval(interval)
        initSonicCountDownTimer()
    }

    private fun initSonicCountDownTimer() {
        mHandler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                if (msg.what == MSG) {
                    if (!isTimerPaused) {
                        if (localTime <= mTime) {
                            onTimerTick(mTime - localTime)
                            localTime += timeInterval
                            sendMessageDelayed(mHandler!!.obtainMessage(MSG), timeInterval)
                        } else stopCountDownTimer()
                    }
                }
            }
        }
    }

    /**
     * Convenience method to check whether the timer is running or not
     *
     * @return: true if timer is running, else false.
     */
    fun isTimerRunning(): Boolean {
        return isTimerRunning
    }

    /**
     * Method to start the CountDownTimer.
     */
    fun startCountDownTimer() {
        if (isTimerRunning) return
        isTimerRunning = true
        isTimerPaused = false
        localTime = 0
        mHandler!!.sendMessage(mHandler!!.obtainMessage(MSG))
    }

    /**
     * Method to stop the CountDownTimer.
     */
    fun stopCountDownTimer() {
        isTimerRunning = false
        mHandler!!.removeMessages(MSG)
        onTimerFinish()
    }

    /**
     * Method to cancel the CountDownTimer.
     */
    fun cancelCountDownTimer() {
        isTimerRunning = false
        mHandler!!.removeMessages(MSG)
    }

    /**
     * Method to check whether the CountDownTimer is paused.
     *
     * @return: true if CountDownTimer is paused else false.
     */
    @Synchronized
    fun isTimerPaused(): Boolean {
        return isTimerPaused
    }

    /**
     * To pause the timer from Main thread.
     *
     * @param isPaused: true to pause the timer, false to resume.
     */
    @Synchronized
    private fun setTimerPaused(isPaused: Boolean) {
        this.isTimerPaused = isPaused
    }

    /**
     * Convenience method to pause the timer.
     */
    @Synchronized
    fun pauseCountDownTimer() {
        setTimerPaused(true)
    }

    /**
     * Convenience method to resume the timer.
     */
    @Synchronized
    fun resumeCountDownTimer() {
        setTimerPaused(false)
        mHandler!!.sendMessage(mHandler!!.obtainMessage(MSG))
    }

    /**
     * Setter for Time.
     *
     * @param timeInMillis: in milliseconds
     */
    fun setCountDownTime(timeInMillis: Long) {
        var timeInMillis = timeInMillis
        if (isTimerRunning) return
        if (mTime <= 0) if (timeInMillis < 0) timeInMillis *= -1
        mTime = timeInMillis
    }

    /**
     * @return remaining time
     */
    fun getRemainingTime(): Long {
        return if (isTimerRunning) {
            mTime
        } else 0
    }

    /**
     * Setter for time interval.
     *
     * @param intervalInMillis: in milliseconds
     */
    fun setTimeInterval(intervalInMillis: Long) {
        var intervalInMillis = intervalInMillis
        if (isTimerRunning) return
        if (timeInterval <= 0) if (intervalInMillis < 0) intervalInMillis *= -1
        timeInterval = intervalInMillis
    }

    companion object {
        private const val INTERVAL = 1000
        private const val MSG = 1
    }
}
