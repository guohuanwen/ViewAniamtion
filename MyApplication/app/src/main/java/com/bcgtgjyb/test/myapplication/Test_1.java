package com.bcgtgjyb.test.myapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.Button;
import com.bcgtgjyb.test.mylibrary.MovePath;
import com.bcgtgjyb.test.mylibrary.MyAnimation;
import com.nineoldandroids.animation.AnimatorSet;

import java.util.ArrayList;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_1);
        init();

    }

    private void init() {
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
        b5=(Button)findViewById(R.id.button5);
        b1.setOnClickListener(new MyButtonListener());
    }

    class MyButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            float y=b2.getY();
            float x=b1.getX();
            float[] coordinate=new float[]{x,y};
            MovePath movePath=new MovePath();

            List b3List=movePath.getCurveData(getTranslation(b3, coordinate), 50, 1);
            List b2List=movePath.getCurveData(getTranslation(b2, coordinate), 50, 1);
            List b4List=movePath.getCurveData(getTranslation(b4, coordinate), 50, 1);
            List b5List=movePath.getCurveData(getTranslation(b5, coordinate), 50, 0);

            List animationList=new ArrayList();
            MyAnimation myAnimation = new MyAnimation();
            animationList.add(myAnimation.setAlpha(b2,0,1000));
            animationList.add(myAnimation.setAlpha(b3,0,1000));
            animationList.add(myAnimation.setTranslation(b3, (List) b3List.get(0), (List) b3List.get(1), 1000));
            animationList.add(myAnimation.setTranslation(b2, (List) b2List.get(0), (List) b2List.get(1), 1000));
            animationList.add(myAnimation.setAlpha(b4,0,1000));
            animationList.add(myAnimation.setAlpha(b5,0,1000));
            animationList.add(myAnimation.setTranslation(b4, (List) b4List.get(0), (List) b4List.get(1), 1000));
            animationList.add(myAnimation.setTranslation(b5, (List) b5List.get(0), (List) b5List.get(1), 1000));
            myAnimation.playTogether(animationList).start();




        }
    }

    private float[] getTranslation(View view,float[] endCoordinate){

        float x=view.getX();
        float y=view.getY();
        float translationX=endCoordinate[0]-x;
        float translationY=endCoordinate[1]-y;
        Log.i(TAG, "getTranslation translationX Y"+translationX+"   "+translationY);
        return new float[]{translationX,translationY};
    }



}
