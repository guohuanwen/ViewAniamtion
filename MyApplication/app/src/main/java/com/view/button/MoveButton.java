package com.view.button;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

/**
 * Created by Administrator on 2015/8/6.
 */
public class MoveButton extends Button {
    private Context context;
    /**
     * 自由运动范围
     */
    private int moveScope=30;
    /**
     * 随机点的个数
     */
    private int coordinateNumber=10;
    /**
     * 是否随机移动
     */
    private boolean isMoved=true;

    public void setIsMove(boolean param){
        this.isMoved=param;
    }


    public void setMoveScope(int moveScope) {
        this.moveScope = moveScope;
    }

    public void setCoordinateNumber(int coordinateNumber) {
        this.coordinateNumber = coordinateNumber;
    }




    public MoveButton(Context context){
        super(context);
        this.context=context;
    }

    public MoveButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setAnimation();
    }

    /**
     * 外部调用
     * 为button设置运动动画
     */
    private void setAnimation(){
        if(isMoved) {
            float myX = this.getX();
            float myY = this.getY();
            List list = getDate(coordinateNumber);
            setButtonAnimation(this, (List) list.get(0), (List) list.get(1));
        }
    }

    /**
     * 让button移动到指定位置
     * @param but
     * @param x
     * @param y
     */
    public void move(Button but,float x,float y){
        float nowX=but.getX();
        float nowY=but.getY();
        AnimatorSet animatorSet=new AnimatorSet();
//		animatorSet.setDuration(10);
        animatorSet.playTogether(//
                ObjectAnimator.ofFloat(but, "translationX", x * 10),//
                ObjectAnimator.ofFloat(but, "translationY", y*10));
        animatorSet.setTarget(but);
        animatorSet.start();
    }

    /**
     * 让button 按指定路径运动
     * @param button
     * @param listx
     * @param listy
     */
    private void setButtonAnimation(final Button button, List listx, List listy) {
        List animationList = new ArrayList<Animator>();
        AnimatorSet myAnimation=new AnimatorSet();
        float x = 0;
        float y;
        for (int i = 0; i < listx.size(); i++) {
            y = (float) listy.get(i);
            x = (float) listx.get(i);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(1500);
            Log.i("Animation", "setButtonAnimation++" + x + "---" + y);
            animatorSet.playTogether(//
                    ObjectAnimator.ofFloat(button,"translationX",x), //
                    ObjectAnimator.ofFloat(button, "translationY",y));//
            animationList.add(animatorSet);

        }
        myAnimation.playSequentially(animationList);
        myAnimation.setTarget(button);


        myAnimation.addListener(new Animator.AnimatorListener() {
            private boolean mCanceled;
            public void onAnimationStart(Animator animation) {
                mCanceled=false;

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
                mCanceled=true;
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
    private List<List> getDate(int param) {
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
    
}
