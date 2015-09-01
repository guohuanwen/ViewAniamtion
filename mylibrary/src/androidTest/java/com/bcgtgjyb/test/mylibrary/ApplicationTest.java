package com.bcgtgjyb.test.mylibrary;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.bcgtgjyb.test.mylibrary.Interpolator.MyInterpolator;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    private final String TAG="ApplicationTest";
    public ApplicationTest() {
        super(Application.class);
        testMyInterpolator();
    }

    private void testMyInterpolator() {
        MyInterpolator myInterpolator=new MyInterpolator();
        float x=0;
        while(x<=1) {
//            myInterpolator.getData(new int[]{0, 1, 0}, x);
            x = x + 0.1f;
            Log.i(TAG, "ApplicationTest ");
        }
    }
}