# ViewAnimation
animation of View<br>
---
i did not finish it at this time<br>
<br>
Demo
==
the Demo of the branch:20150817<br>
![](https://github.com/guohuanwen/ViewAniamtion/blob/master/sreenshots/view.gif)<br>
<br>
<br>


Usage
=
*cope mylibrary to your project

*methd
>MyAnimation 
>>setScaleX
>>setScaleY
>>setRotation
>>setRotationX
>>setRotationY
>>setAlpha
>>setTranslation
<br>
>MovePath 
>>getRandomDate
>>getCurveData

dont move animation(button is your view)
-
1.AnimatorSet animatorSet=new AnimatorSet();
2.MyAnimation myAnimation=new MyAnimation();
3.List list=new ArrayList();
4.list.add(moveAnimation.setAlpha(button, 0.2f, 1000));
5.list.add(moveAnimation.setRotation(button, 360, 1000));
6.list.add(moveAnimation.setScaleX(button, 2, 1000));
7.animatorSet.playTogether(list);
8.animatorSet.start();

move view
-
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






