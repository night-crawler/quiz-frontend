package redux.form

import kotlin.reflect.KClass
import react.Component
import react.RBuilder

fun <P : Component<*, *>> RBuilder.field(component: KClass<P>) = child(Field::class) {
    attrs {
        this.name = "lol"
        this.component = component.js
    }
}
