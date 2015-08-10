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
    private long moveVelocity=100;
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
            setButtonAnimation(view, (List) list.get(0), (List) list.get(1));
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
    private void setButtonAnimation(final View view, List listx, List listy) {
        List animationList = new ArrayList<Animator>();
        AnimatorSet myAnimation=new AnimatorSet();
        float x = 0;
        float y;
        for (int i = 0; i < listx.size(); i++) {
            y = (float) listy.get(i);
            x = (float) listx.get(i);
            AnimatorSet animatorSet = new AnimatorSet();
            //持续时间
//            animatorSet.setDuration(moveVelocity);
            Log.i("Animation", "setButtonAnimation++" + x + "---" + y);
            animatorSet.playTogether(//
                    ObjectAnimator.ofFloat(view,"translationX",x), //
                    ObjectAnimator.ofFloat(view, "translationY",y));//
            animationList.add(animatorSet);

        }
        myAnimation.playSequentially(animationList);
        myAnimation.setDuration(moveVelocity*coordinateNumber);
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


    private List<List<Long>> getCurveData(long[] start,long[] end,long[] arc){
        List listX=new ArrayList<Long>();
        List listY=new ArrayList<Long>();


        return null;
    }


}
