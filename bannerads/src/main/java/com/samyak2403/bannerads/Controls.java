/*
 * Created by Samyak kamble on 8/11/24, 8:49 PM
 *  Copyright (c) 2024 . All rights reserved.
 *  Last modified 8/11/24, 8:49 PM
 */

package com.samyak2403.bannerads;


import android.app.Activity;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;

public class Controls {

    private Activity activity;

    public Controls(Activity activity) {
        this.activity = activity;
    }

    public void loadBannerAd(int linearLayoutId, String adUnitId) {
        LinearLayout linearLayout = activity.findViewById(linearLayoutId);
        if (linearLayout != null) {
            AdView adView = new AdView(activity);
            adView.setAdUnitId(adUnitId);

            AdSize adSize = getAdSize();
            adView.setAdSize(adSize);

            linearLayout.addView(adView);
            adView.setAdListener(new AdListener() {
                @Override
                public void onAdClicked() {
                    super.onAdClicked();
                }

                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                }

                @Override
                public void onAdFailedToLoad(LoadAdError adError) {
                    super.onAdFailedToLoad(adError);
                    // Toast.makeText(activity, "Ad Failed", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAdImpression() {
                    super.onAdImpression();
                }

                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                }

                @Override
                public void onAdOpened() {
                    super.onAdOpened();
                }
            });
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        } else {
            Toast.makeText(activity, "LinearLayout not found", Toast.LENGTH_SHORT).show();
        }
    }

    private AdSize getAdSize() {
        DisplayMetrics outMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        float density = outMetrics.density;

        float adWidthPixels = outMetrics.widthPixels;
        int adWidth = (int) (adWidthPixels / density);

        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth);
    }
}
