package com.bcgtgjyb.test.myapplication;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.bcgtgjyb.test.mylibrary.MoveAnimation;


public class MainActivity extends ActionBarActivity {
    private Button button;
    private String TAG="MainActivity";
    private MoveAnimation moveAnimation;
    private float[] viewCoordinate=new float[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.move_button);
        moveAnimation=new MoveAnimation(button);
//        moveAnimation.setRandomAnimation(true);
        Log.d(TAG, "onCreate"+button.getX()+","+button.getY());
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        button.measure(w, h);
        int height = button.getMeasuredHeight();
        int width = button.getMeasuredWidth();
        viewCoordinate[0]=width;
        viewCoordinate[1]=height;

//        moveAnimation.setBoundary(getWindowManager(),viewCoordinate);
//        moveAnimation.setCurveMove(new float[]{200, 200}, 100, 0);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] location=new int[2];
                button.getLocationOnScreen(location);
                Log.i(TAG, "onCreate button x y:" + location[0] + ","+location[1]);
                moveAnimation.setBoundary(getWindowManager(),viewCoordinate);
                Log.i(TAG, "onCreate button x y:"+button.getX()+","+button.getY());
                moveAnimation.setCurveMove(new float[]{300, 300}, 100, 0);
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



}
