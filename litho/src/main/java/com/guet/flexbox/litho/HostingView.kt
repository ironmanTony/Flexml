package com.guet.flexbox.litho

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.RestrictTo
import com.facebook.litho.ComponentTree
import com.facebook.litho.LithoView
import com.facebook.litho.SizeSpec
import com.guet.flexbox.eventsystem.EventBus


open class HostingView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null
) : LithoView(context, attrs) {

    init {
        super.suppressMeasureComponentTree(true)
    }

    val eventBus = EventBus()

    var templatePage: TemplatePage?
        set(value) {
            templatePage?.outerTarget = null
            value?.outerTarget = eventBus
            super.setComponentTree(value)
            requestLayout()
        }
        get() {
            return super.getComponentTree() as? TemplatePage
        }

    @Deprecated("", level = DeprecationLevel.HIDDEN)
    final override fun suppressMeasureComponentTree(suppress: Boolean) {
    }

    @Deprecated(
            "use templatePage",
            ReplaceWith("templatePage"),
            DeprecationLevel.HIDDEN
    )
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    final override fun getComponentTree(): TemplatePage? {
        return super.getComponentTree() as TemplatePage?
    }

    @Deprecated(
            "use templatePage",
            ReplaceWith("templatePage"),
            DeprecationLevel.HIDDEN
    )
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    final override fun setComponentTree(componentTree: ComponentTree?) {
        super.setComponentTree(componentTree as TemplatePage?)
    }

    final override fun setClipToPadding(clipToPadding: Boolean) {
    }

    final override fun setPaddingRelative(start: Int, top: Int, end: Int, bottom: Int) {}

    final override fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {}

    final override fun getPaddingLeft(): Int {
        return 0
    }

    final override fun getPaddingBottom(): Int {
        return 0
    }

    final override fun getPaddingEnd(): Int {
        return 0
    }

    final override fun getPaddingRight(): Int {
        return 0
    }

    final override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val page = templatePage
        Log.d("tony", "hosting view start onMeasure:$this, page is null:${page == null}")
        if (page != null) {
            //fast path
//            val widthSpec = SizeSpec.getMode(widthMeasureSpec)
//            val heightSpec = SizeSpec.getMode(heightMeasureSpec)
//
//            val parentWidthSpec = when (widthSpec) {
//                SizeSpec.AT_MOST, SizeSpec.EXACTLY ->
//                    SizeSpec.makeSizeSpec(SizeSpec.getSize(widthMeasureSpec), SizeSpec.AT_MOST)
//                else -> SizeSpec.makeSizeSpec(SizeSpec.getSize(widthMeasureSpec), SizeSpec.UNSPECIFIED)
//            }
//            val parentHeightSpec = when (heightSpec) {
//                SizeSpec.AT_MOST, SizeSpec.EXACTLY ->
//                    SizeSpec.makeSizeSpec(SizeSpec.getSize(heightMeasureSpec), SizeSpec.AT_MOST)
//                else -> SizeSpec.makeSizeSpec(0, SizeSpec.UNSPECIFIED)
//            }
//            super.onMeasure(
//                    SizeSpec.makeSizeSpec(0, SizeSpec.UNSPECIFIED),
//                    SizeSpec.makeSizeSpec(0, SizeSpec.UNSPECIFIED)
//            )
            setMeasuredDimension(
                    View.getDefaultSize(
                            page.width,
                            widthMeasureSpec
                    ),
                    View.getDefaultSize(
                            page.height,
                            heightMeasureSpec
                    )
            )
            Log.d("tony", "hosting view onMeasure, width:${page.width}, height:${page.height}")
        } else {
            //otherwise
            setMeasuredDimension(
                    View.getDefaultSize(
                            suggestedMinimumWidth,
                            widthMeasureSpec
                    ),
                    View.getDefaultSize(
                            suggestedMinimumHeight,
                            heightMeasureSpec
                    )
            )
        }
    }
}