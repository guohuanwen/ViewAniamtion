package com.bcgtgjyb.test.myapplication;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.bcgtgjyb.test.mylibrary.MoveAnimation;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
        MoveAnimation moveAnimation=MoveAnimation.getInstance();
//        moveAnimation.getNonlinearCoordinate(0,0,400,400,200,200);
    }
}