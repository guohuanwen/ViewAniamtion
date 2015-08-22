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
     * get coordinate of random animation
     * 模拟随机运动，返回运动坐标
     * 以初始位置为中心，以定值为半径做圆，在圆内生成随机数，返回一串随即数，最后一个为初始位置
     * @param param  the animation's scope
     * @return List list;List x=(List)list.get(0);List y=(List)list.get(1);
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
     * get the curve‘s coordinate(the curve is a part of a circle)
     * 获取曲线数据
     * @param end the view finally coordinate
     * @param R the circle's radius
     * @param direction if direction is 1,clockwise direction; 0,counter-clockwise
     * @return List list,  List x=(List)list.get(0),List y=(List)list.get(1);
     */
    public List<List<Double>> getCurveData(float[] end,int R,int direction){



        List listX=new ArrayList<Long>();
        List listY=new ArrayList<Long>();
        //计算用matlab，有需要可以学习一下

        //起始点坐标
        double x1=0;
        double y1=0;
        //结束点坐标
        double x2=end[0];
        double y2=end[1];
        double r=Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        if(R<r/2){
            R=(int)r/2;
        }


        //圆心坐标
        double x0;
        double y0;
        double x01= -(2*y1*(y1/2 + y2/2 + (x1*Math.sqrt(-(x1 * x1 - 4 * R * R - 2 * x1 * x2 + x2 * x2 + y1 * y1 - 2 * y1 * y2 + y2 * y2) / (x1 * x1 - 2 * x1 * x2 + x2 * x2 + y1 * y1 - 2 * y1 * y2 + y2 * y2)))/2 - (x2*Math.sqrt(-(x1 * x1 - 4 * R * R - 2 * x1 * x2 + x2 * x2 + y1 * y1 - 2 * y1 * y2 + y2 * y2) / (x1 * x1 - 2 * x1 * x2 + x2 * x2 + y1 * y1 - 2 * y1 * y2 + y2 * y2)))/2) - 2*y2*(y1/2 + y2/2 + (x1*Math.sqrt(-(x1 * x1 - 4 * R * R - 2 * x1 * x2 + x2 * x2 + y1 * y1 - 2 * y1 * y2 + y2 * y2) / (x1 * x1 - 2 * x1 * x2 + x2 * x2 + y1 * y1 - 2 * y1 * y2 + y2 * y2)))/2 - (x2*Math.sqrt(-(x1 * x1 - 4 * R * R - 2 * x1 * x2 + x2 * x2 + y1 * y1 - 2 * y1 * y2 + y2 * y2) / (x1 * x1 - 2 * x1 * x2 + x2 * x2 + y1 * y1 - 2 * y1 * y2 + y2 * y2)))/2) - x1*x1 + x2*x2 - y1*y1 + y2*y2)/(2*x1 - 2*x2);
        double y01= y1/2 + y2/2 + (x1*Math.sqrt(-(x1 * x1 - 4 * R * R - 2 * x1 * x2 + x2 * x2 + y1 * y1 - 2 * y1 * y2 + y2 * y2) / (x1 * x1 - 2 * x1 * x2 + x2 * x2 + y1 * y1 - 2 * y1 * y2 + y2 * y2)))/2 - (x2*Math.sqrt(-(x1 * x1 - 4 * R * R - 2 * x1 * x2 + x2 * x2 + y1 * y1 - 2 * y1 * y2 + y2 * y2) / (x1 * x1 - 2 * x1 * x2 + x2 * x2 + y1 * y1 - 2 * y1 * y2 + y2 * y2)))/2;
        double x02=-(2*y1*(y1/2 + y2/2 - (x1*Math.sqrt(-(x1 * x1 - 4 * R * R - 2 * x1 * x2 + x2 * x2 + y1 * y1 - 2 * y1 * y2 + y2 * y2) / (x1 * x1 - 2 * x1 * x2 + x2 * x2 + y1 * y1 - 2 * y1 * y2 + y2 * y2)))/2 + (x2*Math.sqrt(-(x1 * x1 - 4 * R * R - 2 * x1 * x2 + x2 * x2 + y1 * y1 - 2 * y1 * y2 + y2 * y2) / (x1 * x1 - 2 * x1 * x2 + x2 * x2 + y1 * y1 - 2 * y1 * y2 + y2 * y2)))/2) - 2*y2*(y1/2 + y2/2 - (x1*Math.sqrt(-(x1 * x1 - 4 * R * R - 2 * x1 * x2 + x2 * x2 + y1 * y1 - 2 * y1 * y2 + y2 * y2) / (x1 * x1 - 2 * x1 * x2 + x2 * x2 + y1 * y1 - 2 * y1 * y2 + y2 * y2)))/2 + (x2*Math.sqrt(-(x1 * x1 - 4 * R * R - 2 * x1 * x2 + x2 * x2 + y1 * y1 - 2 * y1 * y2 + y2 * y2) / (x1 * x1 - 2 * x1 * x2 + x2 * x2 + y1 * y1 - 2 * y1 * y2 + y2 * y2)))/2) - x1*x1 + x2*x2 - y1*y1 + y2*y2)/(2*x1 - 2*x2);
        double y02=y1/2 + y2/2 - (x1*Math.sqrt(-(x1 * x1 - 4 * R * R - 2 * x1 * x2 + x2 * x2 + y1 * y1 - 2 * y1 * y2 + y2 * y2) / (x1 * x1 - 2 * x1 * x2 + x2 * x2 + y1 * y1 - 2 * y1 * y2 + y2 * y2)))/2 + (x2*Math.sqrt(-(x1 * x1 - 4 * R * R - 2 * x1 * x2 + x2 * x2 + y1 * y1 - 2 * y1 * y2 + y2 * y2) / (x1 * x1 - 2 * x1 * x2 + x2 * x2 + y1 * y1 - 2 * y1 * y2 + y2 * y2)))/2;
        if((x2>x1&&direction==0)||(x2<=x1&&direction==1)){
                if(y02<y01){
                    y0=y02;
                    x0=x02;
                }else {
                    y0=y01;
                    x0=x01;
                }
            }
            else {
                if(y02>y01){
                    y0=y02;
                    x0=x02;
                }else {
                    y0=y01;
                    x0=x01;
                }
            }

        if(Double.isNaN(x0)){
            x0=(x1+x2)/2;
        }
        if(Double.isNaN(y0)){
            y0=(y1+y2)/2;
        }

        Log.i(TAG, "getCurveData x0y0  "+ x0+ "  "+y0);


        double paramX1=Math.abs(y1 - y0)/R;
        if(paramX1<-1.0){
            paramX1=-1.0;
        }else if(paramX1>1.0){
            paramX1=1.0;}
        double paramX2=Math.abs(y2 - y0)/R;
        if(paramX2<-1.0){
            paramX2=-1.0;
        }else if(paramX2>1.0){
            paramX2=1.0;}

        double a=Math.asin(paramX1);
        double b=Math.asin(paramX2);


        if(x1>x0&&y1>y0){
            a=a;
        }else if(x1<x0&&y1>y0){
            a=pi-a;
        }else if(x1<x0&&y1<y0){
            a=a+pi;
        }else if(x1>x0&&y1<y0){
            a=2*pi-a;
        }else if(x1>x0&&y1==y0){
            a=0;
        }else if(x1==x0&&y1>y0){
            a=pi/2;
        }else if(x1<x0&&y1==y0){
            a=pi;
        }else if(x1==x0&&y1<y0){
            a=pi*1.5;
        }

        if(x2>x0&&y2>y0){
            b=b;
        }else if(x2<x0&&y2>y0){
            b=pi-b;
        }else if(x2<x0&&y2<y0){
            b=b+pi;
        }else if(x2>x0&&y2<y0){
            b=2*pi-b;
        }else if(x2>x0&&y2==y0){
            b=0;
        }else if(x2==x0&&y2>y0){
            b=pi/2;
        }else if(x2<x0&&y2==y0){
            b=pi;
        }else if(x2==x0&&y2<y0){
            b=pi*1.5;
        }

        Log.i(TAG, "getCurveData " +a+ "  "+b);
        Log.i(TAG, "getCurveData x0 y0 x1 y1 x2 y2 " +x0 +","+y0+","+x1+","+y1+","+x2+","+y2);
        listX.add((double)0);
        listY.add((double)0);

        if((direction==0&&b>a)){
            double nub=(a+2*pi-b)/30;
            double n=a+2*pi;
            while (n>=b){
                double x=x0+R*Math.cos(n);
                double y=y0+R*Math.sin(n);
                Log.i(TAG, "getCurveData xy1  " +x+ "  "+ y);
                listX.add(x);
                listY.add(y);
                n=n-nub;
            }
        }else if ((direction==0&&b<=a)){
            double nub=(a-b)/30;
            double n=a;
            while (n>=b){
                double x=x0+R*Math.cos(n);
                double y=y0+R*Math.sin(n);
                Log.i(TAG, "getCurveData xy2  " +x+ "  "+ y);
                listX.add(x);
                listY.add(y);
                n=n-nub;
            }
        }else if(direction==1&&b>a){
            double nub=(b-a)/30;
            double n=a;
            while (n<=b){
                double x=x0+R*Math.cos(n);
                double y=y0+R*Math.sin(n);
                Log.i(TAG, "getCurveData xy3  " +x+ "  "+ y);
                listX.add(x);
                listY.add(y);
                n=n+nub;
            }
        }else if(direction==1&&b<=a){
            double nub=(b-(a-pi*2))/30;
            double n=(a-pi*2);
            while (n<=b){
                double x=x0+R*Math.cos(n);
                double y=y0+R*Math.sin(n);
                Log.i(TAG, "getCurveData xy4  " +x+ "  "+ y);
                listX.add(x);
                listY.add(y);
                n=n+nub;
            }
        }

        List list=new ArrayList<List>();
        Log.i(TAG, "getCurveData "+listX.size());
        list.add(listX);
        list.add(listY);
        return list;
    }

    /**
     * set the boundary of the view's animation
     * @param view set you view
     * @param boundary if boundary is true,animation have boundary;false,not
     * @param boundaryXY set the boundary's width and height
     */
    public void setBoundary(View view,boolean boundary,int[] boundaryXY) {
        windowsWight = boundaryXY[0];
        windowsHeight = boundaryXY[1];
        if(boundary){
            setMyBoundary(view);
        }
        this.boundary = boundary;
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

    /**
     *get the coordinate of animation on a circle
     * @param view set your view
     * @param coordinate set coordinate of the center of the circle
     * @return List list,List x=(List)list.get(0),List y=(List)list.get(1);
     */
    public List getCircleData(View view,float[] coordinate){
        float number=0.1f;
        double x1=0;
        double y1=0;
        double x0=coordinate[0];
        double y0=coordinate[1];
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

        listX.add((double)0);
        listY.add((double)0);


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
