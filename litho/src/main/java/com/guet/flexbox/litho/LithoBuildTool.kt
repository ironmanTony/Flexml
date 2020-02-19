package com.guet.flexbox.litho

import android.util.ArrayMap
import com.facebook.yoga.YogaNodeManager
import com.guet.flexbox.build.*
import com.guet.flexbox.litho.bitmap.ExBitmapDrawableLibraryModule
import com.guet.flexbox.litho.factories.*
import com.guet.flexbox.litho.widget.ComponentTreePool

object LithoBuildTool : BuildTool() {

    override val widgets: Map<String, ToWidget> by lazy {
        val arr = arrayOf(
                "Empty" to (Empty to ToEmpty),
                "Flex" to (Flex to ToFlex),
                "Banner" to (Banner to ToBanner),
                "Image" to (Image to ToImage),
                "Scroller" to (Scroller to ToScroller),
                "TextInput" to (TextInput to ToTextInput),
                "Text" to (Text to ToText),
                "Stack" to (CommonProps to ToStack),
                "for" to (For to null),
                "foreach" to (ForEach to null),
                "when" to (When to null),
                "if" to (If to null)
        )
        arr.toMap(ArrayMap<String, ToWidget>(arr.size))
    }

    override val kits: List<Kit> by lazy {
        return@lazy listOf(
                YogaNodeManager,
                ExBitmapDrawableLibraryModule,
                ComponentTreePool
        )
    }

}