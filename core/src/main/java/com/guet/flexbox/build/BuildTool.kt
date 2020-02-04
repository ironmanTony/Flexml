package com.guet.flexbox.build

import com.guet.flexbox.EventContext
import com.guet.flexbox.TemplateNode
import com.guet.flexbox.el.ELContext

abstract class BuildTool {

    protected abstract val widgets: Map<String, ToWidget>

    companion object {
        private val default: ToWidget = Common to null
    }

    fun build(
            templateNode: TemplateNode,
            eventContext: EventContext,
            data: ELContext,
            c: Any
    ): Child {
        return buildAll(
                listOf(templateNode),
                eventContext,
                data,
                true,
                c
        ).single()
    }

    internal fun buildAll(
            templates: List<TemplateNode>,
            eventContext: EventContext,
            data: ELContext,
            upperVisibility: Boolean,
            other: Any
    ): List<Child> {
        if (templates.isEmpty()) {
            return emptyList()
        }
        return templates.map { templateNode ->
            val type = templateNode.type
            val toWidget: ToWidget = widgets[type] ?: default
            toWidget.toWidget(
                    this@BuildTool,
                    templateNode,
                    eventContext,
                    data,
                    upperVisibility,
                    other
            )
        }.flatten()
    }
}
