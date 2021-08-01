package com.orbitalsonic.sonictimer

/**
 * Created by @author Engr. Muhammad Yaqoob
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