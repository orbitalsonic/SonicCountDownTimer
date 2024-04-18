package com.orbitalsonic.sonictimer

/**
 * @Author: Muhammad Yaqoob
 * @Date: 18,April,2024.
 * @Accounts
 *      -> https://github.com/orbitalsonic
 *      -> https://www.linkedin.com/in/myaqoob7
 */
interface SonicCountDownTimerListener {
    /**
     * Method to be called every second by the [SonicCountDownTimer]
     *
     * @param timeRemaining: Time remaining in milliseconds.
     */
    fun onTimerTick(timeRemaining: Long)

    /**
     * Method to be called by [SonicCountDownTimer] when the thread is getting  finished
     */
    fun onTimerFinish()
}