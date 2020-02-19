package com.guet.flexbox.build

import com.guet.flexbox.enums.ScaleType

internal object Graphic : Declaration(CommonProps) {
    override val attributeInfoSet: AttributeInfoSet by create {
        enum("scaleType", mapOf(
                "center" to ScaleType.CENTER,
                "fitCenter" to ScaleType.FIT_CENTER,
                "fitXY" to ScaleType.FIT_XY,
                "fitStart" to ScaleType.FIT_START,
                "fitEnd" to ScaleType.FIT_END,
                "centerInside" to ScaleType.CENTER_INSIDE,
                "centerCrop" to ScaleType.CENTER_CROP
        ))
        text("src")
        value("aspectRatio")
    }
}