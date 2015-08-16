package com.bcgtgjyb.test.mylibrary;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.Keyframe;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2015/8/10.
 */
public class MoveAnimation {
    private AnimationBuild animationBuild;
    private Context context;
    private final double pi=Math.PI;
    boolean boundary=false;
    private String TAG="MoveAnimation";
    private View view;
    /**
     * 运动速度
     */
    private int moveVelocity=5;
    /**
     * 自由运动范围
     */
    private int moveScope=30;
    /**
     * 随机点的个数
     */
    private int coordinateNumber=10;
    //屏幕宽高
    private int windowsWight=0;
    private int windowsHeight=0;

    private float viewFourCoordinate[]={0,0,0,0,0,0,0,0};
    private WindowManager wm;

    private MoveAnimation(){

    }

    public MoveAnimation(View view,Context context){
        this.context=context;
        this.view=view;
    }

    public static MoveAnimation getInstance(){
        return new MoveAnimation();
    }


    public AnimationBuild getBuild(){
        if (animationBuild==null){
            animationBuild=new AnimationBuild();
        }
        return animationBuild;
    }
    public void setBoundary(boolean boundary,int[] boundaryXY) {
        windowsWight = boundaryXY[0];
        windowsHeight = boundaryXY[1];
        if(boundary){
            setMyBoundary();
        }
        this.boundary = boundary;
    }

    /**
     * set view random move
     * @param param is true ,view random move
     */
    public void setRandomAnimation(boolean param){
        if(param) {
            int[] location=new int[2];
            view.getLocationOnScreen(location);
            float myX = location[0];
            float myY = location[1];
            List list = getRandomDate(coordinateNumber);
            List animation=new ArrayList();
            getAnimationList(animation, (List) list.get(0), (List) list.get(1));
            setViewAnimationCirculate(view, animation);
        }
    }

    /**
     * set view curve move
     * @param end   final location of the view
     * @param arc
     * @param direction
     */
    public void setCurveMove(float[] end,int arc,int direction){
        if(true){
            float[] start={0,0};
            List list=getCurveData(start, end, arc, direction);
            List animation=new ArrayList();
//            getAnimationList(animation,(List) list.get(0), (List) list.get(1));
//            getAnimationListOpposite(animation, (List) list.get(0), (List) list.get(1));


            List list1=new ArrayList();
            List list2=new ArrayList();
            List listX=(List) list.get(0);
            List listY=(List) list.get(1);
            list1.addAll(listX);
            list2.addAll(listY);
            Collections.reverse(listX);
            Collections.reverse(listY);
            list1.addAll(listX);
            list2.addAll(listY);
//            getAllAnimationList(animation, new AnimationBuild().setAlpha(0.3f).//
//                    setRotation(360).setScaleX(2).setRotationX(360).//
//                    setListXY(list1, list2));

            setViewAnimationOnce(view, animation);
        }
    }


    /**
     * set view move on a circle
     * @param x0
     * @param y0
     */
    public void setCircleMove(float x0,float y0){
        float[] location=getCoordinateOnFartherView();
        List list=getCircleData(location[0],location[1],x0,y0);
        List listX=(List)list.get(0);
        List listY=(List)list.get(1);
        moveTo(listX, listY);
//        setViewAnimationReturn(view, listX, listY);
    }









    private float[] getCoordinateOnScreen(){
        int[] location=new int[2];
        view.getLocationOnScreen(location);
        float myX = location[0];
        float myY = location[1];
        return new float[]{myX,myY};
    }

    private float[] getCoordinateOnFartherView(){
        float x1=view.getX();
        float y1=view.getY();
        return  new float[]{x1,y1};
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

    class AnimationOnceListener implements Animator.AnimatorListener{

        private boolean mCanceled;

        public void onAnimationStart(Animator animation) {
            mCanceled = false;
            float[] location=getCoordinateOnFartherView();
            Log.i(TAG, "onAnimationStart x y "+ location[0]+"   "+location[1]);

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            if (!mCanceled) {
//                    animation.start();
            }
            float[] location=getCoordinateOnFartherView();
            Log.i(TAG, "onAnimationStart x y "+ location[0]+"   "+location[1]);

        }

        @Override
        public void onAnimationCancel(Animator animation) {
            mCanceled = true;
        }
    }

    class AnimationCirculateListener implements Animator.AnimatorListener{

        private boolean mCanceled;

        public void onAnimationStart(Animator animation) {
            mCanceled = false;
            float[] location=getCoordinateOnFartherView();
            Log.i(TAG, "onAnimationStart x y "+ location[0]+"   "+location[1]);

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            if (!mCanceled) {
                    animation.start();
            }
            float[] location=getCoordinateOnFartherView();
            Log.i(TAG, "onAnimationStart x y "+ location[0]+"   "+location[1]);

        }

        @Override
        public void onAnimationCancel(Animator animation) {
            mCanceled = true;
        }
    }



    /**
     * make view move to the coordinates which is in Listx and Listy,and return original coordinate !then Circulate like this
     * @param view
     */
    private void setViewAnimationCirculate(final View view,List animationList) {
        AnimatorSet myAnimation=new AnimatorSet();
        myAnimation.playSequentially(animationList);
        myAnimation.setDuration(moveVelocity);
        myAnimation.setTarget(view);
        myAnimation.addListener(new AnimationCirculateListener());
        myAnimation.start();

    }

    /**
     * set view move to the coordinate,no return
     * @param view
     */
    public void setViewAnimationOnce(final View view,List animationList) {
        AnimatorSet myAnimation=new AnimatorSet();
        myAnimation.playSequentially(animationList);
        myAnimation.addListener(new AnimationOnceListener());
        myAnimation.setDuration(moveVelocity);
        myAnimation.setTarget(view);
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
    public AnimatorSet getAllAnimationList(List animationList,AnimationBuild animationBuild){
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

    /**
     * 模拟随机运动，返回运动坐标
     * 以初始位置为中心，以定值为半径做圆，在圆内生成随机数，返回一串随即数，最后一个为初始位置
     * @param param
     * @return
     */
    public List<List> getRandomDate(int param) {
        List x = new ArrayList<Object>();
        List y = new ArrayList<Object>();
        float m;
        float n;
        Random random = new Random();
        //定义半径
        float R = moveScope;
        float x1 =0f;
        float y1 =0f;
        float x2=0f;
        float y2=0f;
        for (int i = 0; i < param; i++) {
            x2 = (random.nextFloat()*300) % R;
            y2 = (float) Math.sqrt((R * R - x2 * x2));
            m = x2 - x1;
            n = y2 - y1;
            x.add(m);
            y.add(n);
            x2 = x1;
            y2 = y1;
        }
        m = 0f-x2;
        n = 0f-y2;
        x.add(m);
        y.add(n);
//		Log.i("Animation", x.size()+"");
        List re = new ArrayList<List>();
        re.add(x);
        re.add(y);
        return re;
    }

    /**
     * 获取曲线数据
     * @param start
     * @param end
     * @param arc
     * @param direction
     * @return
     */
    public List<List<Double>> getCurveData(float[] start,float[] end,int arc,int direction){
        int coordinateNumber=80;

        float[] original=getCoordinateOnFartherView();
        List listX=new ArrayList<Long>();
        List listY=new ArrayList<Long>();
        //计算用matlab，有需要可以学习一下

        //起始点坐标
        double x1=start[0];
        double y1=start[1];
        //结束点坐标
        double x2=end[0];
        double y2=end[1];


        //圆弧中点
        double x3,y3;
        if(direction==0){
            x3=-(2*y1*(y1/2 + y2/2 + arc*x1*Math.sqrt(1/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2)) - arc*x2*Math.sqrt(1/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2))) - 2*y2*(y1/2 + y2/2 + arc*x1*Math.sqrt(1/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2)) - arc*x2*Math.sqrt(1/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2))) - x1*x1 + x2*x2 - y1*y1 + y2*y2)/(2*x1 - 2*x2);
            y3=y1/2 + y2/2 + arc*x1*Math.sqrt(1/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2)) - arc*x2*Math.sqrt(1/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2));
//             x3=-(2*y1*(y1/2 + y2/2 + x1*(arc/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2))^(1/2) - x2*(arc/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2))^(1/2)) - 2*y2*(y1/2 + y2/2 + x1*(arc/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2))^(1/2) - x2*(arc/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2))^(1/2)) - x1*x1 + x2*x2 - y1*y1 + y2*y2)/(2*x1 - 2*x2);
//             y3=y1/2 + y2/2 + x1*(arc/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2))^(1/2) - x2*(arc/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2))^(1/2);
        }else if(direction==1){
            x3=-(2*y1*(y1/2 + y2/2 - arc*x1*Math.sqrt(1/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2)) + arc*x2*Math.sqrt(1/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2))) - 2*y2*(y1/2 + y2/2 - arc*x1*Math.sqrt(1/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2)) + arc*x2*Math.sqrt(1/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2))) - x1*x1 + x2*x2 - y1*y1 + y2*y2)/(2*x1 - 2*x2);
            y3=y1/2 + y2/2 - arc*x1*Math.sqrt(1/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2)) + arc*x2*Math.sqrt(1/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2));
//             x3=-(2*y1*(y1/2 + y2/2 - x1*(arc/(x1^2 - 2*x1*x2 + x2^2 + y1^2 - 2*y1*y2 + y2^2))^(1/2) + x2*(arc/(x1^2 - 2*x1*x2 + x2^2 + y1^2 - 2*y1*y2 + y2^2))^(1/2)) - 2*y2*(y1/2 + y2/2 - x1*(arc/(x1^2 - 2*x1*x2 + x2^2 + y1^2 - 2*y1*y2 + y2^2))^(1/2) + x2*(arc/(x1^2 - 2*x1*x2 + x2^2 + y1^2 - 2*y1*y2 + y2^2))^(1/2)) - x1^2 + x2^2 - y1^2 + y2^2)/(2*x1 - 2*x2);
//             y3=y1/2 + y2/2 - x1*(arc/(x1^2 - 2*x1*x2 + x2^2 + y1^2 - 2*y1*y2 + y2^2))^(1/2) + x2*(arc/(x1^2 - 2*x1*x2 + x2^2 + y1^2 - 2*y1*y2 + y2^2))^(1/2);
        }else{
            x3= 121;
            y3= 78;
        }
//        Log.i("MoveAnimation","x1="+x1+",y1="+y1+",x2="+x2+",y2="+y2+",arc="+arc+",x3="+x3+",y3="+y3);
        //三点求圆的方程
        double a,b,c;
        a =-(x1*x1*y2 - x1*x1*y3 - x2*x2*y1 + x2*x2*y3 + x3*x3*y1 - x3*x3*y2 + y1*y1*y2 - y1*y1*y3 - y1*y2*y2 + y1*y3*y3 + y2*y2*y3 - y2*y3*y3)/(x1*y2 - x2*y1 - x1*y3 + x3*y1 + x2*y3 - x3*y2);
        b =-(x1*x1*x3 - x1*x1*x2 + x1*x2*x2 - x1*x3*x3 + x1*y2*y2 - x1*y3*y3 - x2*x2*x3 + x2*x3*x3 - x2*y1*y1 + x2*y3*y3 + x3*y1*y1 - x3*y2*y2)/(x1*y2 - x2*y1 - x1*y3 + x3*y1 + x2*y3 - x3*y2);
        c =(x1*x1*x3*y2 - x1*x1*x2*y3 + x1*x2*x2*y3 - x1*x3*x3*y2 + x1*y2*y2*y3 - x1*y2*y3*y3 - x2*x2*x3*y1 + x2*x3*x3*y1 - x2*y1*y1*y3 + x2*y1*y3*y3 + x3*y1*y1*y2 - x3*y1*y2*y2)/(x1*y2 - x2*y1 - x1*y3 + x3*y1 + x2*y3 - x3*y2);

//        Log.i("MoveAnimation","a="+a+",b="+b+",c="+c);
        double x,y;
        x=x1;
        y=y1;
        int left=0;
        if(x1>x2){
            left=1;
        }else{
            left=0;
        }
        listX.add((double)0);
        listY.add((double)0);
        if(left==0){
            //x1<=x2
            double moveX=(x2-x1)/coordinateNumber;
            double moveNub=x1+moveX;
            for(int i=0;i<coordinateNumber;i++){
                if (moveNub<=x2){
                    double[] re=calculateMoveCoordinate(x,y,moveX,a,b,c);
                    x=re[0];
                    y=re[1];
                    moveNub=moveNub+moveX;
                    if(boundary){
                        if(isBoundary(new float[]{(float)(Math.abs(x)),(float)(Math.abs(y))})){
                            listX.add(x);
                            listY.add(y);
                        }
                    }else{
                        listX.add(x);
                        listY.add(y);
                    }


                }else{
                    moveX=x2-(moveNub-moveX);
                    double[] re=calculateMoveCoordinate(x,y,moveX,a,b,c);
                    x=re[0];
                    y=re[1];
                    if(boundary){
                        if(isBoundary(new float[]{(float)(Math.abs(x)),(float)(Math.abs(y))})){
                            listX.add(x);
                            listY.add(y);
                        }
                    }else{
                        listX.add(x);
                        listY.add(y);
                    }
                    break;
                }
            }
        }else{
            //x1>x2
            double moveX=(x2-x1)/coordinateNumber;
            double moveNub=x1+moveX;
            for(int i=0;i<coordinateNumber;i++){
                if (moveNub>=x2){
                    double[] re=calculateMoveCoordinate(x,y,moveX,a,b,c);
                    x=re[0];
                    y=re[1];
                    moveNub=moveNub+moveX;
                    if(boundary){
                        if(isBoundary(new float[]{(float)(Math.abs(x)),(float)(Math.abs(y))})){
                            listX.add(x);
                            listY.add(y);
                        }
                    }else{
                        listX.add(x);
                        listY.add(y);
                    }
                }else{
                    moveX=x2-(moveNub-moveX);
                    double[] re=calculateMoveCoordinate(x,y,moveX,a,b,c);
                    x=re[0];
                    y=re[1];
                    if(boundary){
                        if(isBoundary(new float[]{(float)(Math.abs(x)),(float)(Math.abs(y))})){
                            listX.add(x);
                            listY.add(y);
                        }
                    }else{
                        listX.add(x);
                        listY.add(y);
                    }
                    break;
                }
            }
        }


        List list=new ArrayList<List>();
        list.add(listX);
        list.add(listY);
        return list;
    }

    /**
     * 给定x计算圆上坐标
     * @param startX
     * @param startY
     * @param moveX
     * @param a
     * @param b
     * @param c
     * @return
     */
    private double[] calculateMoveCoordinate(double startX,double startY,double moveX,double a,double b,double c){
        double coordinate[]={0,0};
        double x=startX+moveX;

        double yy1=- b/2 - Math.sqrt(b*b - 4*x*x - 4*a*x - 4*c)/2;
        double yy2=Math.sqrt(b*b - 4*x*x - 4*a*x - 4*c)/2 - b/2;

        coordinate[0]=x;
        if(Math.abs(yy1-startY)>Math.abs(yy2-startY)){
            coordinate[1]=yy2;
        }
        else{
            coordinate[1]=yy1;
        }
        return coordinate;
    }

    //获取屏幕宽高，获取view四点坐标
    private void setMyBoundary(){

//        float[] location=getCoordinateInScreen();
        float[] location=getCoordinateOnFartherView();

        float param[]=getViewWightHeight();
        //上左一点
        float x1=location[0];
        float y1=location[1];
        //上右
        float x2=x1+param[0];
        float y2=y1;
        //下左
        float x3=x1;
        float y3=y1+param[1];
        //下右
        float x4=x1+param[0];
        float y4=y1+param[1];

        viewFourCoordinate[0]=x1;
        viewFourCoordinate[1]=y1;
        viewFourCoordinate[2]=x2;
        viewFourCoordinate[3]=y2;
        viewFourCoordinate[4]=x3;
        viewFourCoordinate[5]=y3;
        viewFourCoordinate[6]=x4;
        viewFourCoordinate[7]=y4;
        Log.i(TAG, "setBoundary "+"view w h:"+param[0]+","+param[1]);
        for(int i=0;i<8;i++){
            Log.i(TAG, "setBoundary "+"viewFourCoordinate:"+viewFourCoordinate[i]);
        }


    }
//判断是否在边界
    private boolean isBoundary(float[] coordinate){
//        Log.i(TAG, "isBoundary:"+"screenWidth"+windowsWight+",screenHeight"+windowsHeight);
        Log.i(TAG, "isBoundary move x y"+coordinate[0]+","+coordinate[1]);
        float x=coordinate[0];
        float y=coordinate[1];
        if(x+viewFourCoordinate[0]>0&&x+viewFourCoordinate[2]<windowsWight&&y+viewFourCoordinate[1]>0&&y+viewFourCoordinate[5]<windowsHeight){
            return true;
        }else {
            return false;
        }
    }


    private float[] getViewWightHeight(){
        float [] viewCoordinate=new float[2];
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int height = view.getMeasuredHeight();
        int width =  view.getMeasuredWidth();
        viewCoordinate[0]=width;
        viewCoordinate[1]=height;
        return viewCoordinate;
    }


    //传入圆心坐标
    public List getCircleData(float x1,float y1,float x0,float y0){
        float number=0.1f;
        float location[]=getCoordinateOnFartherView();
//        float[] location=getCoordinateInFartherView();
        //起始点坐标
//        double x1=location[0];
//        double y1=location[1];
        Log.i(TAG, "getCircleData x y: " + x1+"  ,"+y1);

        double R=Math.sqrt((x1-x0)*(x1-x0)+(y1-y0)*(y1-y0));

        List listX=new ArrayList();
        List listY=new ArrayList();
        List list=new ArrayList();

        double param=Math.abs(y1 - y0)/R;
        if(param<-1.0){
            param=-1.0;
        }else if(param>1.0){
            param=1.0;}

        double a=Math.asin(param);
        if(x1>=x0&&y1>=y0){
            a=a;
        }else if(x1<x0&&y1>=y0){
            a=pi-a;
        }else if(x1<x0&&y1<y0){
            a=a+pi;
        }else {
            a=2*pi-a;
        }

        double i=a+number;

        Log.i(TAG, "getCircleData a: " + a + "  R:" + R);
        while (i<=(a+2*pi)){
            double y,x;
            x=x0+R*Math.cos(i);y=y0+R*Math.sin(i);
//            Log.i(TAG, "getCircleData i cos sin  "+i+"   "+Math.cos(i)+"   "+Math.sin(i));

            listX.add(x);
            listY.add(y);
            double aa=x-x1;
            double bb=y-y1;
//            listX.add(aa);
//            listY.add(bb);
            Log.i(TAG, "getCircleData  a:" +i+ "   x:" + x + "  y:" + y  +"  a:"+ aa+"   b:"+bb);
            x1=(float)x;
            y1=(float)y;
            i=i+number;
        }

        float[] orinage=getCoordinateOnFartherView();
        listX.add((double)orinage[0]);
        listY.add((double)orinage[1]);


        list.add(listX);
        list.add(listY);
        return list;
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
