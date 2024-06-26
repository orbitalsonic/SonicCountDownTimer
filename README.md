[![](https://jitpack.io/v/orbitalsonic/SonicCountDownTimer.svg)](https://jitpack.io/#orbitalsonic/SonicCountDownTimer)
# CountDownTimer-With-Pause-Resume-Android-Kotlin
CountDownTimer in Android is used to set a countdown based on interval set by you and it will stop when the time has come in future.  You can use this Count Down Timer for creating any countdown for an event. But in some cases we need Pause or Resume CountDownTimer but it doesn't have Pause and Resume methods. SonicCountDownTimer library will fulfill this need.

# Add Gradle Files

Add maven repository in project level build.gradle or in latest project setting.gradle file

```
repositories {
    google()
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

```

###### Add following dependency in app gradle file

```
  implementation 'com.github.orbitalsonic:SonicCountDownTimer:1.0.5'
 
 ```
 
 
 # Java or Kotlin
   
   ###### Three constructors for initializing
        SonicCountDownTimer()       //default time interval is 1 second
        SonicCountDownTimer(timeInMillis: Long)   //default time interval is 1 second 
        SonicCountDownTimer(timeInMillis: Long, intervalInMillis: Long)
   
   ###### Methods to manage countdown timer
        fun startCountDownTimer()
        fun pauseCountDownTimer()
        fun resumeCountDownTimer()
        fun stopCountDownTimer()
        fun cancelCountDownTimer()
   
   ###### Abstract methods for updating UI
        fun onTimerTick(timeRemaining: Long)    // Called after every time interval.
        fun onTimerFinish()                   // Called when time specified finishes 
   
   ###### Setters & Getters
        fun setCountDownTime(timeInMillis: Long)
        fun setTimeInterval(intervalInMillis: Long)
        fun getRemainingTime(): Long
        
 # Example
           val  sonicCountDownTimer =
            object : SonicCountDownTimer(30000, 1000) {

                override fun onTimerTick(timeRemaining: Long) {
                    // Update UI

                }

                override fun onTimerFinish() {
                    // CountDownTimer finished

                }

            }

        sonicCountDownTimer.startCountDownTimer()


# Screenshots
![alt text](https://github.com/orbitalsonic/CountDownTimer-With-Pause-Resume-Android-Kotlin/blob/master/Shcreenshots/screen1.gif)
![alt text](https://github.com/orbitalsonic/CountDownTimer-With-Pause-Resume-Android-Kotlin/blob/master/Shcreenshots/screen2.png)

