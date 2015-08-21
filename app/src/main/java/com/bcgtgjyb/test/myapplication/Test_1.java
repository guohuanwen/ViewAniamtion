package com.bcgtgjyb.test.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.bcgtgjyb.test.mylibrary.AnimationOnceListener;
import com.bcgtgjyb.test.mylibrary.MovePath;
import com.bcgtgjyb.test.mylibrary.MyAnimation;
import com.nineoldandroids.animation.AnimatorSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2015/8/17.
 */
public class Test_1 extends ActionBarActivity {
    private  String TAG="Test_1";
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    Handler handler;
    Runnable runnable;
    List b3List,b2List,b4List,b5List,b3ListX,b2ListX,b4ListX,b5ListX,b3ListY,b2ListY,b4ListY,b5ListY;
    int open=-1;
    MovePath m=new MovePath();
    AnimatorSet animatorSet=new AnimatorSet();
    MyAnimation a=new MyAnimation();
    List l2=m.getRandomDate(30, 1);
    List l3=m.getRandomDate(30, 2);
    List l4=m.getRandomDate(30, 3);
    List l5=m.getRandomDate(30, 4);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_1);
        init();

    }


    private void setRondomMove(){
        b2.clearAnimation();
        b3.clearAnimation();
        b4.clearAnimation();
        b5.clearAnimation();
        List list=new ArrayList();
        list.add(a.setTranslation(b4,(List)l4.get(0),(List)l4.get(1),3000));
        list.add(a.setTranslation(b2, (List) l2.get(0), (List) l2.get(1), 3000));
        list.add(a.setTranslation(b3,(List)l3.get(0),(List)l3.get(1),3000));
        list.add(a.setTranslation(b5,(List)l5.get(0),(List)l5.get(1),3000));
        animatorSet=a.playTogether(list);
        animatorSet.setDuration(10000);
        animatorSet.addListener(new AnimationOnceListener());
//        animatorSet.start();
        handler.postDelayed(runnable, animatorSet.getDuration());
        animatorSet.start();
    }

    private void init() {
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
        b5=(Button)findViewById(R.id.button5);
        b1.setOnClickListener(new MyButtonListener());
        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                b2.clearAnimation();
                b3.clearAnimation();
                b4.clearAnimation();
                b5.clearAnimation();
                setRondomMove();
            }
        };
        setRondomMove();
    }


    class MyButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            b2.clearAnimation();
            b3.clearAnimation();
            b4.clearAnimation();
            b5.clearAnimation();
            if(open==-1){
                //初始化数据
                float y=(b2.getY()+b4.getY())/2;
                float x=b1.getX();
                float[] coordinate=new float[]{x,y};
                MovePath movePath=new MovePath();
                b3List=movePath.getCurveData(getTranslation(b3, coordinate), 40, 1);
                b2List=movePath.getCurveData(getTranslation(b2, coordinate), 40, 1);
                b4List=movePath.getCurveData(getTranslation(b4, coordinate), 40, 1);
                b5List=movePath.getCurveData(getTranslation(b5, coordinate), 40, 1);

                b2ListX=(List)b2List.get(0);
                b3ListX=(List)b3List.get(0);
                b4ListX=(List)b4List.get(0);
                b5ListX=(List)b5List.get(0);
                b2ListY=(List)b2List.get(1);
                b3ListY=(List)b3List.get(1);
                b4ListY=(List)b4List.get(1);
                b5ListY=(List)b5List.get(1);

                setClose();
            }
            else if(open==1){
                Collections.reverse(b2ListX);
                Collections.reverse(b3ListX);
                Collections.reverse(b4ListX);
                Collections.reverse(b5ListX);
                Collections.reverse(b2ListY);
                Collections.reverse(b3ListY);
                Collections.reverse(b4ListY);
                Collections.reverse(b5ListY);
                setClose();
            }else if(open==0){
                setOpen();
            }






        }
    }

    private void setClose(){
        handler.removeCallbacks(runnable);
        List animationList=new ArrayList();
        MyAnimation myAnimation = new MyAnimation();
        animationList.add(myAnimation.setAlpha(b2,0,1000));
        animationList.add(myAnimation.setAlpha(b3, 0, 1000));
        animationList.add(myAnimation.setTranslation(b2, b2ListX, b2ListY, 1000));
        animationList.add(myAnimation.setTranslation(b3, b3ListX, b3ListY, 1000));

        animationList.add(myAnimation.setAlpha(b4, 0, 1000));
        animationList.add(myAnimation.setAlpha(b5, 0, 1000));
        animationList.add(myAnimation.setTranslation(b4, b4ListX, b4ListY, 1000));
        animationList.add(myAnimation.setTranslation(b5, b5ListX, b5ListY, 1000));

        animationList.add(myAnimation.setScaleX(b2, 0.2f, 1000));
        animationList.add(myAnimation.setScaleY(b2, 0.2f, 1000));

        animationList.add(myAnimation.setScaleX(b3, 0.2f, 1000));
        animationList.add(myAnimation.setScaleY(b3, 0.2f, 1000));

        animationList.add(myAnimation.setScaleX(b4, 0.2f, 1000));
        animationList.add(myAnimation.setScaleY(b4, 0.2f, 1000));

        animationList.add(myAnimation.setScaleX(b5, 0.2f, 1000));
        animationList.add(myAnimation.setScaleY(b5, 0.2f, 1000));
        myAnimation.playTogether(animationList).start();
        open=0;
    }

    private void setOpen(){
        Collections.reverse(b2ListX);
        Collections.reverse(b3ListX);
        Collections.reverse(b4ListX);
        Collections.reverse(b5ListX);
        Collections.reverse(b2ListY);
        Collections.reverse(b3ListY);
        Collections.reverse(b4ListY);
        Collections.reverse(b5ListY);
        List animationList = new ArrayList();
        MyAnimation myAnimation = new MyAnimation();
        animationList.add(myAnimation.setAlpha(b2,1, 1000));
        animationList.add(myAnimation.setAlpha(b3, 1, 1000));
        animationList.add(myAnimation.setTranslation(b2, b2ListX, b2ListY, 1000));
        animationList.add(myAnimation.setTranslation(b3, b3ListX, b3ListY, 1000));
        animationList.add(myAnimation.setAlpha(b4, 1, 1000));
        animationList.add(myAnimation.setAlpha(b5, 1, 1000));
        animationList.add(myAnimation.setTranslation(b4, b4ListX, b4ListY, 1000));
        animationList.add(myAnimation.setTranslation(b5, b5ListX, b5ListY, 1000));
        animationList.add(myAnimation.setScaleX(b2, 1f, 1000));
        animationList.add(myAnimation.setScaleY(b2, 1f, 1000));
        animationList.add(myAnimation.setScaleX(b3, 1f, 1000));
        animationList.add(myAnimation.setScaleY(b3,1f,1000));
        animationList.add(myAnimation.setScaleX(b4, 1f, 1000));
        animationList.add(myAnimation.setScaleY(b4,1f,1000));
        animationList.add(myAnimation.setScaleX(b5, 1f, 1000));
        animationList.add(myAnimation.setScaleY(b5,1f,1000));
        AnimatorSet animatorSet=myAnimation.playTogether(animationList);
        animatorSet.setDuration(1000);

        open=1;

        new Handler().postDelayed(new Runnable() {
            public void run() {
                b2.clearAnimation();
                b3.clearAnimation();
                b4.clearAnimation();
                b5.clearAnimation();
                setRondomMove();
            }
        }, animatorSet.getDuration());
        animatorSet.start();
    }

    private float[] getTranslation(View view,float[] endCoordinate){

        float x=view.getX();
        float y=view.getY();
        float translationX=endCoordinate[0]-x;
        float translationY=endCoordinate[1]-y;
        Log.i(TAG, "getTranslation translationX Y"+translationX+"   "+translationY);
        return new float[]{translationX,translationY};
    }

    private  void logList(List l){
        List listX=new ArrayList();
        List listY=new ArrayList();
        for (int i=0;i<l.size();i++){
            listX=(List)l.get(0);
            listY=(List)l.get(1);
        }
        for (int i=0;i<listX.size();i++){
            Log.i(TAG, "logList x y  "+ listX.get(i)+"   "+listY.get(i));
        }
    }



}
