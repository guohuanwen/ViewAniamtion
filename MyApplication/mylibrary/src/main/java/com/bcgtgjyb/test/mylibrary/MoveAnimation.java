package com.bcgtgjyb.test.mylibrary;


import android.util.Log;
import android.view.View;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2015/8/10.
 */
public class MoveAnimation {
    private View view;
    /**
     * 运动速度
     */
    private long moveVelocity=50;
    /**
     * 自由运动范围
     */
    private int moveScope=30;
    /**
     * 随机点的个数
     */
    private int coordinateNumber=10;
    private MoveAnimation(){

    }

    public MoveAnimation(View view){
        this.view=view;
    }



    public void setRandomAnimation(boolean param){
        if(param) {
            float myX = view.getX();
            float myY = view.getY();
            List list = getRandomDate(coordinateNumber);
            setViewAnimation(view, (List) list.get(0), (List) list.get(1));
        }
    }

    public void setCurveMove(float[] start,float[] end,int arc,int direction){
        if(true){
            List list=getCurveData(start,end,arc,direction);
            setViewAnimation(view, (List) list.get(0), (List) list.get(1));
        }
    }
    /**
     * make button move to (x,y)
     * @param view
     * @param x
     * @param y
     */
    public void move(View view,float x,float y){
        float nowX=view.getX();
        float nowY=view.getY();
        AnimatorSet animatorSet=new AnimatorSet();
		animatorSet.setDuration(moveVelocity);
        animatorSet.playTogether(//
                ObjectAnimator.ofFloat(view, "translationX", x*10),//
                ObjectAnimator.ofFloat(view, "translationY", y*10));
        animatorSet.setTarget(view);
        animatorSet.start();
    }

    /**
     * make view move to the coordinates which is in Listx and Listy
     * @param view
     * @param listx
     * @param listy
     */
    private void setViewAnimation(final View view, List listx, List listy) {
        List animationList = new ArrayList<Animator>();
        AnimatorSet myAnimation=new AnimatorSet();
        double x=0;
        double y;
        Log.i("MoveAnimation","setViewAnimation:"+listx.size()+";"+listy.size());
        if(listx.size()==0){
            Log.i("MoveAnimation","setViewAnimation:"+"list为空");
            return ;
        }
        for (int i = 0; i < listx.size(); i++) {
            y = (double) listy.get(i);
            x = (double) listx.get(i);

            AnimatorSet animatorSet = new AnimatorSet();
            //持续时间
//            animatorSet.setDuration(moveVelocity);
            Log.i("Animation", "setButtonAnimation++" + x + "---" + y);
            animatorSet.playTogether(//
                    ObjectAnimator.ofFloat(view,"translationX",(float)x), //
                    ObjectAnimator.ofFloat(view, "translationY",(float)y));//
            animationList.add(animatorSet);

        }
        myAnimation.playSequentially(animationList);
        myAnimation.setDuration(moveVelocity);
        myAnimation.setTarget(view);


        myAnimation.addListener(new Animator.AnimatorListener() {
            private boolean mCanceled;

            public void onAnimationStart(Animator animation) {
                mCanceled = false;

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (!mCanceled) {
                    animation.start();
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCanceled = true;
            }
        });
        myAnimation.start();

    }

    /**
     * 模拟随机运动，返回运动坐标
     * 以初始位置为中心，以定值为半径做圆，在圆内生成随机数，返回一串随即数，最后一个为初始位置
     * @param param
     * @return
     */
    private List<List> getRandomDate(int param) {
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
    private List<List<Double>> getCurveData(float[] start,float[] end,int arc,int direction){
        int coordinateNumber=50;
        List listX=new ArrayList<Long>();
        List listY=new ArrayList<Long>();
        //计算都是用的matlab，有需要都可以学习一下

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
        Log.i("MoveAnimation","x1="+x1+",y1="+y1+",x2="+x2+",y2="+y2+",arc="+arc+",x3="+x3+",y3="+y3);
        //三点求圆的方程
        double a,b,c;
        a =-(x1*x1*y2 - x1*x1*y3 - x2*x2*y1 + x2*x2*y3 + x3*x3*y1 - x3*x3*y2 + y1*y1*y2 - y1*y1*y3 - y1*y2*y2 + y1*y3*y3 + y2*y2*y3 - y2*y3*y3)/(x1*y2 - x2*y1 - x1*y3 + x3*y1 + x2*y3 - x3*y2);
        b =-(x1*x1*x3 - x1*x1*x2 + x1*x2*x2 - x1*x3*x3 + x1*y2*y2 - x1*y3*y3 - x2*x2*x3 + x2*x3*x3 - x2*y1*y1 + x2*y3*y3 + x3*y1*y1 - x3*y2*y2)/(x1*y2 - x2*y1 - x1*y3 + x3*y1 + x2*y3 - x3*y2);
        c =(x1*x1*x3*y2 - x1*x1*x2*y3 + x1*x2*x2*y3 - x1*x3*x3*y2 + x1*y2*y2*y3 - x1*y2*y3*y3 - x2*x2*x3*y1 + x2*x3*x3*y1 - x2*y1*y1*y3 + x2*y1*y3*y3 + x3*y1*y1*y2 - x3*y1*y2*y2)/(x1*y2 - x2*y1 - x1*y3 + x3*y1 + x2*y3 - x3*y2);

        Log.i("MoveAnimation","a="+a+",b="+b+",c="+c);
        double x,y;
        x=x1;
        y=y1;
        int left=0;
        if(x1>x2){
            left=1;
        }else{
            left=0;
        }

        if(left==0){
            //x1<=x2
            double moveX=(x2-x1)/coordinateNumber;
            double moveNub=x1+moveX;
            for(int i=0;i<coordinateNumber;i++){
                if (moveNub<=x2){
                    double[] re=claculateMoveCoordinate(x,y,moveX,a,b,c);
                    x=re[0];
                    y=re[1];
                    moveNub=moveNub+moveX;
                    listX.add(x);
                    listY.add(y);
                }else{
                    moveX=x2-(moveNub-moveX);
                    double[] re=claculateMoveCoordinate(x,y,moveX,a,b,c);
                    x=re[0];
                    y=re[1];
                    listX.add(x);
                    listY.add(y);
                    break;
                }
            }
        }else{
            //x1>x2
            double moveX=(x2-x1)/coordinateNumber;
            double moveNub=x1+moveX;
            for(int i=0;i<coordinateNumber;i++){
                if (moveNub>=x2){
                    double[] re=claculateMoveCoordinate(x,y,moveX,a,b,c);
                    x=re[0];
                    y=re[1];
                    moveNub=moveNub+moveX;
                    listX.add(x);
                    listY.add(y);
                }else{
                    moveX=x2-(moveNub-moveX);
                    double[] re=claculateMoveCoordinate(x,y,moveX,a,b,c);
                    x=re[0];
                    y=re[1];
                    listX.add(x);
                    listY.add(y);
                    break;
                }
            }
        }


        List list=new ArrayList<List>();
        list.add(listX);
        list.add(listY);
        return list;
    }


    private double[] claculateMoveCoordinate(double startX,double startY,double moveX,double a,double b,double c){
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


}
