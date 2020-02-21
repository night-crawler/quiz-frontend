package fm.force.ui.component

import fm.force.constant.DEFAULT_TITLE_TEMPLATE
import react.RBuilder
import react.helmet.helmet

fun RBuilder.helmet(children: RBuilder.() -> Unit) =
    helmet(titleTemplate = DEFAULT_TITLE_TEMPLATE, children = children)
