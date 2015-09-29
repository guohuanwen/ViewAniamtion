[![Android Gems](http://www.android-gems.com/badge/guohuanwen/ViewAniamtion.svg?branch=master)](http://www.android-gems.com/lib/guohuanwen/ViewAniamtion)

# ViewAnimation
<br>
This is a library of the AnimatorSet,it has three move path and many animation,you can make a easy animation of view by youself.
<br>
##animation of View<br>
i did not finish it at this time<br>
<br>
##Demo
![](https://github.com/guohuanwen/ViewAniamtion/blob/master/sreenshots/AnimationGif.gif)<br>
<br>
  
##MovePath  
任意圆弧运动  
Any circular motion  
List list=movePath.getCurveData(new float[]{200, 200}, 40, 1);  
第一个参数：结束点坐标；第二个参数：曲率半径；第三个参数：弯曲方向  
first param:end coordinate;second param：Radius of curvature；third param：curve direction  

<br>
##Interpolator  
  
####Direction
![](https://github.com/guohuanwen/ViewAniamtion/blob/master/sreenshots/direction.jpg)  
  
####Rate
![](https://github.com/guohuanwen/ViewAniamtion/blob/master/sreenshots/rate.jpg)  
  
####Code test1:  
public float setMy(float param) {  
float y=new BezierInterpolatorData().bezierDataWithoutRate(new float[]{0,0.5,1},new int[]{1,0},param);  
return y;  
}  
![](https://github.com/guohuanwen/ViewAniamtion/blob/master/sreenshots/twoLine.jpg)
  
####Code test2:  
public float setMy(float param) {  
float y=new BezierInterpolatorData().bezierDataWithoutRate(new float[]{0,0.5,0.8,1},new int[]{0,1,0},param);  
return y;  
}  
![](https://github.com/guohuanwen/ViewAniamtion/blob/master/sreenshots/threeLine.jpg)  
  
  
  
##Usage
<br>
####cope mylibrary to your project
<br>
###methd
####MyAnimation 
#####setScaleX
#####setScaleY
#####setRotation
#####setRotationX
#####setRotationY
#####setAlpha
#####setTranslation
<br>
<br>
####MovePath 
#####getRandomData
#####getCurveData
#####getCirlcrData

##dont move animation(button is your view)

    AnimatorSet animatorSet=new AnimatorSet();
    MyAnimation myAnimation=new MyAnimation();
    List list=new ArrayList();
    list.add(moveAnimation.setAlpha(button, 0.2f, 1000));
    list.add(moveAnimation.setRotation(button, 360, 1000));
    list.add(moveAnimation.setScaleX(button, 2, 1000));
    animatorSet.playTogether(list);
    animatorSet.start();

##move view
    AnimatorSet animatorSet=new AnimatorSet();
    MyAnimation myAnimation=new MyAnimation();
    MovePath movePath=new MovePath();
    //return List of coordinate
    List coordinateList=movePath.getCurveData(new float[]{200, 200}, 40, 1);
    //list of x
    List listX=(List)coordinateList.get(0);
    //list of y
    List listY=(List)coordinateList.get(1);
    List list=new ArrayList();
    list.add(moveAnimation.setTranslation(button, listX, listY, 1000));
    list.add(moveAnimation.setAlpha(button, 0.2f, 1000));
    list.add(moveAnimation.setRotation(button, 360, 1000));
    list.add(moveAnimation.setScaleX(button, 2, 1000));
    animatorSet.playTogether(list);
    animatorSet.start();





##License
    Copyright 2014 guohuanwen

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.




