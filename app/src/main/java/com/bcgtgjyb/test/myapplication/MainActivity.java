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
        button=(Button)findViewById(R.id.move_button);
        returnButton=(Button)findViewById(R.id.button);
        next=(Button)findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Test_1.class);
                startActivity(intent);
            }
        });

        moveAnimation=new MyAnimation(button);
//        moveAnimation.setRandomAnimation(true);
//        Log.d(TAG, "onCreate"+button.getX()+","+button.getY());

        WindowManager wm=getWindowManager();
        boundaryXY[0]=wm.getDefaultDisplay().getWidth();
        boundaryXY[1]=wm.getDefaultDisplay().getHeight();
        final MovePath movePath=new MovePath();


        list=movePath.getCurveData(new float[]{200, 200}, 40, 1);







//        moveAnimation.setBoundary(getWindowManager(),viewCoordinate);
//        moveAnimation.setCurveMove(new float[]{200, 200}, 100, 0);
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
//                moveAnimation.setTranslation(button, listX, listY, 1000).start();

                movePath.setBoundary(button,true, boundaryXY);

//                animation.add(a1);

                my.playTogether(animation);
                my.start();

//                moveAnimation.setViewAnimationOnce(button, aniamtionList);
//                moveAnimation.setCurveMove(new float[]{600, 600}, 300, 0);
//                moveAnimation.setCircleMove(0,0);
//                moveAnimation.setAlpha(0.1f);
//                moveAnimation.setAlpha(1f);
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
                animation.add(moveAnimation.setRotationX(button,0,1000));
                moveAnimation.playTogether(animation).start();
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
