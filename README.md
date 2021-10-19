# MLKit

[![Download](https://img.shields.io/badge/download-App-blue.svg)](https://raw.githubusercontent.com/jenly1314/MLKit/master/app/release/app-release.apk)
[![MavenCentral](https://img.shields.io/maven-central/v/com.github.jenly1314.MLKit/mlkit-camera-core)](https://repo1.maven.org/maven2/com/github/jenly1314/MLKit)
[![JitPack](https://jitpack.io/v/jenly1314/MLKit.svg)](https://jitpack.io/#jenly1314/MLKit)
[![CI](https://travis-ci.org/jenly1314/MLKit.svg?branch=master)](https://travis-ci.org/jenly1314/MLKit)
[![CircleCI](https://circleci.com/gh/jenly1314/MLKit.svg?style=svg)](https://circleci.com/gh/jenly1314/MLKit)
[![API](https://img.shields.io/badge/API-21%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=21)
[![License](https://img.shields.io/badge/license-Apche%202.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Blog](https://img.shields.io/badge/blog-Jenly-9933CC.svg)](https://jenly1314.github.io/)
[![QQGroup](https://img.shields.io/badge/QQGroup-20867961-blue.svg)](http://shang.qq.com/wpa/qunwpa?idkey=8fcc6a2f88552ea44b1.1.982c94fd124f7bb3ec227e2a400dbbfaad3dc2f5ad)


ML Kit是一个能够将谷歌专业的机器学习知识带到应用中的极其简单易用的封装包。无论您是否有机器学习的经验，您都可以在几行代码中实现您想要的功能。甚至，您无需对神经网络或者模型优化有多深入的了解，也能完成您想要做的事情。
基于现有的API您可以很轻松的实现文字识别、条码识别、图像标记、人脸检测、对象检测等功能；另一方面，如果您是一位经验丰富的ML开发人员，ML kit甚至提供了便利的API，可帮助您在移动应用中使用自定义的TensorFlow Lit模型。

## GIF 展示

因为功能太多，暂时没有录制GIF

> 你可以直接下载 [演示App](https://raw.githubusercontent.com/jenly1314/MLKit/master/app/release/app-release.apk) 体验效果


## 各Module相关说明

### [app](app)

示例App：主要用于提供MLKit各个子库的演示效果

### [mlkit-camera-core](mlkit-camera-core)

Camera：为各个子库提供相机预览分析的核心库

> 参见[CameraX](https://developer.android.google.cn/training/camerax)

### [mlkit-barcode-scanning](mlkit-barcode-scanning)

条码扫描：通过分析图像能够识别条码的内容信息

> 参见[barcode-scanning](https://developers.google.cn/ml-kit/vision/barcode-scanning)

### [mlkit-face-detection](mlkit-face-detection)

人脸检测：通过分析图像能够检测到人脸和分析面部轮廓关键点信息

> 参见[face-detection](https://developers.google.cn/ml-kit/vision/face-detection)

### [mlkit-image-labeling](mlkit-image-labeling)

图像标记：通过分析图像能够标记一般对象、场所、动物种类、产品等

> 参见[image-labeling](https://developers.google.cn/ml-kit/vision/image-labeling)

### [mlkit-object-detection](mlkit-object-detection)

对象检测：通过分析图像能够检测出图像中的对象的位置信息（一张图最多可以检测五个对象）

> 参见[object-detection](https://developers.google.cn/ml-kit/vision/object-detection)

### [mlkit-pose-detection](mlkit-pose-detection)

Pose检测：通过分析图像能够检测人物摆姿势的关键点信息

> 参见[pose-detection](https://developers.google.cn/ml-kit/vision/pose-detection)

### [mlkit-pose-detection-accurate](mlkit-pose-detection-accurate)

Pose检测：通过分析图像能够检测人物摆姿势的关键点信息（精确版，依赖包也更大）

> 参见[pose-detection](https://developers.google.cn/ml-kit/vision/pose-detection)

### [mlkit-segmentation-selfie](mlkit-segmentation-selfie)

自拍分割：通过分析图像能够将自拍照的人物特征进行分割

> 参见[selfie-segmentation](https://developers.google.cn/ml-kit/vision/selfie-segmentation)

### [mlkit-text-recognition](mlkit-text-recognition)

文字识别：识别图像中的文字信息（需ML模型）

> 参见[text-recognition](https://developers.google.cn/ml-kit/vision/text-recognition/v2)

## ViewfinderView相关字段说明
| 字段 | 字段类型 | 默认值 | 字段说明 |
| :------| :------ | :------ | :------ |
| maskColor | color |<font color=#000000>#60000000</font>| 扫描区外遮罩的颜色 |
| frameColor | color |<font color=#1FB3E2>#7F1FB3E2</font>| 扫描区边框的颜色 |
| cornerColor | color |<font color=#1FB3E2>#FF1FB3E2</font>| 扫描区边角的颜色 |
| laserColor | color |<font color=#1FB3E2>#FF1FB3E2</font>| 扫描区激光线的颜色 |
| labelText | string |  | 扫描提示文本信息 |
| labelTextColor | color |<font color=#C0C0C0>#FFC0C0C0</font>| 提示文本字体颜色 |
| labelTextSize | dimension |14sp| 提示文本字体大小 |
| labelTextPadding | dimension |24dp| 提示文本距离扫描区的间距 |
| labelTextWidth | dimension | | 提示文本的宽度，默认为View的宽度 |
| labelTextLocation | enum |bottom| 提示文本显示位置 |
| frameWidth | dimension |  | 扫码框宽度 |
| frameHeight | dimension |  | 扫码框高度 |
| laserStyle | enum | line | 扫描激光的样式 |
| gridColumn | integer | 20 | 网格扫描激光列数 |
| gridHeight | integer | 40dp | 网格扫描激光高度，为0dp时，表示动态铺满 |
| cornerRectWidth | dimension | 4dp | 扫描区边角的宽 |
| cornerRectHeight | dimension | 16dp | 扫描区边角的高 |
| scannerLineMoveDistance | dimension | 2dp | 扫描线每次移动距离 |
| scannerLineHeight | dimension | 5dp | 扫描线高度 |
| frameLineWidth | dimension | 1dp | 边框线宽度 |
| scannerAnimationDelay | integer | 20 | 扫描动画延迟间隔时间，单位：毫秒 |
| frameRatio | float | 0.625f | 扫码框与屏幕占比 |
| framePaddingLeft | dimension | 0 | 扫码框左边的内间距 |
| framePaddingTop | dimension | 0 | 扫码框上边的内间距 |
| framePaddingRight | dimension | 0 | 扫码框右边的内间距 |
| framePaddingBottom | dimension | 0 | 扫码框下边的内间距 |
| frameGravity | enum | center | 扫码框对齐方式 |
| pointColor | color | <font color=#1FB3E2>#FF1FB3E2</font> | 结果点的颜色 |
| pointStrokeColor | color | <font color=#FFFFFF>#FFFFFFFF</font> | 结果点描边的颜色 |
| pointRadius | dimension | 15dp | 结果点的半径 |
| pointStrokeRatio | float | 1.2 | 结果点描边半径与结果点半径的比例 |
| pointDrawable | reference |  | 结果点自定义图片 |
| showPointAnim | boolean | true | 是否显示结果点的动画 |
| laserDrawable | reference | | 扫描激光自定义图片 |
| viewfinderStyle | enum | classic | 取景框样式；支持：classic：经典样式（带扫码框那种）、popular：流行样式（不带扫码框） |

## 引入

### Gradle:

1. 在Project的 **build.gradle** 里面添加远程仓库  
          
```gradle
allprojects {
    repositories {
        //...
        mavenCentral()
    }
}
```

2. 在Module的 **build.gradle** 里面添加引入依赖项
```gradle

//Camera核心 (*必须)
implementation 'com.github.jenly1314.MLKit:mlkit-camera-core:1.0.3'

//条码识别 (可选)
implementation 'com.github.jenly1314.MLKit:mlkit-barcode-scanning:1.0.3'

//人脸检测 (可选)
implementation 'com.github.jenly1314.MLKit:mlkit-face-detection:1.0.3'

//图像标记 (可选)
implementation 'com.github.jenly1314.MLKit:mlkit-image-labeling:1.0.3'

//对象检测 (可选)
implementation 'com.github.jenly1314.MLKit:mlkit-object-detection:1.0.3'

//Pose检测 (可选)
implementation 'com.github.jenly1314.MLKit:mlkit-pose-detection:1.0.3'

//Pose检测精确版 (可选)
implementation 'com.github.jenly1314.MLKit:mlkit-pose-detection-accurate:1.0.3'

//自拍分割 (可选)
implementation 'com.github.jenly1314.MLKit:mlkit-segmentation-selfie:1.0.3'

//文字识别 (可选)
implementation 'com.github.jenly1314.MLKit:mlkit-text-recognition:1.0.3'

```

## 示例

布局示例
>  可自定义布局（覆写getLayoutId方法），布局内至少要保证有PreviewView，然后自己可根据需要添加的控件。

> PreviewView 用来预览，布局内至少要保证有PreviewView，如果是继承BaseCameraScanActivity或BaseCameraScanFragment，控件id可覆写getPreviewViewId方法自定义

```Xml
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <androidx.camera.view.PreviewView
        android:id="@+id/previewView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
    <!-- 只需保证有布局内有PreviewView即可，然后自己可根据需要添加的控件 -->
</FrameLayout>
```

如：扫二维码的布局示例 (**ViewfinderView** 是 **mlkit-barcode-scanning**中的)
```Xml
<?xml version="1.0" encoding="UTF-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.camera.view.PreviewView
        android:id="@+id/previewView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
    <com.king.mlkit.vision.barcode.ViewfinderView
        android:id="@+id/viewfinderView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
    <ImageView
        android:id="@+id/ivFlashlight"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:src="@drawable/ml_flashlight_selector"
        android:layout_marginTop="@dimen/ml_flashlight_margin_top" />
</FrameLayout>
```

CameraScan配置示例
```java
        //获取CameraScan，扫码相关的配置设置。CameraScan里面包含部分支持链式调用的方法，即调用返回是CameraScan本身的一些配置建议在startCamera之前调用。
        getCameraScan().setPlayBeep(true)//设置是否播放音效，默认为false
            .setVibrate(true)//设置是否震动，默认为false
            .setCameraConfig(new CameraConfig())//设置相机配置信息，CameraConfig可覆写options方法自定义配置
            .setNeedTouchZoom(true)//支持多指触摸捏合缩放，默认为true
            .setDarkLightLux(45f)//设置光线足够暗的阈值（单位：lux），需要通过{@link #bindFlashlightView(View)}绑定手电筒才有效
            .setBrightLightLux(100f)//设置光线足够明亮的阈值（单位：lux），需要通过{@link #bindFlashlightView(View)}绑定手电筒才有效
            .bindFlashlightView(ivFlashlight)//绑定手电筒，绑定后可根据光线传感器，动态显示或隐藏手电筒按钮
            .setOnScanResultCallback(this)//设置扫码结果回调，需要自己处理或者需要连扫时，可设置回调，自己去处理相关逻辑
            .setAnalyzer(new BarcodeScanningAnalyzer())//设置分析器，如这里使用条码分析器，BarcodeScanningAnalyzer是mlkit-barcode-scanning中的
            .setAnalyzeImage(true)//设置是否分析图片，默认为true。如果设置为false，相当于关闭了扫码识别功能
            .startCamera();//启动预览（如果是通过直接或间接继承BaseCameraScanActivity或BaseCameraScanFragment实现的则无需调用这句）


        //设置闪光灯（手电筒）是否开启,需在startCamera之后调用才有效
        getCameraScan().enableTorch(torch);
```

### 各个module的使用 

#### mlkit-camera-core

Camera核心：为各个子库提供相机预览分析的核心库。

如果MLKit支持的衍生库没有满足你的需求，你也可以通过依赖 **mlkit-camera-core** 去拓展实现任何与相机预览和分析相关的衍生库。

#### mlkit-barcode-scanning

扫条形码/二维码实现示例：通过直接继承 **BarcodeCameraScanActivity** 实现的示例
[BarcodeScanningActivity](app/src/main/java/com/king/mlkit/vision/app/barcode/BarcodeScanningActivity.kt)

扫二维码实现示例：通过间接继承 **BarcodeCameraScanActivity** 实现的示例
[QRCodeScanningActivity](app/src/main/java/com/king/mlkit/vision/app/barcode/QRCodeScanningActivity.kt)


扫二维码（多个结果）实现示例：通过间接继承 **BarcodeCameraScanActivity** 实现的示例
[MultipleQRCodeScanningActivity](app/src/main/java/com/king/mlkit/vision/app/barcode/MultipleQRCodeScanningActivity.kt)


#### mlkit-face-detection

人脸检测实现示例：通过直接继承 **FaceCameraScanActivity** 实现的示例
[FaceDetectionActivity](app/src/main/java/com/king/mlkit/vision/app/face/FaceDetectionActivity.kt)

多人脸检测实现示例：通过间接继承 **FaceCameraScanActivity** 实现的示例
[MultipleFaceDetectionActivity](app/src/main/java/com/king/mlkit/vision/app/face/MultipleFaceDetectionActivity.kt)

#### mlkit-image-labeling

图像标记实现示例：通过直接继承 **ImageCameraScanActivity** 实现的示例
[ImageLabelingActivity](app/src/main/java/com/king/mlkit/vision/app/image/ImageLabelingActivity.kt)

#### mlkit-image-labeling

对象检测实现示例：通过直接继承 **ObjectCameraScanActivity** 实现的示例
[ObjectDetectionActivity](app/src/main/java/com/king/mlkit/vision/app/object/ObjectDetectionActivity.kt)

多对象检测实现示例：通过间接继承 **ObjectCameraScanActivity** 实现的示例
[MultipleObjectDetectionActivity](app/src/main/java/com/king/mlkit/vision/app/object/MultipleObjectDetectionActivity.kt)

#### mlkit-pose-detection

Pose检测实现示例：通过直接继承 **PoseCameraScanActivity** 实现的示例
[PoseDetectionActivity](app/src/main/java/com/king/mlkit/vision/app/pose/PoseDetectionActivity.kt)

#### mlkit-pose-detection-accurate

Pose检测精确版实现示例：通过间接继承 **AccuratePoseCameraScanActivity** 实现的示例
[AccuratePoseDetectionActivity](app/src/main/java/com/king/mlkit/vision/app/pose/AccuratePoseDetectionActivity.kt)

#### mlkit-segmentation-selfie

自拍分割实现示例：通过直接继承 **SegmentationCameraScanActivity** 实现的示例
[SelfieSegmentationActivity](app/src/main/java/com/king/mlkit/vision/app/segmentation/SelfieSegmentationActivity.kt)

#### mlkit-text-recognition

文字识别实现示例：通过直接继承 **TextCameraScanActivity** 实现的示例
[TextRecognitionActivity](app/src/main/java/com/king/mlkit/vision/app/text/TextRecognitionActivity.kt)


### 模型配置

关于自动下载模型，可选项但推荐：在AndroidManifest中申明配置
```xml
    <meta-data
        android:name="com.google.firebase.ml.vision.DEPENDENCIES"
        android:value="ocr" />
    <!-- To use multiple models: android:value="ocr,model2,model3" -->
```

如：配置Barcode模型，Face模型，OCR模型
```xml
    <meta-data
        android:name="com.google.firebase.ml.vision.DEPENDENCIES"
        android:value="barcode,face,ocr" />
```

更多使用详情，请查看[app](app)中的源码使用示例或直接查看 [API帮助文档](https://jitpack.io/com/github/jenly1314/MLKit/latest/javadoc/)

### 其他

需使用JDK8+编译，在你项目中的build.gradle的android{}中添加配置：

```gradle
compileOptions {
    targetCompatibility JavaVersion.VERSION_1_8
    sourceCompatibility JavaVersion.VERSION_1_8
}

```

## 特别说明

关于[mlkit-camera-core](mlkit-camera-core)的核心代码是基于[ZXingLite](https://github.com/jenly1314/ZXingLite)抽离修改而成，所以在Camera预览相关API的使用上有很多相似。
各个mlkit相关的 **Module** 主要是基于[mlkit-camera-core](mlkit-camera-core)提供相机的预览帧来做不同的分析处理，所以如果MLKit当前不满足您的需求，您可以自定义拓展去实现。

### 相关推荐

#### [ZXingLite](https://github.com/jenly1314/ZXingLite) 基于ZXing库优化扫码和生成二维码/条形码功能，扫码界面完全支持自定义。    
#### [WeChatQRCode](https://github.com/jenly1314/WeChatQRCode) 基于OpenCV开源的微信二维码引擎移植的扫码识别库。


## 版本记录

#### v1.0.3：2021-10-18
* 更新CameraX至v1.0.2
* ViewfinderView新增支持显示结果点相关
* 新增扫二维码有多个结果时可选实现示例（类似于新版微信效果）
* 文字识别（text recognition）改为静态（即：使用v2）

#### v1.0.2：2021-8-4
* 更新CameraX至v1.0.1
* 优化CameraConfig的一些默认配置

#### v1.0.1：2021-7-2
* 更新MLKit相关依赖库版本
* 优化细节

#### v1.0.0：2021-4-7
* MLKit初始版本

## 赞赏
如果您喜欢MLKit，或感觉MLKit帮助到了您，可以点右上角“Star”支持一下，您的支持就是我的动力，谢谢 :smiley:<p>
您也可以扫描下面的二维码，请作者喝杯咖啡 :coffee:
    <div>
        <img src="https://jenly1314.github.io/image/pay/wxpay.png" width="280" heght="350">
        <img src="https://jenly1314.github.io/image/pay/alipay.png" width="280" heght="350">
        <img src="https://jenly1314.github.io/image/pay/qqpay.png" width="280" heght="350">
        <img src="https://jenly1314.github.io/image/alipay_red_envelopes.jpg" width="233" heght="350">
    </div>

## 关于我
   Name: <a title="关于作者" href="https://about.me/jenly1314" target="_blank">Jenly</a>

   Email: <a title="欢迎邮件与我交流" href="mailto:jenly1314@gmail.com" target="_blank">jenly1314#gmail.com</a> / <a title="给我发邮件" href="mailto:jenly1314@vip.qq.com" target="_blank">jenly1314#vip.qq.com</a>

   CSDN: <a title="CSDN博客" href="http://blog.csdn.net/jenly121" target="_blank">jenly121</a>

   CNBlogs: <a title="博客园" href="https://www.cnblogs.com/jenly" target="_blank">jenly</a>

   GitHub: <a title="GitHub开源项目" href="https://github.com/jenly1314" target="_blank">jenly1314</a>

   Gitee: <a title="Gitee开源项目" href="https://gitee.com/jenly1314" target="_blank">jenly1314</a>

   加入QQ群: <a title="点击加入QQ群" href="http://shang.qq.com/wpa/qunwpa?idkey=8fcc6a2f88552ea44b1411582c94fd124f7bb3ec227e2a400dbbfaad3dc2f5ad" target="_blank">20867961</a>
   <div>
       <img src="https://jenly1314.github.io/image/jenly666.png">
       <img src="https://jenly1314.github.io/image/qqgourp.png">
   </div>
