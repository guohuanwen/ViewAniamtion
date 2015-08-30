package com.bcgtgjyb.test.mylibrary;

import android.animation.TimeInterpolator;
import android.view.animation.Interpolator;

/**
 * Created by huanwen on 2015/8/30.
 */
public class MyInterpolator {

    public  static class  SlowQuickSlowInterpolator implements Interpolator{
        public SlowQuickSlowInterpolator(){

        }
        @Override
        public float getInterpolation(float input) {
            if(input<0.5){
                return (1/10)+input*input;
            }else{
                return -(1/10)+input*input;
            }

        }

    }
}
