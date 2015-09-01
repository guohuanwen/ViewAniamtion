clc
clear all
x0=0;y0=0;
x1=0;y1=1;
x2=1;y2=1;
t=0:0.1:1;
x=(1-t).*(1-t).*x0+2.*t.*(1-t).*x1+t.*t.*x2;
y=(1-t).*(1-t).*y0+2.*t.*(1-t).*y1+t.*t.*y2;
plot(x,y)
hold on



clear all
t=0:0.1:1;
x0=0;y0=0;
x2=1;y2=1;
changeRate=3;
dd=(x2-x0)/10;
xx0=x0;
yy0=y2;
xx2=x2; 
yy2=y0;
x1=(x2-x0)/2-changeRate*dd+x0;
y1=(x1*yy0 - x1*yy2 + xx0*yy2 - xx2*yy0)/(xx0 - xx2);
x=(1-t).*(1-t).*x0+2.*t.*(1-t).*x1+t.*t.*x2;
y=(1-t).*(1-t).*y0+2.*t.*(1-t).*y1+t.*t.*y2;
%plot(x,y)



clear all
t=0:0.1:1;
x0=0;y0=0;
x2=1;y2=1;
changeRate=3;
dd=(x2-x0)/10;
xx0=x0;
yy0=y2;
xx2=x2; 
yy2=y0;
x1=(x2-xx0)/2+changeRate*dd+x0;
y1=(x1*yy0 - x1*yy2 + xx0*yy2 - xx2*yy0)/(xx0 - xx2);
x=(1-t).*(1-t).*x0+2.*t.*(1-t).*x1+t.*t.*x2;
y=(1-t).*(1-t).*y0+2.*t.*(1-t).*y1+t.*t.*y2;
plot(x,y)



clear all
t=0:0.1:1;
x0=0;y0=0;
x2=0.5;y2=0.5;
changeRate=3;
dd=(x2-x0)/10;
xx0=x0;
yy0=y2;
xx2=x2; 
yy2=y0;
x1=(x2-xx0)/2+changeRate*dd+x0;
y1=(x1*yy0 - x1*yy2 + xx0*yy2 - xx2*yy0)/(xx0 - xx2);
x=(1-t).*(1-t).*x0+2.*t.*(1-t).*x1+t.*t.*x2;
y=(1-t).*(1-t).*y0+2.*t.*(1-t).*y1+t.*t.*y2;
%plot(x,y)



clear all
t=0:0.1:1;
x0=0.5;y0=0.5;
x2=1;y2=1;
changeRate=3;
dd=(x2-x0)/10;
xx0=x0;
yy0=y2;
xx2=x2; 
yy2=y0;
x1=(x2-xx0)/2-changeRate*dd+x0
y1=(x1*yy0 - x1*yy2 + xx0*yy2 - xx2*yy0)/(xx0 - xx2)
x=(1-t).*(1-t).*x0+2.*t.*(1-t).*x1+t.*t.*x2;
y=(1-t).*(1-t).*y0+2.*t.*(1-t).*y1+t.*t.*y2;
%plot(x,y)
clear all



t=0:0.1:1;
x0=0.8;y0=0.8;
x2=1;y2=1;
changeRate=3;
dd=(x2-x0)/10;
xx0=x0;
yy0=y2;
xx2=x2; 
yy2=y0;
x1=(x2-xx0)/2-changeRate*dd+x0
y1=(x1*yy0 - x1*yy2 + xx0*yy2 - xx2*yy0)/(xx0 - xx2)
x=(1-t).*(1-t).*x0+2.*t.*(1-t).*x1+t.*t.*x2;
y=(1-t).*(1-t).*y0+2.*t.*(1-t).*y1+t.*t.*y2;
%plot(x,y)

clear all
t=0:0.1:1;
x0=0.5;y0=0.5;
x2=0.8;y2=0.8;
changeRate=3;
dd=(x2-x0)/10;
xx0=x0;
yy0=y2;
xx2=x2; 
yy2=y0;
x1=(x2-xx0)/2+changeRate*dd+x0
y1=(x1*yy0 - x1*yy2 + xx0*yy2 - xx2*yy0)/(xx0 - xx2)
x=(1-t).*(1-t).*x0+2.*t.*(1-t).*x1+t.*t.*x2;
y=(1-t).*(1-t).*y0+2.*t.*(1-t).*y1+t.*t.*y2;
%plot(x,y)

clear all
t=0:0.1:1;
x0=0;y0=0;
x2=0.5;y2=0.5;
changeRate=3;
dd=(x2-x0)/10;
xx0=x0;
yy0=y2;
xx2=x2; 
yy2=y0;
x1=(x2-xx0)/2-changeRate*dd+x0
y1=(x1*yy0 - x1*yy2 + xx0*yy2 - xx2*yy0)/(xx0 - xx2)
x=(1-t).*(1-t).*x0+2.*t.*(1-t).*x1+t.*t.*x2;
y=(1-t).*(1-t).*y0+2.*t.*(1-t).*y1+t.*t.*y2;
%plot(x,y)

