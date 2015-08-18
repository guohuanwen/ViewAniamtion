package com.bcgtgjyb.test.mylibrary;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2015/8/17.
 */
public class MovePath {
    private String TAG="MovePath";
    private final double pi=Math.PI;
    boolean boundary=false;
    /**
     * 随机点的个数
     */
    private int coordinateNumber=50;
    //屏幕宽高
    private int windowsWight=0;
    private int windowsHeight=0;

    private float viewFourCoordinate[]={0,0,0,0,0,0,0,0};



    /**
     * 模拟随机运动，返回运动坐标
     * 以初始位置为中心，以定值为半径做圆，在圆内生成随机数，返回一串随即数，最后一个为初始位置
     * @param param
     * @return
     */
    public List<List> getRandomDate(int moveScope,int param) {
        List x = new ArrayList<Object>();
        List y = new ArrayList<Object>();
        Random random = new Random();
        //定义半径
        float R = moveScope;
        double x1=0;
        double y1=R;
        double x2=-Math.cos(18)*R;
        double y2=Math.sin(18)*R;
        double x3=-Math.cos(54)*R;
        double y3=-Math.sin(54)*R;
        double x4=Math.cos(54)*R;
        double y4=-Math.sin(54)*R;
        double x5=Math.cos(18)*R;
        double y5=Math.sin(18)*R;

        switch (param){
            case 1:
                x.add((double)0);
                x.add(x1);
                x.add(x3);
                x.add(x5);
                x.add(x2);
                x.add(x4);
                x.add((double)0);

                y.add((double)0);
                y.add(y1);
                y.add(y3);
                y.add(y5);
                y.add(y2);
                y.add(y4);
                y.add((double)0);
                break;
            case 2:
                x.add((double)0);
//                x.add(x1);
                x.add(x3);
                x.add(x5);
                x.add(x2);
                x.add(x4);
                x.add(x1);
                x.add(x3);
                x.add((double)0);
                y.add((double)0);
//                y.add(y1);
                y.add(y3);
                y.add(y5);
                y.add(y2);
                y.add(y4);
                y.add(y1);
                y.add(y3);
                y.add((double)0);
                break;
            case 3:
                x.add((double)0);
//                x.add(x1);
//                x.add(x3);
                x.add(x5);
                x.add(x2);
                x.add(x4);
                x.add(x1);
                x.add(x3);
                x.add(x5);
                x.add((double)0);
                y.add((double)0);
//                y.add(y1);
//                y.add(y3);
                y.add(y5);
                y.add(y2);
                y.add(y4);
                y.add(y1);
                y.add(y3);
                y.add(y5);
                y.add((double)0);
                break;
            case 4:
                x.add((double)0);
//                x.add(x1);
//                x.add(x3);
//                x.add(x5);
                x.add(x2);
                x.add(x4);
                x.add(x1);
                x.add(x3);
                x.add(x5);
                x.add(x2);
                x.add((double)0);
                y.add((double)0);
//                y.add(y1);
//                y.add(y3);
//                y.add(y5);
                y.add(y2);
                y.add(y4);
                y.add(y1);
                y.add(y3);
                y.add(y5);
                y.add(y2);
                y.add((double)0);
                break;
            default:
                x.add((double)0);
//                x.add(x1);
//                x.add(x3);
//                x.add(x5);
//                x.add(x2);
                x.add(x4);
                x.add(x1);
                x.add(x3);
                x.add(x5);
                x.add(x2);
                x.add(x4);
                x.add((double)0);
                y.add((double)0);
//                y.add(y1);
//                y.add(y3);
//                y.add(y5);
//                y.add(y2);
                y.add(y4);
                y.add(y1);
                y.add(y3);
                y.add(y5);
                y.add(y2);
                y.add(y4);
                y.add((double)0);
                break;
        }


        List re = new ArrayList<List>();
        re.add(x);
        re.add(y);
        return re;
    }


    /**
     * 获取曲线数据
     * @param end
     * @param arc
     * @param direction
     * @return
     */
    public List<List<Double>> getCurveData(float[] end,int arc,int direction){

        List listX=new ArrayList<Long>();
        List listY=new ArrayList<Long>();
        //计算用matlab，有需要可以学习一下

        //起始点坐标
        double x1=0;
        double y1=0;
        //结束点坐标
        double x2=end[0];
        double y2=end[1];

        //圆弧中点
        double x3,y3;
        double x31=-(2*y1*(y1/2 + y2/2 + arc*x1*Math.sqrt(1/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2)) - arc*x2*Math.sqrt(1/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2))) - 2*y2*(y1/2 + y2/2 + arc*x1*Math.sqrt(1/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2)) - arc*x2*Math.sqrt(1/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2))) - x1*x1 + x2*x2 - y1*y1 + y2*y2)/(2*x1 - 2*x2);
        double y31=y1/2 + y2/2 + arc*x1*Math.sqrt(1/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2)) - arc*x2*Math.sqrt(1/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2));
        double x32=-(2*y1*(y1/2 + y2/2 - arc*x1*Math.sqrt(1/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2)) + arc*x2*Math.sqrt(1/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2))) - 2*y2*(y1/2 + y2/2 - arc*x1*Math.sqrt(1/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2)) + arc*x2*Math.sqrt(1/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2))) - x1*x1 + x2*x2 - y1*y1 + y2*y2)/(2*x1 - 2*x2);
        double y32=y1/2 + y2/2 - arc*x1*Math.sqrt(1/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2)) + arc*x2*Math.sqrt(1/(x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2));


            if((x2>x1&&direction==0)||(x2<=x1&&direction==1)){
                if(y32>y31){
                    y3=y32;
                    x3=x32;
                }else {
                    y3=y31;
                    x3=x31;
                }
            }
            else {
                if(y32<y31){
                    y3=y32;
                    x3=x32;
                }else {
                    y3=y31;
                    x3=x31;
                }
            }


//        Log.i("MoveAnimation","x1="+x1+",y1="+y1+",x2="+x2+",y2="+y2+",arc="+arc+",x3="+x3+",y3="+y3);
        //三点求圆的方程
        double a,b,c;
        a =-(x1*x1*y2 - x1*x1*y3 - x2*x2*y1 + x2*x2*y3 + x3*x3*y1 - x3*x3*y2 + y1*y1*y2 - y1*y1*y3 - y1*y2*y2 + y1*y3*y3 + y2*y2*y3 - y2*y3*y3)/(x1*y2 - x2*y1 - x1*y3 + x3*y1 + x2*y3 - x3*y2);
        b =-(x1*x1*x3 - x1*x1*x2 + x1*x2*x2 - x1*x3*x3 + x1*y2*y2 - x1*y3*y3 - x2*x2*x3 + x2*x3*x3 - x2*y1*y1 + x2*y3*y3 + x3*y1*y1 - x3*y2*y2)/(x1*y2 - x2*y1 - x1*y3 + x3*y1 + x2*y3 - x3*y2);
        c =(x1*x1*x3*y2 - x1*x1*x2*y3 + x1*x2*x2*y3 - x1*x3*x3*y2 + x1*y2*y2*y3 - x1*y2*y3*y3 - x2*x2*x3*y1 + x2*x3*x3*y1 - x2*y1*y1*y3 + x2*y1*y3*y3 + x3*y1*y1*y2 - x3*y1*y2*y2)/(x1*y2 - x2*y1 - x1*y3 + x3*y1 + x2*y3 - x3*y2);
        double x,y;
        x=x1;
        y=y1;
        int small=0;
        if((x2>x1&&direction==0)||(x2<=x1&&direction==1)){
            small=0;
        }else {
            small=1;
        }
//        listX.add((double)0);
//        listY.add((double)0);
            double moveX=(x2-x1)/coordinateNumber;
            double moveNub=x1;
            for(int i=0;i<coordinateNumber;i++){
                if ((moveNub<=x2&&x1<=x2)||(moveNub>x2&&x1>x2)){
                    double[] re=calculateMoveCoordinate(moveNub,a,b,c,small);
                    x=re[0];
                    y=re[1];
                    moveNub=moveNub+moveX;
                    if(boundary){
                        if(isBoundary(new float[]{(float)(Math.abs(x)),(float)(Math.abs(y))})){
                            listX.add(x);
                            listY.add(y);
                        }
                    }else{
                        listX.add(x);
                        listY.add(y);
                    }


                }else{
                    double[] re=calculateMoveCoordinate(moveNub,a,b,c,small);
                    x=re[0];
                    y=re[1];
                    if(boundary){
                        if(isBoundary(new float[]{(float)(Math.abs(x)),(float)(Math.abs(y))})){
                            listX.add(x);
                            listY.add(y);
                        }
                    }else{
                        listX.add(x);
                        listY.add(y);
                    }
                    break;
                }
            }

        List list=new ArrayList<List>();
        list.add(listX);
        list.add(listY);
        return list;
    }

    public void setBoundary(View view,boolean boundary,int[] boundaryXY) {
        windowsWight = boundaryXY[0];
        windowsHeight = boundaryXY[1];
        if(boundary){
            setMyBoundary(view);
        }
        this.boundary = boundary;
    }

    /**
     * 给定x计算圆上坐标
     * @param moveX
     * @param a
     * @param b
     * @param c
     * @return
     */
    private double[] calculateMoveCoordinate(double moveX,double a,double b,double c,int small){
        double coordinate[]={0,0};
        double x=moveX;

        double yy1=- b/2 - Math.sqrt(b*b - 4*x*x - 4*a*x - 4*c)/2;
        double yy2=Math.sqrt(b*b - 4*x*x - 4*a*x - 4*c)/2 - b/2;

        coordinate[0]=x;
        if((small==0&&yy1>yy2)||(small==1&&yy1<=yy2)){
            coordinate[1]=yy1;
        }
        else{
            coordinate[1]=yy2;
        }
        return coordinate;
    }

    //获取屏幕宽高，获取view四点坐标
    private void setMyBoundary(View view){

//        float[] location=getCoordinateInScreen();
        float[] location=getCoordinateOnFartherView(view);

        float param[]=getViewWightHeight(view);
        //上左一点
        float x1=location[0];
        float y1=location[1];
        //上右
        float x2=x1+param[0];
        float y2=y1;
        //下左
        float x3=x1;
        float y3=y1+param[1];
        //下右
        float x4=x1+param[0];
        float y4=y1+param[1];

        viewFourCoordinate[0]=x1;
        viewFourCoordinate[1]=y1;
        viewFourCoordinate[2]=x2;
        viewFourCoordinate[3]=y2;
        viewFourCoordinate[4]=x3;
        viewFourCoordinate[5]=y3;
        viewFourCoordinate[6]=x4;
        viewFourCoordinate[7]=y4;
        Log.i(TAG, "setBoundary " + "view w h:" + param[0] + "," + param[1]);
        for(int i=0;i<8;i++){
            Log.i(TAG, "setBoundary "+"viewFourCoordinate:"+viewFourCoordinate[i]);
        }


    }
    //判断是否在边界
    private boolean isBoundary(float[] coordinate){
//        Log.i(TAG, "isBoundary:"+"screenWidth"+windowsWight+",screenHeight"+windowsHeight);
        Log.i(TAG, "isBoundary move x y"+coordinate[0]+","+coordinate[1]);
        float x=coordinate[0];
        float y=coordinate[1];
        if(x+viewFourCoordinate[0]>0&&x+viewFourCoordinate[2]<windowsWight&&y+viewFourCoordinate[1]>0&&y+viewFourCoordinate[5]<windowsHeight){
            return true;
        }else {
            return false;
        }
    }


    private float[] getViewWightHeight(View view){
        float [] viewCoordinate=new float[2];
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int height = view.getMeasuredHeight();
        int width =  view.getMeasuredWidth();
        viewCoordinate[0]=width;
        viewCoordinate[1]=height;
        return viewCoordinate;
    }


    //传入圆心坐标
    public List getCircleData(View view,float x1,float y1,float x0,float y0){
        float number=0.1f;
//        float[] location=getCoordinateInFartherView();
        //起始点坐标
//        double x1=location[0];
//        double y1=location[1];
        Log.i(TAG, "getCircleData x y: " + x1+"  ,"+y1);

        double R=Math.sqrt((x1-x0)*(x1-x0)+(y1-y0)*(y1-y0));

        List listX=new ArrayList();
        List listY=new ArrayList();
        List list=new ArrayList();

        double param=Math.abs(y1 - y0)/R;
        if(param<-1.0){
            param=-1.0;
        }else if(param>1.0){
            param=1.0;}

        double a=Math.asin(param);
        if(x1>=x0&&y1>=y0){
            a=a;
        }else if(x1<x0&&y1>=y0){
            a=pi-a;
        }else if(x1<x0&&y1<y0){
            a=a+pi;
        }else {
            a=2*pi-a;
        }

        double i=a+number;

        Log.i(TAG, "getCircleData a: " + a + "  R:" + R);
        while (i<=(a+2*pi)){
            double y,x;
            x=x0+R*Math.cos(i);y=y0+R*Math.sin(i);
//            Log.i(TAG, "getCircleData i cos sin  "+i+"   "+Math.cos(i)+"   "+Math.sin(i));

            listX.add(x);
            listY.add(y);
            double aa=x-x1;
            double bb=y-y1;
//            listX.add(aa);
//            listY.add(bb);
            Log.i(TAG, "getCircleData  a:" +i+ "   x:" + x + "  y:" + y  +"  a:"+ aa+"   b:"+bb);
            x1=(float)x;
            y1=(float)y;
            i=i+number;
        }

        float[] orinage=getCoordinateOnFartherView(view);
        listX.add((double)orinage[0]);
        listY.add((double)orinage[1]);


        list.add(listX);
        list.add(listY);
        return list;
    }



    private float[] getCoordinateOnScreen(View view){
        int[] location=new int[2];
        view.getLocationOnScreen(location);
        float myX = location[0];
        float myY = location[1];
        return new float[]{myX,myY};
    }

    private float[] getCoordinateOnFartherView(View view){
        float x1=view.getX();
        float y1=view.getY();
        return  new float[]{x1,y1};
    }
}
