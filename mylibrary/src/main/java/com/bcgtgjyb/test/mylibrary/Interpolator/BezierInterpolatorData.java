package com.bcgtgjyb.test.mylibrary.Interpolator;

import android.util.Log;

/**
 * Created by huanwen on 2015/9/1.
 */
public class BezierInterpolatorData {
    private final String TAG="BezierInterpolatorData";

    /**
     * use the default rate of change (rate=3)
     * @param param （all number must be in 0-1）
     * @param direction 0 is slow,1 is quick,direction.length=param.length
     * @param x coordinate of x
     * @return coordinate of y
     */
    public  float bezierDataWithoutRate(float[] param,int[] direction,float x){
        int number=direction.length;
        float [] changeRate=new float[number];
        int i=0;
        while (i<number){
            changeRate[i]=3;
            i++;
        }
        return bezierData(param,direction,changeRate,x);
    }

    /**
     *
     * @param param （all number must be in 0-1）
     * @param direction 0 is slow,1 is quick,direction.length=param.length
     * @param changeRate rate of change (all number must be in 0-5)
     * @param x coordinate of x
     * @return coordinate of y
     */
    public float bezierData(float[] param,int[] direction,float[]changeRate,float x){
        int i=1;
        float small=0;
        float big=0;
        while (true){
            if(param[i]>x){
                small=param[i-1];
                big=param[i];
                break;
            }
            i++;
            if(i>=param.length){
                i--;
                break;
            }
        }
        Log.i(TAG, "getData small big  " + small + "   " + big + "  " + x);
        return getBezierData(small,big,changeRate[i-1],direction[i-1],x);
    }


    //本类中只使用y=x上的点，如需其他点，请自定义
    private float getBezierData(float x0,float x1,float changeRate,int direction,float x){
        return getBezierCurve(new float[]{x0,x0},new float[]{x1,x1},changeRate,direction,x);
    }

    //任意两点(x0,y0)(x1,y1)及(x0,y1)做贝塞尔曲线，
    // 传入两点坐标，
    // 所需点的x坐标，
    //变化率 1-5之间
    //弯曲方向  0为向上弯曲，1向下弯曲
    // 返回所需点的y坐标
    /**
     *
     * @param param0 start coordinate
     * @param param1 end coordinate
     * @param changeRate rate of change (all number must be in 1-5)
     * @param direction 0 is slow,1 is quick
     * @param x coordinate of x
     * @return coordinate of y
     */
    private float getBezierCurve(float param0[],float param1[],float changeRate,int direction,float x){
        float x0=param0[0];
        float y0=param0[1];
        float x2=param1[0];
        float y2=param1[1];
        float x1;
        float y1;
        float dd=(x2-x0)/10;
        float xx0=x0;
        float yy0=y2;
        float xx2=x2;
        float yy2=y0;
        changeRate=changeRate>5?5:changeRate;
        changeRate=changeRate<0?0:changeRate;
        if(direction==0){
            x1=(x2-xx0)/2-changeRate*dd+x0;
        }else{
            x1=(x2-xx0)/2+changeRate*dd+x0;
        }
        y1=(x1*yy0 - x1*yy2 + xx0*yy2 - xx2*yy0)/(xx0 - xx2);
//        Log.i(TAG, "getBezierCurve x y" + x1+"  "+y1);

        float t=0;
        t=(float)(x0 - x1 + Math.sqrt(x1 * x1 - 2. * x * x1 + x * x0 + x * x2 - x0 * x2))/(x0 - 2*x1 + x2);
//        float x=(1-t)*(1-t)*x0+2*t*(1-t)*x1+t*t*x2;
        float y=(1-t)*(1-t)*y0+2*t*(1-t)*y1+t*t*y2;
//        Log.i(TAG, "getBezierCurve y  "+y);
        return y;
    }
}
