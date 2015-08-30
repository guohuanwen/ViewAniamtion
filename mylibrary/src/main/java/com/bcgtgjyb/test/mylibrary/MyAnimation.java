package com.bcgtgjyb.test.mylibrary;


import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.Keyframe;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/10.
 */
public class MyAnimation {
    private String TAG="MoveAnimation";
    public MyAnimation(){
    }


private AnimatorSet setMoveTo(View view,int x,int y,float[] initCoordinate,int duration){
    return setMoveTo(view,x,y,initCoordinate,duration,new LinearInterpolator());
}


    /**
     * make button move to (x,y)
     * @param view set your view
     * @param x x's coordinate
     * @param y y's coordinate
     * @param initCoordinate animation of view start coordinate
     * @param duration animation's duration
     * @return AnimatorSet
     */
    public AnimatorSet setMoveTo(View view,int x,int y,float[] initCoordinate,int duration,Interpolator acc){
        AnimatorSet animatorSet = new AnimatorSet();
        float xIn=initCoordinate[0];
        float yIn=initCoordinate[1];
        PropertyValuesHolder p1=PropertyValuesHolder.ofFloat("x", (float) (x+xIn));
        PropertyValuesHolder p2=PropertyValuesHolder.ofFloat("y", (float) (y+yIn));
        animatorSet.play(ObjectAnimator.ofPropertyValuesHolder(view, p1, p2));
        animatorSet.setDuration(duration);
        animatorSet.setInterpolator(acc);
        return animatorSet;
    }


    public AnimatorSet setMoveTo(View view,List listX,List listY,float[] initCoordinate,int duration){
        return setMoveTo(view,listX,listY,initCoordinate,duration,new LinearInterpolator());
    }
    /**
     * make button move to list's coordinate
     * @param view set your view
     * @param listX x's coordinate
     * @param listY y's coordinate
     * @param initCoordinate animation of view start coordinate
     * @param duration animation's duration
     * @return AnimatorSet
     */
    public AnimatorSet setMoveTo(View view,List listX,List listY,float[] initCoordinate,int duration,Interpolator interpolator){
        AnimatorSet a=new AnimatorSet();
        List list1=new ArrayList();
        List list2=new ArrayList();
        float number=listX.size()-1;
        float xIn=initCoordinate[0];
        float yIn=initCoordinate[1];
        for (int i = 0; i < listX.size(); i++) {
            double y = (double) listY.get(i);
            double x = (double) listX.get(i);
            Keyframe kx=Keyframe.ofFloat((i/number),(float)(x+xIn));
            Keyframe ky=Keyframe.ofFloat((i/number),(float)(y+yIn));
            list1.add(kx);
            list2.add(ky);
        }
        Keyframe[] kx=(Keyframe[])(list1.toArray(new Keyframe[list1.size()]));
        Keyframe[] ky=(Keyframe[])(list2.toArray(new Keyframe[list2.size()]));
        PropertyValuesHolder p1=PropertyValuesHolder.ofKeyframe("x", kx);
        PropertyValuesHolder p2=PropertyValuesHolder.ofKeyframe("y", ky);
        a.play(ObjectAnimator.ofPropertyValuesHolder(view, p1, p2));
        a.setInterpolator(interpolator);
        a.setDuration(duration);
        return a;
    }

    public AnimatorSet setScaleX(View view,float x,int duration) {
        return setScaleX(view,x,duration,new LinearInterpolator());
    }

    /**
     * set view scale on x axis
     * @param view set your view
     * @param x scale's number
     * @param duration animation's duration
     * @return AnimatorSet
     */
    public AnimatorSet setScaleX(View view,float x,int duration,Interpolator interpolator){
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(
                ObjectAnimator.ofFloat(view, "scaleX", x)
        );
        animatorSet.setDuration(duration);
        animatorSet.setInterpolator(interpolator);
        return animatorSet;
    }

    public AnimatorSet setScaleY(View view,float x,int duration){
        return  setScaleY(view,x,duration,new LinearInterpolator());
    }

    /**
     * set view scale on y axis
     * @param view set your view
     * @param x scale's number
     * @param duration animation's duration
     * @return AnimatorSet
     */
    public AnimatorSet setScaleY(View view,float x,int duration,Interpolator interpolator){
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(
                ObjectAnimator.ofFloat(view, "scaleY", x)
        );
        animatorSet.setInterpolator(interpolator);
        animatorSet.setDuration(duration);
        return  animatorSet;
    }


    public AnimatorSet setRotation(View view,float x,int duration){
        return setRotation(view,x,duration,new LinearInterpolator());
    }
    /**
     * set view's horizontal rotation
     * @param view set your view
     * @param x rotation's number
     * @param duration animation's duration
     * @return AnimatorSet
     */
    public AnimatorSet setRotation(View view,float x,int duration,Interpolator interpolator){
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(
                ObjectAnimator.ofFloat(view, "rotation", x)
        );
        animatorSet.setDuration(duration);
        animatorSet.setInterpolator(interpolator);
        return  animatorSet;
    }


    public AnimatorSet setRotationX(View view,float x,int duration) {
        return setRotationX(view,x,duration,new LinearInterpolator());
    }
    /**
     * set view's rotation on x axis
     * @param view set your view
     * @param x rotation's number
     * @param duration animation's duration
     * @return AnimatorSet
     */
    public AnimatorSet setRotationX(View view,float x,int duration,Interpolator interpolator){
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(
                ObjectAnimator.ofFloat(view, "rotationX", x)
        );
        animatorSet.setDuration(duration);
        animatorSet.setInterpolator(interpolator);
        return  animatorSet;
    }

    /**
     * set view's rotation on y axis
     * @param view set your view
     * @param x rotation's number
     * @param duration animation's duration
     * @return AnimatorSet
     */
    public AnimatorSet setRotationY(View view,float x,int duration){
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(
                ObjectAnimator.ofFloat(view, "rotationY", x)
        );
        animatorSet.setDuration(duration);
        return  animatorSet;
    }

    public AnimatorSet setAlpha(View view,float x,int duration){
        return setAlpha(view,x,duration,new LinearInterpolator());
    }
    /**
     * set view's transparency
     * @param view set your view
     * @param x transparency's number
     * @param duration animation's duration
     * @return AnimatorSet
     */
    public AnimatorSet setAlpha(View view,float x,int duration,Interpolator interpolator){
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(
                ObjectAnimator.ofFloat(view, "alpha", x)
        );
        animatorSet.setDuration(duration);
        animatorSet.setInterpolator(interpolator);
        return  animatorSet;
    }

    public AnimatorSet setTranslation(View view,float x,float y,int duration){
        return setTranslation(view,x,y,duration,new LinearInterpolator());
    }
    /**
     * set view move to the coordinate
     * @param view set your view
     * @param x x's coordinate
     * @param y y's coordinate
     * @param duration animation's duration
     * @return AnimatorSet
     */
    public AnimatorSet setTranslation(View view,float x,float y,int duration,Interpolator interpolator){
        AnimatorSet animatorSet = new AnimatorSet();
        PropertyValuesHolder p1=PropertyValuesHolder.ofFloat("translationX", (float) x);
        PropertyValuesHolder p2=PropertyValuesHolder.ofFloat("translationY", (float) y);
        animatorSet.play(ObjectAnimator.ofPropertyValuesHolder(view, p1, p2));
        //setDuration在play后面设置
        animatorSet.setDuration(duration);
        animatorSet.setInterpolator(interpolator);
        return animatorSet;
    }


    public AnimatorSet setTranslation(View view,List listX,List listY,int duration){
        return setTranslation(view,listX,listY,duration,new LinearInterpolator());
    }
    /**
     * set view move on the list of the coordinate
     * @param view set your view
     * @param listX x's coordinate
     * @param listY y's coordinate
     * @param duration animation's duration
     * @return AnimatorSet
     */
    public AnimatorSet setTranslation(View view,List listX,List listY,int duration,Interpolator interpolator){
        AnimatorSet a=new AnimatorSet();
        List list1=new ArrayList();
        List list2=new ArrayList();
        float number=listX.size()-1;


        for (int i = 0; i < listX.size(); i++) {
            double y = (double) listY.get(i);
            double x = (double) listX.get(i);
            Keyframe kx=Keyframe.ofFloat((i/number),(float)x);
            Keyframe ky=Keyframe.ofFloat((i/number),(float)y);
            list1.add(kx);
            list2.add(ky);
        }

        Keyframe[] kx=(Keyframe[])(list1.toArray(new Keyframe[list1.size()]));
        Keyframe[] ky=(Keyframe[])(list2.toArray(new Keyframe[list2.size()]));
        PropertyValuesHolder p1=PropertyValuesHolder.ofKeyframe("translationX", kx);
        PropertyValuesHolder p2=PropertyValuesHolder.ofKeyframe("translationY", ky);
        a.play(ObjectAnimator.ofPropertyValuesHolder(view, p1, p2));
        a.setDuration(duration);
        a.setInterpolator(interpolator);
        return a;
    }

    /**
     * set view animation  play together
     * @param list
     * @return AnimatorSet
     */
    public AnimatorSet playTogether(List list){
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.playTogether(list);
        return  animatorSet;
    }

    /**
     * set view animation  play sequentially
     * @param list
     * @return AnimatorSet
     */

    public AnimatorSet playSequentially(List list){
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.playSequentially(list);
        return  animatorSet;
    }






}
