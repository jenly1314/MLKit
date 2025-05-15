# MLKit

[![MavenCentral](https://img.shields.io/maven-central/v/com.github.jenly1314.MLKit/mlkit-common?logo=sonatype)](https://repo1.maven.org/maven2/com/github/jenly1314/MLKit)
[![JitPack](https://img.shields.io/jitpack/v/github/jenly1314/MLKit?logo=jitpack)](https://jitpack.io/#jenly1314/MLKit)
[![CI](https://img.shields.io/github/actions/workflow/status/jenly1314/MLKit/build.yml?logo=github)](https://github.com/jenly1314/MLKit/actions/workflows/build.yml)
[![Download](https://img.shields.io/badge/download-APK-brightgreen?logo=github)](https://raw.githubusercontent.com/jenly1314/MLKit/master/app/release/app-release.apk)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen?logo=android)](https://developer.android.com/guide/topics/manifest/uses-sdk-element#ApiLevels)
[![License](https://img.shields.io/github/license/jenly1314/MLKit?logo=open-source-initiative)](https://opensource.org/licenses/apache-2-0)

ML Kit是一个能够将谷歌专业的机器学习知识带到应用中的极其简单易用的封装包。无论您是否有机器学习的经验，您都可以在几行代码中实现您想要的功能。甚至，您无需对神经网络或者模型优化有多深入的了解，也能完成您想要做的事情。

基于现有的API您可以很轻松的实现文字识别、条码识别、图像标签、人脸检测、对象检测等功能；另一方面，如果您是一位经验丰富的ML开发人员，ML Kit甚至提供了便利的API，可帮助您在移动应用中使用自定义的TensorFlow Lit模型。

## 效果展示

![Image](GIF.gif)

因为功能太多，所以仅录制演示了部分功能

> 你可以直接下载 [演示App](https://raw.githubusercontent.com/jenly1314/MLKit/master/app/release/app-release.apk) 体验效果

## 各Module相关说明

### [app](app)

示例App：主要用于提供MLKit各个子库的演示效果

### [mlkit-camera-core](mlkit-camera-core)

Camera：为各个子库提供相机预览分析的核心库

> 参见[CameraX](https://developer.android.google.cn/training/camerax)

### [mlkit-common](mlkit-common)

公共库：介于Camera与各个子库之间，为各个字库提供公共业务，从而简化各子库的实现

### [mlkit-barcode-scanning](mlkit-barcode-scanning)

条码扫描：通过分析图像能够识别条码的内容信息

> 参见[barcode-scanning](https://developers.google.cn/ml-kit/vision/barcode-scanning)

### [mlkit-face-detection](mlkit-face-detection)

人脸检测：通过分析图像能够检测到人脸和分析面部轮廓关键点信息

> 参见[face-detection](https://developers.google.cn/ml-kit/vision/face-detection)

人脸网格检测：通过分析图像能够检测到人脸网格信息

> 参见[face-mesh-detection](https://developers.google.cn/ml-kit/vision/face-mesh-detection)

### [mlkit-image-labeling](mlkit-image-labeling)

图像标签：通过分析图像能够标记一般对象、场所、动物种类、产品等

> 参见[image-labeling](https://developers.google.cn/ml-kit/vision/image-labeling)

### [mlkit-object-detection](mlkit-object-detection)

对象检测：通过分析图像能够检测出图像中的对象的位置信息（一张图最多可以检测五个对象）

> 参见[object-detection](https://developers.google.cn/ml-kit/vision/object-detection)

### [mlkit-pose-detection](mlkit-pose-detection)

姿势检测：通过分析图像能够检测人物摆姿势的关键点信息

> 参见[pose-detection](https://developers.google.cn/ml-kit/vision/pose-detection)

### [mlkit-pose-detection-accurate](mlkit-pose-detection-accurate)

姿势检测（精确版）：通过分析图像能够检测人物摆姿势的关键点信息（精确版的依赖库也略大一点）

> 参见[pose-detection](https://developers.google.cn/ml-kit/vision/pose-detection)

### [mlkit-segmentation-selfie](mlkit-segmentation-selfie)

自拍分割：通过分析图像能够将自拍照的人物特征进行分割

> 参见[selfie-segmentation](https://developers.google.cn/ml-kit/vision/selfie-segmentation)

### [mlkit-text-recognition](mlkit-text-recognition)

文字识别：识别图像中的文字信息

> 参见[text-recognition](https://developers.google.cn/ml-kit/vision/text-recognition/v2)

## ViewfinderView相关字段说明

| 属性                      | 属性类型      | 默认值 | 属性说明  |
|:------------------------| :------ | :------ | :------ |
| maskColor               | color |<font color=#000000>#60000000</font>| 扫描区外遮罩的颜色 |
| frameColor              | color |<font color=#1FB3E2>#7F1FB3E2</font>| 扫描区边框的颜色 |
| cornerColor             | color |<font color=#1FB3E2>#FF1FB3E2</font>| 扫描区边角的颜色 |
| laserColor              | color |<font color=#1FB3E2>#FF1FB3E2</font>| 扫描区激光线的颜色 |
| labelText               | string |  | 扫描提示文本信息 |
| labelTextColor          | color |<font color=#C0C0C0>#FFC0C0C0</font>| 提示文本字体颜色 |
| labelTextSize           | dimension |14sp| 提示文本字体大小 |
| labelTextPadding        | dimension |24dp| 提示文本距离扫描区的间距 |
| labelTextWidth          | dimension | | 提示文本的宽度，默认为View的宽度 |
| labelTextLocation       | enum |bottom| 提示文本显示位置 |
| frameWidth              | dimension |  | 扫码框宽度 |
| frameHeight             | dimension |  | 扫码框高度 |
| laserStyle              | enum | line | 扫描激光的样式 |
| gridColumn              | integer | 20 | 网格扫描激光列数 |
| gridHeight              | integer | 40dp | 网格扫描激光高度，为0dp时，表示动态铺满 |
| cornerRectWidth         | dimension | 4dp | 扫描区边角的宽 |
| cornerRectHeight        | dimension | 16dp | 扫描区边角的高 |
| scannerLineMoveDistance | dimension | 2dp | 扫描线每次移动距离 |
| scannerLineHeight       | dimension | 5dp | 扫描线高度 |
| frameLineWidth          | dimension | 1dp | 边框线宽度 |
| scannerAnimationDelay   | integer | 20 | 扫描动画延迟间隔时间，单位：毫秒 |
| frameRatio              | float | 0.625f | 扫码框与屏幕占比 |
| framePaddingLeft        | dimension | 0 | 扫码框左边的内间距 |
| framePaddingTop         | dimension | 0 | 扫码框上边的内间距 |
| framePaddingRight       | dimension | 0 | 扫码框右边的内间距 |
| framePaddingBottom      | dimension | 0 | 扫码框下边的内间距 |
| frameGravity            | enum | center | 扫码框对齐方式 |
| pointColor              | color | <font color=#1FB3E2>#FF1FB3E2</font> | 结果点的颜色 |
| pointStrokeColor        | color | <font color=#FFFFFF>#FFFFFFFF</font> | 结果点描边的颜色 |
| pointRadius             | dimension | 15dp | 结果点的半径 |
| pointStrokeRatio        | float | 1.2 | 结果点描边半径与结果点半径的比例 |
| pointDrawable           | reference |  | 结果点自定义图片 |
| showPointAnim           | boolean | true | 是否显示结果点的动画 |
| laserDrawable           | reference | | 扫描激光自定义图片 |
| laserDrawableRatio      | float | 0.625f | 激光扫描图片与屏幕占比  |
| viewfinderStyle         | enum | classic | 取景框样式；支持：classic：经典样式（带扫码框那种）、popular：流行样式（不带扫码框） |

## 引入

### Gradle:

1. 在Project的 **build.gradle** 中添加远程仓库

```gradle
allprojects {
    repositories {
        //...
        mavenCentral()
    }
}
```

2. 在Module的 **build.gradle** 中添加依赖项

```gradle

//Camera核心 (*必须)
implementation 'com.github.jenly1314.MLKit:mlkit-camera-core:1.4.0'

//--------------------------

//公共库 (可选) （1.3.0新增：当使用到MLKit下面的子库时，需依赖公共库）
implementation 'com.github.jenly1314.MLKit:mlkit-common:1.4.0'

//条码识别 (可选)
implementation 'com.github.jenly1314.MLKit:mlkit-barcode-scanning:1.4.0'

//人脸检测 (可选)
implementation 'com.github.jenly1314.MLKit:mlkit-face-detection:1.4.0'

//人脸网格检测 (可选)
implementation 'com.github.jenly1314.MLKit:mlkit-face-mesh-detection:1.4.0'

//图像标签 (可选)
implementation 'com.github.jenly1314.MLKit:mlkit-image-labeling:1.4.0'

//对象检测 (可选)
implementation 'com.github.jenly1314.MLKit:mlkit-object-detection:1.4.0'

//姿势检测 (可选)
implementation 'com.github.jenly1314.MLKit:mlkit-pose-detection:1.4.0'

//姿势检测精确版 (可选)
implementation 'com.github.jenly1314.MLKit:mlkit-pose-detection-accurate:1.4.0'

//自拍分割 (可选)
implementation 'com.github.jenly1314.MLKit:mlkit-segmentation-selfie:1.4.0'

//文字识别 (可选)
implementation 'com.github.jenly1314.MLKit:mlkit-text-recognition:1.4.0'

```

### 温馨提示

#### 关于MLKit版本与编译的SDK版本要求

> 使用 **v1.2.x** 以上版本时，要求 **compileSdkVersion >= 33**

> 使用 **v1.1.x** 以上版本时，要求 **compileSdkVersion >= 31**

> 如果 **compileSdkVersion < 31** 请使用 **v1.0.x** 版本

## 使用

### 快速实现扫描识别主要有以下几种方式：

> 1、通过继承 **BaseCameraScanActivity** 或者 **BaseCameraScanFragment** 或其子类，可快速实现扫描识别。
> （适用于大多场景，自定义布局时需覆写 **getLayoutId** 方法）

> 2、在你项目的Activity或者Fragment中实例化一个 **BaseCameraScan**。（适用于想在扫描界面写交互逻辑，又因为项目
> 架构或其它原因，无法直接或间接继承 **BaseCameraScanActivity** 或 **BaseCameraScanFragment** 时使用）

> 3、继承 **CameraScan** 自己实现一个，可参照默认实现类 **BaseCameraScan**，其他步骤同方式2。（高级用法，谨慎使用）

### 关于 CameraScan

**CameraScan** 作为相机扫描的（核心）基类；所有与相机扫描相关的都是基于此类来直接或间接进行控制的。

### 关于 CameraConfig

主要是相机相关的配置；如：摄像头的前置后置、相机预览相关、图像分析相关等配置。

> 你可以直接库中内置实现的相机配置： **CameraConfig** 、**AspectRatioCameraConfig** 和 **ResolutionCameraConfig**。

#### 这里简单说下各自的特点：

* **CameraConfig**：默认的相机配置。
* **AspectRatioCameraConfig**：根据纵横比配置相机，使输出分析的图像尽可能的接近屏幕的比例
* **ResolutionCameraConfig**：根据尺寸配置相机的目标图像大小，使输出分析的图像的分辨率尽可能的接近屏幕尺寸

> 你也可以自定义或覆写 **CameraConfig** 中的 **options** 方法，根据需要定制配置。

这里特别温馨提示：默认配置在未配置相机的目标分析图像大小时，会优先使用：横屏：640 * 480 竖屏：480 * 640；

根据这个图像质量顺便说下默认配置的优缺点：

* 优点：因为图像质量不高，所以在低配置的设备上使用也能hold住，这样就能尽可能的适应各种设备；
* 缺点：正是由于图像质量不高，从而可能会对检测识别率略有影响，比如在某些机型上体验欠佳。
* 结论：在适配、性能与体验之间得有所取舍，找到平衡点。

> 当使用默认的 **CameraConfig** 在某些机型上体验欠佳时，你可以尝试使用 **AspectRatioCameraConfig** 或
**ResolutionCameraConfig** 会有意想不到奇效。

### 关于 **Analyzer**

**Analyzer** 为定义的分析器接口；主要用于分析相机预览的帧数据；MLKit的各个子库皆是通过实现 **Analyzer** 来检测分析结果的。

### 关于 **BaseCameraScanActivity** 和 **BaseCameraScanFragment**

**BaseCameraScanActivity** 和 **BaseCameraScanFragment** 作为扫描预览界面的基类，主要目的是便于快速实现扫描识别。

> 扫描预览界面内部持有 **CameraScan**，并处理了 **CameraScan** 的初始化（如：相机权限、相机预览、生命周期等细节）

### CameraScan配置示例

**CameraScan** 里面包含部分支持链式调用的方法，即调用返回是 **CameraScan** 本身的一些配置建议在调用 **startCamera()** 方法之前调用。

> 如果是通过继承 **BaseCameraScanActivity** 或者 **BaseCameraScanFragment** 或其子类实现的相机扫描，可以在
**initCameraScan()** 方法中获取 **CameraScan** ，然后根据需要修改相关配置。

示例：

```java
// 获取CameraScan，根据需要修改相关配置
getCameraScan().setPlayBeep(true)//设置是否播放音效，默认为false
        .setVibrate(true)//设置是否震动，默认为false
        .setCameraConfig(new ResolutionCameraConfig(this))//设置相机配置信息，CameraConfig可覆写options方法自定义配置
        .setNeedTouchZoom(true)//支持多指触摸捏合缩放，默认为true
        .setDarkLightLux(45f)//设置光线足够暗的阈值（单位：lux），需要通过{@link #bindFlashlightView(View)}绑定手电筒才有效
        .setBrightLightLux(100f)//设置光线足够明亮的阈值（单位：lux），需要通过{@link #bindFlashlightView(View)}绑定手电筒才有效
        .bindFlashlightView(ivFlashlight)//绑定手电筒，绑定后可根据光线传感器，动态显示或隐藏手电筒按钮
        .setOnScanResultCallback(this)//设置扫码结果回调，需要自己处理或者需要连扫时，可设置回调，自己去处理相关逻辑
        .setAnalyzer(new BarcodeScanningAnalyzer())//设置分析器，如这里使用条码分析器，BarcodeScanningAnalyzer是mlkit-barcode-scanning中的
        .setAnalyzeImage(true)//设置是否分析图片，默认为true。如果设置为false，相当于关闭了扫码识别功能

        // 启动预览（如果是通过直接或间接继承BaseCameraScanActivity或BaseCameraScanFragment实现的则无需调用startCamera）
        getCameraScan().startCamera();


        // 设置闪光灯（手电筒）是否开启,需在startCamera之后调用才有效
        getCameraScan().enableTorch(torch);
```

### 布局示例

**PreviewView** 用来预览，布局内至少要保证有 **PreviewView**；如果是继承 **BaseCameraScanActivity** 或 
**BaseCameraScanFragment** 或其子类实现的相机扫描；快速实现扫描功能；

需自定义布局时，通过覆写getLayoutId方法即可；预览控件ID可覆写getPreviewViewId方法自定义；更多代码用法可**BaseCameraScanActivity**源码或参见下面的使用示例。

示例：

```Xml

<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent" 
    android:layout_height="match_parent">
    <androidx.camera.view.PreviewView 
        android:id="@+id/previewView"
        android:layout_width="match_parent" 
        android:layout_height="match_parent" />
    <!-- 只需保证有布局内有PreviewView即可，然后自己可根据需要添加的控件 -->
</FrameLayout>
```

如：扫二维码的布局示例 (**ViewfinderView** 是 **mlkit-barcode-scanning** 中的扫描渲染效果视图)

```Xml
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.camera.view.PreviewView
        android:id="@+id/previewView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

    <com.king.mlkit.vision.barcode.ViewfinderView
        android:id="@+id/viewfinderView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

    <ImageView
        android:id="@+id/ivFlashlight"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:layout_marginTop="@dimen/ml_flashlight_margin_top"
        android:contentDescription="@null"
        android:src="@drawable/ml_flashlight_selector" />
</FrameLayout>
```
### 各个子模块的核心类说明

下面就列一下各个子模块实现的具体功能和核心类；主要包括实现对应功能的**Analyzer** 和便于快速实现扫描检测的 **BaseCameraScanActivity** 或 **BaseCameraScanFragment** 的子类。

| 功能        | 所属子模块                        | 对应的Analyzer实现                 | 对应的BaseCameraScanActivity子类                    |
|:----------|:---------------------------------|:------------------------------|:---------------------------------------------------|
| 条码扫描      | mlkit-barcode-scanning           | BarcodeScanningAnalyzer       | BarcodeCameraScanActivity/QRCodeCameraScanActivity |
| 人脸检测      | mlkit-face-detection             | FaceDetectionAnalyzer         | FaceCameraScanActivity                             |
| 人脸网格检测   | mlkit-face-mesh-detection        | FaceMeshDetectionAnalyzer     | FaceMeshCameraScanActivity                         |
| 图像标签      | mlkit-image-labeling             | ImageLabelingAnalyzer         | ImageCameraScanActivity                            |
| 对象检测      | mlkit-object-detection           | ObjectDetectionAnalyzer       | ObjectCameraScanActivity                           |
| 姿势检测      | mlkit-pose-detection             | PoseDetectionAnalyzer         | PoseCameraScanActivity                             |
| 姿势检测（精确版） | mlkit-pose-detection-accurate | AccuratePoseDetectionAnalyzer | AccuratePoseCameraScanActivity                     |
| 自拍分割      | mlkit-segmentation-selfie        | SegmentationAnalyzer          | SegmentationCameraScanActivity                     |
| 文字识别      | mlkit-text-recognition           | TextRecognitionAnalyzer       | TextCameraScanActivity                             |

> **xxx**CameraScanActivity 和 **xxx**BaseCameraScanFragment 在上面只列出了一个，因为有一个 **xxx**CameraScanActivity 就有一个与之对应的 **xxx**CameraScanFragment；命名前缀一样，使用方式也基本一样。

### 条形码检测分析示例（**mlkit-barcode-scanning**）

#### 支持检测识别的条形码格式主要有：
  * 线性格式：Codabar, Code 39, Code 93, Code 128, EAN-8, EAN-13, ITF, UPC-A, UPC-E
  * 2D格式：Aztec, Data Matrix, PDF417, QR Code

```kotlin
BarcodeDecoder.process(bitmap, object : OnAnalyzeListener<List<Barcode>?> {
    override fun onSuccess(result: List<Barcode>) {
        // 分析成功
    }

    override fun onFailure(e: Exception?) {
        // 分析失败
    }
})
```

### 各个module的使用示例

#### mlkit-camera-core

Camera核心：为各个子库提供相机预览分析的核心库。

如果MLKit支持的衍生库没有满足你的需求，你也可以通过依赖 **mlkit-camera-core** 去拓展实现任何与相机预览和分析相关的衍生库。

#### mlkit-common （1.3.0新增）

公共库：介于Camera与各个子库之间，为各个字库提供公共业务，从而简化各子库的实现。

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

#### mlkit-face-mesh-detection （1.2.0新增）

人脸网格检测实现示例：通过直接继承 **FaceMeshCameraScanActivity** 实现的示例
[FaceMeshDetectionActivity](app/src/main/java/com/king/mlkit/vision/app/face/FaceMeshDetectionActivity.kt)

#### mlkit-image-labeling

图像标签实现示例：通过直接继承 **ImageCameraScanActivity** 实现的示例
[ImageLabelingActivity](app/src/main/java/com/king/mlkit/vision/app/image/ImageLabelingActivity.kt)

#### mlkit-object-detection

对象检测实现示例：通过直接继承 **ObjectCameraScanActivity** 实现的示例
[ObjectDetectionActivity](app/src/main/java/com/king/mlkit/vision/app/object/ObjectDetectionActivity.kt)

多对象检测实现示例：通过间接继承 **ObjectCameraScanActivity** 实现的示例
[MultipleObjectDetectionActivity](app/src/main/java/com/king/mlkit/vision/app/object/MultipleObjectDetectionActivity.kt)

#### mlkit-pose-detection

姿势检测实现示例：通过直接继承 **PoseCameraScanActivity** 实现的示例
[PoseDetectionActivity](app/src/main/java/com/king/mlkit/vision/app/pose/PoseDetectionActivity.kt)

#### mlkit-pose-detection-accurate

姿势检测（精确版）实现示例：通过间接继承 **AccuratePoseCameraScanActivity** 实现的示例
[AccuratePoseDetectionActivity](app/src/main/java/com/king/mlkit/vision/app/pose/AccuratePoseDetectionActivity.kt)

#### mlkit-segmentation-selfie

自拍分割实现示例：通过直接继承 **SegmentationCameraScanActivity** 实现的示例
[SelfieSegmentationActivity](app/src/main/java/com/king/mlkit/vision/app/segmentation/SelfieSegmentationActivity.kt)

#### mlkit-text-recognition

文字识别实现示例：通过直接继承 **TextCameraScanActivity** 实现的示例
[TextRecognitionActivity](app/src/main/java/com/king/mlkit/vision/app/text/TextRecognitionActivity.kt)

### 模型配置（可选）

关于依赖项下载模型（如果选择使用 Google Play Services 中的模型）则可以通过在AndroidManifest中申明配置

```xml

<meta-data android:name="com.google.firebase.ml.vision.DEPENDENCIES"
    android:value="ocr" /><!-- To use multiple models: android:value="ocr,model2,model3" -->
```

如：配置Barcode模型、Face模型、OCR模型等等~

```xml

<meta-data android:name="com.google.firebase.ml.vision.DEPENDENCIES"
    android:value="barcode,face,ocr" />
```

更多使用详情，请查看[app](app)
中的源码使用示例或直接查看 [API帮助文档](https://jitpack.io/com/github/jenly1314/MLKit/latest/javadoc/)

### 其他

#### ABI过滤

在Module的 **build.gradle** 里面的 android{} 中设置支持的 SO 库架构（可选，支持多个平台的 so，支持的平台越多，APK体积越大）

```gradle
    defaultConfig {
    
        //...
        
        ndk {
            //设置支持的 SO 库架构（开发者可以根据需要，选择一个或多个平台的 so）
            abiFilters 'armeabi-v7a' // , 'arm64-v8a', 'x86', 'x86_64'
        }
    }
```

#### JDK版本

需使用JDK8+编译，在你项目中的build.gradle的android{}中添加配置：

```gradle
compileOptions {
    targetCompatibility JavaVersion.VERSION_1_8
    sourceCompatibility JavaVersion.VERSION_1_8
}

```

## 特别说明

### 关于 mlkit-camera-core

[mlkit-camera-core](mlkit-camera-core) 的核心代码是基于 [ZXingLite](https://github.com/jenly1314/ZXingLite)
抽取后修改而来的，所以在使用上有很多相似之处，特别是 **CameraScan** 相关的API。
各个 **MLKit** 相关的 **Module** 主要是基于 [mlkit-camera-core](mlkit-camera-core) 提供相机的预览帧来做不同的分析处理，
所以如果 **MLKit** 当前不满足您的需求，您可以自定义拓展去实现; 也可以基于 [mlkit-camera-core](mlkit-camera-core)
去开发各种衍生库。（例如：[WeChatQRCode](https://github.com/jenly1314/WeChatQRCode)）

## 相关推荐

- [ZXingLite](https://github.com/jenly1314/ZXingLite) 基于zxing实现的扫码库，优化扫码和生成二维码/条形码功能。
- [WeChatQRCode](https://github.com/jenly1314/WeChatQRCode) 基于OpenCV开源的微信二维码引擎移植的扫码识别库。
- [CameraScan](https://github.com/jenly1314/CameraScan) 一个简化扫描识别流程的通用基础库。
- [ViewfinderView](https://github.com/jenly1314/ViewfinderView) ViewfinderView一个取景视图：主要用于渲染扫描相关的动画效果。
- [LibYuv](https://github.com/jenly1314/libyuv) 基于Google的libyuv编译封装的YUV转换工具库，主要用途是在各种YUV与RGB之间进行相互转换、裁减、旋转、缩放、镜像等。

## 版本日志

#### v1.4.0：2023-4-15
* 优化CameraScan的缺省配置（CameraConfig相关配置）
* 优化ViewfinderView自定义属性（新增laserDrawableRatio）
* 更新MLKit相关依赖库版本
* 更新CameraX至v1.2.2

#### v1.3.0：2023-2-23
* 新增公共库（mlkit-common）
* 优化注释
* 更新CameraX至v1.2.1
* 更新Gradle至v7.5

#### v1.2.0：2022-12-11
* 新增人脸网格检测（mlkit-face-mesh-detection）
* 更新MLKit相关依赖库版本
* 更新CameraX至v1.2.0
* 更新compileSdkVersion至33

#### v1.1.0：2022-6-1
* 更新MLKit相关依赖库版本
* 更新CameraX至v1.2.0-rc01
* 更新compileSdkVersion至31
* 更新Gradle至v7.2

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

---

![footer](https://jenly1314.github.io/page/footer.svg)

