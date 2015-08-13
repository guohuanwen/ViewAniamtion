package com.bcgtgjyb.test.myapplication;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;

import com.bcgtgjyb.test.mylibrary.MoveAnimation;


public class MainActivity extends ActionBarActivity {
    private Button button;
    private String TAG="MainActivity";
    private MoveAnimation moveAnimation;
    private float[] viewCoordinate=new float[2];
    private int[] boundaryXY=new int[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.move_button);
        moveAnimation=new MoveAnimation(button);
//        moveAnimation.setRandomAnimation(true);
//        Log.d(TAG, "onCreate"+button.getX()+","+button.getY());

        WindowManager wm=getWindowManager();
        boundaryXY[0]=wm.getDefaultDisplay().getWidth();
        boundaryXY[1]=wm.getDefaultDisplay().getHeight();







//        moveAnimation.setBoundary(getWindowManager(),viewCoordinate);
//        moveAnimation.setCurveMove(new float[]{200, 200}, 100, 0);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                moveAnimation.setBoundary(false,boundaryXY);
//                moveAnimation.setCurveMove(new float[]{600, 600}, 700, 0);
                moveAnimation.setCircleMove(300,300);
            }
        });
        //是否自由移动
//        moveButton.setIsMove(true);
        //设置自由移动坐标数量
//        moveButton.setCoordinateNumber(10);
        //设置自由运动范围
//        moveButton.setMoveScope(30);
        //设置动画持续时间，改变速度（测试在联想A390t（android4.0.3）上速度无法设置）
//        moveButton.setMoveVelocity(1500);


//        button.getViewTreeObserver().addOnGlobalLayoutListener(
//                new ViewTreeObserver.OnGlobalLayoutListener()
//                {
//                    @SuppressLint("NewApi")
//                    public void onGlobalLayout()
//                    {
//                        // Now you may get the left/top/etc.
//                        int[] location=new int[2];
//                        button.getLocationOnScreen(location);
//                        // Optionally remove the listener so future layouts don't change the value
//                        button.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                    }
//                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        int[] location=new int[2];
        button.getLocationOnScreen(location);
        Log.i(TAG, "onResume x y:" + location[0]+","+location[1]);

    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            int[] location=new int[2];
            button.getLocationOnScreen(location);
            Log.i(TAG, "onWindowFocusChanged x y:"+location[0]+","+location[1]);
        }
    }
}
