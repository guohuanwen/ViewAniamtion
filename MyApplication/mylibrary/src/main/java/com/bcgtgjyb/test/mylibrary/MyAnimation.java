package com.bcgtgjyb.test.mylibrary;


import android.content.Context;
import android.util.Log;
import android.view.View;
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
    private AnimationBuild animationBuild;
    private String TAG="MoveAnimation";
    private View view;
    /**
     * 运动速度
     */
    private int moveVelocity=5;


    private MyAnimation(){

    }

    public MyAnimation(View view){
        this.view=view;
    }

//    public static MoveAnimation getInstance(){
//        return new MoveAnimation();
//    }


    public AnimationBuild getBuild(){
        if (animationBuild==null){
            animationBuild=new AnimationBuild();
        }
        return animationBuild;
    }


    /**
     * make button move to (x,y)
     *直接也到某坐标，坐标是相对于此时的父视图
     */
    private void moveTo(List listX,List listY){
        List animationList = new ArrayList<Animator>();
        AnimatorSet myAnimation = new AnimatorSet();
        getAnimationList(animationList,listX,listY);
        myAnimation.playSequentially(animationList);
        myAnimation.setDuration(moveVelocity);
        myAnimation.setTarget(view);
        myAnimation.addListener(new AnimationOnceListener());
        myAnimation.start();
    }

    public AnimatorSet setScaleX(View view,float x,int duration){
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(
                ObjectAnimator.ofFloat(view, "scaleX", x)
        );
        animatorSet.setDuration(duration);
        return animatorSet;
    }


    public AnimatorSet setScaleY(View view,float x,int duration){
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(
                ObjectAnimator.ofFloat(view, "scaleY", x)
        );
        animatorSet.setDuration(duration);
        return  animatorSet;
    }

    public AnimatorSet setRotation(View view,float x,int duration){
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(
                ObjectAnimator.ofFloat(view, "rotation", x)
        );
        animatorSet.setDuration(duration);
        return  animatorSet;
    }

    public AnimatorSet setRotationX(View view,float x,int duration){
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(
                ObjectAnimator.ofFloat(view, "rotationX", x)
        );
        animatorSet.setDuration(duration);
        return  animatorSet;
    }

    public AnimatorSet setRotationY(View view,float x,int duration){
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(
                ObjectAnimator.ofFloat(view, "rotationY", x)
        );
        animatorSet.setDuration(duration);
        return  animatorSet;
    }

    public AnimatorSet setAlpha(View view,float x,int duration){
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(
                ObjectAnimator.ofFloat(view, "alpha", x)
        );
        animatorSet.setDuration(duration);
        return  animatorSet;
    }

    public AnimatorSet setTranslation(View view,float x,float y,int duration){
        AnimatorSet animatorSet = new AnimatorSet();
        PropertyValuesHolder p1=PropertyValuesHolder.ofFloat("translationX", (float) x);
        PropertyValuesHolder p2=PropertyValuesHolder.ofFloat("translationY", (float) y);

        animatorSet.play(ObjectAnimator.ofPropertyValuesHolder(view, p1, p2));
        //setDuration在play后面设置
        animatorSet.setDuration(duration);
        return animatorSet;
    }

    public AnimatorSet setTranslation(View view,List listX,List listY,int duration){
        AnimatorSet a=new AnimatorSet();
        List list1=new ArrayList();
        List list2=new ArrayList();
        float number=listX.size();
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
        a.play(ObjectAnimator.ofPropertyValuesHolder(view,p1,p2));
        a.setDuration(duration);
        return a;
    }

    public AnimatorSet playTogether(List list){
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.playTogether(list);
        return  animatorSet;
    }

    public AnimatorSet playSequentially(List list){
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.playSequentially(list);
        return  animatorSet;
    }

    /**
     *
     * @param animationList add animationSet in this
     * @param animationBuild
     * @return
     */
    private AnimatorSet getAllAnimationList(List animationList,AnimationBuild animationBuild){
        AnimatorSet a=new AnimatorSet();
        int circleNumber=30;
        List list=animationBuild.getListXY();

        List listx=(List)list.get(0);
        List listy=(List)list.get(1);
        if(listx.size()!=0){
            circleNumber=listx.size();
        }
        float alphaNum=(1-animationBuild.getAlpha())/circleNumber;
        float rotation=(animationBuild.getRotation()-view.getRotation())/circleNumber;
        float rotationX=(animationBuild.getRotationX()-view.getRotationX())/circleNumber;
        float rotationY=(animationBuild.getRotationY()-view.getRotationY())/circleNumber;
        float scaleX = (animationBuild.getLastScaleX() - animationBuild.getNowScaleX()) / circleNumber;
        boolean scaleXParam;
        if(animationBuild.getLastScaleX() - animationBuild.getNowScaleX()>=0){
            //放大
            scaleXParam=false;
        }else {
            scaleXParam=false;
        }
        float scaleY=(animationBuild.getScaleY()-view.getScaleY())/circleNumber;

        float nowAlpha=1;
        float nowRotation=0;
        float nowRotationX=0;
        float nowRotationY=0;
        float nowScaleX=1;
        float nowScaleY=1;
        float lastScaleX=1;
        float lastScaleY=1;
        double x=0;
        double y=0;
        for (int i = 0; i < circleNumber; i++) {
            nowAlpha=nowAlpha-alphaNum;
            nowRotation=nowRotation+rotation;
            nowRotationX=nowRotationX+rotationX;
            nowRotationY=nowRotationY+rotationY;
            nowScaleX = nowScaleX + scaleX;
            nowScaleY=nowScaleY+scaleY;
            if(listx.size()!=0){
                 y = (double) listy.get(i);
                 x = (double) listx.get(i);}
//            Log.i("MoveAnimation","setViewAnimation:"+x+";"+y);
            AnimatorSet animatorSet = new AnimatorSet();
            //持续时间
//            animatorSet.setDuration(moveVelocity);
//            Log.i("Animation", "setButtonAnimation++" + x + "---" + y);
            Log.i(TAG, "getAllAnimationList  "+nowScaleX+"  "+nowScaleY);
            animatorSet.playTogether(//
                    ObjectAnimator.ofFloat(view, "rotation", nowRotation),
                    ObjectAnimator.ofFloat(view, "rotationX", nowRotationX),
                    ObjectAnimator.ofFloat(view, "rotationY", nowRotationY),
                    ObjectAnimator.ofFloat(view, "scaleX", lastScaleX, nowScaleX),
                    ObjectAnimator.ofFloat(view, "scaleY", nowScaleY),
                    ObjectAnimator.ofFloat(view, "alpha", nowAlpha),
                    ObjectAnimator.ofFloat(view, "translationX", (float) x), //
                    ObjectAnimator.ofFloat(view, "translationY", (float) y));//
            lastScaleX=nowScaleX;
            animationList.add(animatorSet);
        }
        a.playSequentially(animationList);
        return  a;
    }



    private List getAnimationList(List animationList,List listx,List listy){
        for (int i = 0; i < listx.size(); i++) {
            double y = (double) listy.get(i);
            double x = (double) listx.get(i);
//            Log.i("MoveAnimation","setViewAnimation:"+x+";"+y);
            AnimatorSet animatorSet = new AnimatorSet();
            //持续时间
//            animatorSet.setDuration(moveVelocity);
//            Log.i("Animation", "setButtonAnimation++" + x + "---" + y);
            animatorSet.playTogether(//
                    ObjectAnimator.ofFloat(view,"translationX",(float)x), //
                    ObjectAnimator.ofFloat(view, "translationY",(float)y));//
            animationList.add(animatorSet);
        }
        return  animationList;
    }


    private List getAnimationListOpposite(List animationList,List listx,List listy){
        for (int i =listx.size()-1; i >=0; i--) {
            double y = (double) listy.get(i);
            double x = (double) listx.get(i);
//            Log.i("MoveAnimation","setViewAnimation:"+x+";"+y);
            AnimatorSet animatorSet = new AnimatorSet();
            //持续时间
//            animatorSet.setDuration(moveVelocity);
//            Log.i("Animation", "setButtonAnimation++" + x + "---" + y);
            animatorSet.playTogether(//
                    ObjectAnimator.ofFloat(view, "translationX", (float) x), //
                    ObjectAnimator.ofFloat(view, "translationY", (float) y));//
            animationList.add(animatorSet);
        }
        return animationList;
    }




    public class AnimationBuild {
//        * @param rotation 水平旋转
//        * @param rotationX 3dx轴旋转
//        * @param ratationY 3dY轴旋转
//        * @param scaleX X轴缩放
//        * @param scaleY y轴缩放
//        * @param alpha 透明度（0-1）
        /**
         * 水平旋转（0°-360°）
         */
        float rotation=0;
        /**
         *3dx轴旋转（0°-360°）
         */
        float rotationX=0;
        /**
         *3dY轴旋转（0°-360°）
         */
        float rotationY=0;
        /**
         *X轴缩放
         */
        float nowScaleX=1;

        public float getNowScaleX() {
            return nowScaleX;
        }

        public float getLastScaleX() {
            return lastScaleX;
        }

        float lastScaleX=1;
        /**
         *y轴缩放
         */
        float scaleY=1;
        /**
         *透明度（0-1）
         */
        float alpha=1;

        /**
         * 默认的animation数目
         */
        int number=30;


        int listSequence=1;

        public int getListSequence() {
            return listSequence;
        }

        public AnimationBuild setListSequence(int listSequence) {
            this.listSequence = listSequence;
            return this;
        }

        public AnimationBuild setNumber(int number) {
            this.number=number;
            return this;
        }

        public int getNumber() {
            if (listX.size()!=0){
                number=listX.size();
            }
            return number;
        }

        List listX=new ArrayList();
        List listY=new ArrayList();

        public float getAlpha() {
            return alpha;
        }

        public float getRotation() {
            return rotation;
        }

        public float getRotationX() {
            return rotationX;
        }

        public float getRotationY() {
            return rotationY;
        }


        public float getScaleY() {
            return scaleY;
        }

        AnimationBuild(){

        }

        public AnimationBuild setScaleY(float scaleY) {
            this.scaleY = scaleY;
            return this;
        }

        public AnimationBuild setAlpha(float alpha) {
            if(alpha>1){
                alpha=1;
            }
            if (alpha<0){
                alpha = 0;
            }
            this.alpha = alpha;
            return this;
        }

        public AnimationBuild setRotation(float rotation) {
            this.rotation = rotation;
            return this;
        }

        public AnimationBuild setRotationX(float rotationX) {
            this.rotationX = rotationX;
            return this;
        }

        public AnimationBuild setRotationY(float rotationY) {
            this.rotationY = rotationY;
            return this;
        }

        public AnimationBuild setScaleX(float nowScaleX,float lastScaleX) {
            this.lastScaleX = lastScaleX;
            this.nowScaleX=nowScaleX;
            return this;
        }

        public AnimationBuild setListXY(List listX,List listY){
            this.listX=listX;
            this.listY=listY;

            return this;
        }

        public List getListXY() {
            List list=new ArrayList();
            list.add(listX);
            list.add(listY);
            return list;
        }
    }









}
