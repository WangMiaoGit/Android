package com.wang.latte.ui;

/**
 * Created by MaxWang on 2018/9/22.
 * 项目名称：Android
 * 类描述  ：
 * 创建人  ：MaxWang
 * 创建时间：2018/9/22 13:57
 * 修改人  ：MaxWang
 * 修改时间：2018/9/22
 * 修改备注：
 */

//loader的类型的枚举，不同样式。。。官方的是通过反射拿到样式
@SuppressWarnings("unused")
public enum LoaderStyle {
    BallPulseIndicator,//横向三点
    BallGridPulseIndicator,//九点
    BallClipRotateIndicator,//圆圈
    BallClipRotatePulseIndicator,//圈中实心点
    SquareSpinIndicator,//正方体
    BallClipRotateMultipleIndicator,//大圆圈包小圆圈
    BallPulseRiseIndicator,//五点上下翻转
    BallRotateIndicator,//三点转圈
    CubeTransitionIndicator,//方块转动
    BallZigZagIndicator,//三点，两点移动
    BallZigZagDeflectIndicator,//三点，两点移动
    BallTrianglePathIndicator,//三空心圆 三角转动
    BallScaleIndicator,//圆点 放大
    LineScaleIndicator,//五条竖线
    LineScalePartyIndicator,//四条竖线
    BallScaleMultipleIndicator,//圆点 放大虚幻
    BallPulseSyncIndicator,//三点上下蹦
    BallBeatIndicator,//三点两边蹦
    LineScalePulseOutIndicator,//五条竖线变换
    LineScalePulseOutRapidIndicator,//五竖条变换
    BallScaleRippleIndicator,//圆圈放大
    BallScaleRippleMultipleIndicator,//多圆圈放大
    BallSpinFadeLoaderIndicator,//一圈实心圆 变换
    LineSpinFadeLoaderIndicator,//一圈椭圆 变换
    TriangleSkewSpinIndicator,//三角上下
    PacmanIndicator,//大圆吃小圆
    BallGridBeatIndicator,//九个实心圆
    SemiCircleSpinIndicator,//
//    com.wang.avi.sample.MyCustomIndicator;
}
