package redux.form

import react.Component
import react.RBuilder
import kotlin.reflect.KClass


fun <P : Component<*, *>> RBuilder.field(component: KClass<P>) = child(Field::class) {
    attrs {
        this.name = "lol"
        this.component = component.js
    }
}
