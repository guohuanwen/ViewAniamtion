package com.bcgtgjyb.test.myapplication;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.bcgtgjyb.test.mylibrary.MoveAnimation;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    private String TAG="ApplicationTest";
    public ApplicationTest() {
        super(Application.class);

        math();
//        MoveAnimation moveAnimation=MoveAnimation.getInstance();
//        moveAnimation.getNonlinearCoordinate(0,0,400,400,200,200);
    }

    private void math() {
        float x1=10;
        float y1=15;
        float x0=0;
        float y0=0;
        double pi=Math.PI;
        double R=Math.sqrt((x1-x0)*(x1-x0)+(y1-y0)*(y1-y0));
        double bili=Math.abs(y1-y0)/R;

        double a=Math.asin(bili);
        if(x1>=x0&&y1>=y0){
            a=a;
        }else if(x1<x0&&y1>=y0){
            a=pi-a;
        }else if(x1<x0&&y1<y0){
            a=a+pi;
        }else {
            a=2*pi-a;
        }
        double x=R*Math.cos(a);
        double y=R*Math.sin(a);
        Log.i(TAG, "math  a="+a+"  jiao="+(a/(2*pi))*360 +"  x="+x+"   y="+y);

    }
}