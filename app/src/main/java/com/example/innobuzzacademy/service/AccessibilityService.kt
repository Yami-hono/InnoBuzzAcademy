package com.example.innobuzzacademy.service

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast

class AccessibilityService:AccessibilityService() {
    override fun onAccessibilityEvent(p0: AccessibilityEvent?) {

        val packageName=p0?.packageName.toString()
        val packageManager=this.packageManager
        try {
            val applicationInfo = packageManager.getApplicationInfo(packageName, 0)
            val applicationLabel=packageManager.getApplicationLabel(applicationInfo)
            if(applicationLabel=="WhatsApp")
            Toast.makeText(this.applicationContext,"Whatsapp is launched", Toast.LENGTH_SHORT).show()
        }catch (ex:Exception){

        }

    }

    override fun onInterrupt() {
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        val info=AccessibilityServiceInfo()
        info.apply {
            eventTypes = AccessibilityEvent.TYPE_VIEW_CLICKED or AccessibilityEvent.TYPE_VIEW_FOCUSED

            feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN

            notificationTimeout = 100
        }

        this.serviceInfo = info

    }
}