package com.bcgtgjyb.test.mylibrary.Interpolator;

import android.view.animation.Interpolator;

import com.bcgtgjyb.test.mylibrary.Interpolator.InterpolatorInterface;

/**
 * Created by huanwen on 2015/8/30.
 */
public class MyInterpolator implements Interpolator{
private final String TAG="MyInterpolator";
//    private InterpolatorType myType;
    private float[] coordinate;
    private InterpolatorInterface i;
    private MyInterpolator(){

    }
    public MyInterpolator(InterpolatorInterface i){
        this.i=i;
    }
//    public MyInterpolator(InterpolatorType myType){
//        this.myType=myType;
//    }
//
//    public InterpolatorType getEnum(){
//       return this.myType;
//    }
//
//    public static enum InterpolatorType{
//        SlowQuickSlow,QuickSlow
//    }

//    public void setCoordinate(float[] param){
//        coordinate=param;
//    }
//
//    public void setInterpolator(float[] param,int[] direction,int input){
//        getInterpolation(input);
//    }

    @Override
    public float getInterpolation(float input) {
//        float myReturn =getData(new float[]{0,1},new int[]{0},input);
        float myReturn=i.setMy(input);
        return myReturn;
    }




}
