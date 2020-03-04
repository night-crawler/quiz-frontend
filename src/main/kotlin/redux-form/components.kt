@file:Suppress("PackageDirectoryMismatch")

package redux.form

import kotlin.reflect.KClass
import react.Component
import react.RBuilder

fun <WP, C : Component<WP, *>> RBuilder.field(
    name: String,
    component: KClass<C>,
    props: WP
) = child(Field::class) {
    attrs {
        this.name = name
        this.component = component.js
        this.props = props
    }
}
