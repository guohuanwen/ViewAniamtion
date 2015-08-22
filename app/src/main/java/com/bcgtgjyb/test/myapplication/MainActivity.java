package com.bcgtgjyb.test.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import com.bcgtgjyb.test.mylibrary.MovePath;
import com.bcgtgjyb.test.mylibrary.MyAnimation;
import com.nineoldandroids.animation.AnimatorSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private Button button;
    private Button circle;
    private String TAG="MainActivity";
    private MyAnimation moveAnimation;
    private float[] viewCoordinate=new float[2];
    private int[] boundaryXY=new int[2];
    private Button returnButton;
    private List list=new ArrayList();
    private int is=0;
    private Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Test_1.class);
                startActivity(intent);
            }
        });

        moveAnimation=new MyAnimation(button);

        WindowManager wm=getWindowManager();
        boundaryXY[0]=wm.getDefaultDisplay().getWidth();
        boundaryXY[1]=wm.getDefaultDisplay().getHeight();
        final MovePath movePath=new MovePath();
        list=movePath.getCurveData(new float[]{-100, 0},30, 1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorSet my=new AnimatorSet();
                List listX=(List)list.get(0);
                List listY=(List)list.get(1);
                if(is==1){
                    Collections.reverse(listX);
                    Collections.reverse(listY);
                    is=0;
                }
                List animation=new ArrayList();
                animation.add(moveAnimation.setAlpha(button, 0.2f, 1000));
                animation.add(moveAnimation.setRotation(button, 360, 1000));
                animation.add(moveAnimation.setScaleX(button, 2, 1000));
                animation.add(moveAnimation.setTranslation(button, listX, listY, 1000));
                animation.add(moveAnimation.setRotationX(button,360,1000));
                movePath.setBoundary(button,true, boundaryXY);
                my.playTogether(animation);
                my.start();
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List listX=(List)list.get(0);
                List listY=(List)list.get(1);
                if(is==0){
                    Collections.reverse(listX);
                    Collections.reverse(listY);
                    is=1;
                }
                List animation=new ArrayList();
                animation.add(moveAnimation.setAlpha(button, 1, 1000));
                animation.add(moveAnimation.setRotation(button, 0, 1000));
                animation.add(moveAnimation.setScaleX(button, 1, 1000));
                animation.add(moveAnimation.setTranslation(button, listX, listY, 1000));
                animation.add(moveAnimation.setRotationX(button, 0, 1000));
                moveAnimation.playTogether(animation).start();
            }
        });

        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List list = movePath.getCircleData(circle, new float[]{50, 50});
                moveAnimation.setTranslation(circle,(List)list.get(0),(List)list.get(1),1000).start();

            }
        });

    }

    private void init() {
        button=(Button)findViewById(R.id.move_button);
        returnButton=(Button)findViewById(R.id.button);
        next=(Button)findViewById(R.id.next);
        circle=(Button)findViewById(R.id.circle);
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
