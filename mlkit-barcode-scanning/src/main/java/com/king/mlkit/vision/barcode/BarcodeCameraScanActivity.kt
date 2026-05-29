package com.king.mlkit.vision.barcode

import android.view.View
import com.google.mlkit.vision.barcode.common.Barcode
import com.king.camera.scan.BaseCameraScanActivity
import com.king.camera.scan.analyze.Analyzer
import com.king.mlkit.vision.barcode.analyze.BarcodeScanningAnalyzer
import com.king.view.viewfinderview.ViewfinderView

abstract class BarcodeCameraScanActivity : BaseCameraScanActivity<List<Barcode>>() {

    protected var viewfinderView: ViewfinderView? = null

    override fun initUI() {
        val viewfinderViewId = getViewfinderViewId()
        if (viewfinderViewId != View.NO_ID && viewfinderViewId != 0) {
            viewfinderView = findViewById(viewfinderViewId)
        }
        super.initUI()
    }

    override fun createAnalyzer(): Analyzer<List<Barcode>>? = BarcodeScanningAnalyzer(Barcode.FORMAT_ALL_FORMATS)

    override fun getLayoutId(): Int = R.layout.ml_barcode_camera_scan

    open fun getViewfinderViewId(): Int = R.id.viewfinderView
}
