/*
 * Created by Samyak Kamble on 8/11/24, 8:29 PM
 * Copyright (c) 2024. All rights reserved.
 * Last modified 8/11/24, 8:29 PM
 */

package com.samyak2403.bannerads

import android.app.Activity
import android.util.DisplayMetrics
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError

class Control(private val activity: Activity) {

    // Function to load and display a banner ad in the specified LinearLayout
    // निर्दिष्ट LinearLayout मध्ये एक बॅनर अॅड लोड आणि प्रदर्शित करण्यासाठी फंक्शन
    fun loadBannerAd(linearLayoutId: Int, adUnitId: String) {
        // Find the LinearLayout by ID
        // ID द्वारे LinearLayout शोधा
        val linearLayout: LinearLayout? = activity.findViewById(linearLayoutId)
        if (linearLayout != null) {
            // Create a new AdView instance
            // नवीन AdView इंस्टन्स तयार करा
            val adView = AdView(activity)
            // Set the ad unit ID
            // अॅड युनिट ID सेट करा
            adView.adUnitId = adUnitId

            // Get the appropriate ad size for the device
            // डिव्हाइससाठी योग्य अॅड साइज प्राप्त करा
            val adSize = getAdSize()
            adView.setAdSize(adSize)

            // Add the AdView to the LinearLayout
            // AdView ला LinearLayout मध्ये जोडा
            linearLayout.addView(adView)
            // Set up an AdListener to handle ad events
            // अॅड इव्हेंट्स हाताळण्यासाठी AdListener सेट करा
            adView.adListener = object : AdListener() {
                override fun onAdClicked() {
                    super.onAdClicked()
                    // Called when the user clicks on the ad
                    // वापरकर्ता अॅडवर क्लिक केल्यावर कॉल केले जाते
                }

                override fun onAdClosed() {
                    super.onAdClosed()
                    // Called when the ad is closed
                    // अॅड बंद झाल्यावर कॉल केले जाते
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    super.onAdFailedToLoad(loadAdError)
                    // Called when the ad fails to load
                    // अॅड लोड होण्यात अपयशी झाल्यावर कॉल केले जाते
                    // Uncomment the line below to show a Toast message when the ad fails to load
                    // अॅड लोड होण्यात अपयशी झाल्यावर Toast संदेश दाखवण्यासाठी खालील ओळ अनकमेंट करा
                    // Toast.makeText(activity, "Ad Failed", Toast.LENGTH_SHORT).show()
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                    // Called when an impression is recorded for the ad
                    // अॅडसाठी इंप्रेशन नोंदवले गेल्यावर कॉल केले जाते
                }

                override fun onAdLoaded() {
                    super.onAdLoaded()
                    // Called when the ad has finished loading
                    // अॅड लोड होणे समाप्त झाल्यावर कॉल केले जाते
                }

                override fun onAdOpened() {
                    super.onAdOpened()
                    // Called when the ad is opened
                    // अॅड उघडल्यावर कॉल केले जाते
                }
            }
            // Create an ad request and load the ad
            // अॅड विनंती तयार करा आणि अॅड लोड करा
            val adRequest = AdRequest.Builder().build()
            adView.loadAd(adRequest)
        } else {
            // Show a Toast message if the LinearLayout was not found
            // LinearLayout सापडला नाही तर Toast संदेश दर्शवा
            Toast.makeText(activity, "LinearLayout not found", Toast.LENGTH_SHORT).show()
        }
    }

    // Function to get the appropriate ad size based on the device's screen width and density
    // डिव्हाइसच्या स्क्रीन चौडाई आणि घनता यांच्या आधारावर योग्य अॅड साइज प्राप्त करण्यासाठी फंक्शन
    private fun getAdSize(): AdSize {
        val outMetrics = DisplayMetrics()
        // Get the display metrics of the device
        // डिव्हाइसच्या डिस्प्ले मेट्रिक्स प्राप्त करा
        activity.windowManager.defaultDisplay.getMetrics(outMetrics)
        val density = outMetrics.density

        // Calculate the ad width in pixels
        // पिक्सेल्समध्ये अॅडची चौडाई गणना करा
        val adWidthPixels = outMetrics.widthPixels.toFloat()
        // Convert pixels to dp (density-independent pixels)
        // पिक्सल्सना dp (घनत्व-स्वतंत्र पिक्सल्स) मध्ये रूपांतरित करा
        val adWidth = (adWidthPixels / density).toInt()

        // Return the ad size based on the current orientation and ad width
        // वर्तमान ओरिएंटेशन आणि अॅड चौडाईच्या आधारावर अॅड साइज परत करा
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth)
    }
}

///*
// * Created by Samyak kamble on 8/11/24, 8:29 PM
// *  Copyright (c) 2024 . All rights reserved.
// *  Last modified 8/11/24, 8:29 PM
// */
//
//package com.samyak2403.bannerads
//
//
//
//import android.app.Activity
//import android.util.DisplayMetrics
//import android.widget.LinearLayout
//import android.widget.Toast
//import com.google.android.gms.ads.AdListener
//import com.google.android.gms.ads.AdRequest
//import com.google.android.gms.ads.AdSize
//import com.google.android.gms.ads.AdView
//import com.google.android.gms.ads.LoadAdError
//
//class Control(private val activity: Activity) {
//
//    fun loadBannerAd(linearLayoutId: Int, adUnitId: String) {
//        val linearLayout: LinearLayout? = activity.findViewById(linearLayoutId)
//        if (linearLayout != null) {
//            val adView = AdView(activity)
//            adView.adUnitId = adUnitId
//
//            val adSize = getAdSize()
//            adView.setAdSize(adSize)
//
//            linearLayout.addView(adView)
//            adView.adListener = object : AdListener() {
//                override fun onAdClicked() {
//                    super.onAdClicked()
//                }
//
//                override fun onAdClosed() {
//                    super.onAdClosed()
//                }
//
//                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
//                    super.onAdFailedToLoad(loadAdError)
//                    // Toast.makeText(activity, "Ad Failed", Toast.LENGTH_SHORT).show()
//                }
//
//                override fun onAdImpression() {
//                    super.onAdImpression()
//                }
//
//                override fun onAdLoaded() {
//                    super.onAdLoaded()
//                }
//
//                override fun onAdOpened() {
//                    super.onAdOpened()
//                }
//            }
//            val adRequest = AdRequest.Builder().build()
//            adView.loadAd(adRequest)
//        } else {
//            Toast.makeText(activity, "LinearLayout not found", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    private fun getAdSize(): AdSize {
//        val outMetrics = DisplayMetrics()
//        activity.windowManager.defaultDisplay.getMetrics(outMetrics)
//        val density = outMetrics.density
//
//        val adWidthPixels = outMetrics.widthPixels.toFloat()
//        val adWidth = (adWidthPixels / density).toInt()
//
//        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth)
//    }
//}
