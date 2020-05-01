@file:Suppress("PackageDirectoryMismatch")

package redux.form

import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import react.*

fun <WP, C : Component<WP, RState>> RBuilder.field(
    property: KProperty<*>,
    component: KClass<C>,
    props: WP,
    key: String? = null,
    prefix: String = ""
) = child(Field::class) {
    val name = listOf(prefix, property.name).filter { it.isNotEmpty() }.joinToString(".")
    attrs {
        this.name = name
        this.component = component.js
        this.props = props
        key?.let { this.key = key }
    }
}

fun <WP, C : FunctionalComponent<WP>> RBuilder.field(
    property: KProperty<*>,
    component: C,
    props: WP,
    key: String? = null,
    prefix: String = ""
) = child(Field::class) {
    val name = listOf(prefix, property.name).filter { it.isNotEmpty() }.joinToString(".")
    attrs {
        this.name = name
        this.component = component
        this.props = props
        key?.let { this.key = key }
    }
}

fun <WP, C : RClass<WP>> RBuilder.field(
    property: KProperty<*>,
    component: C,
    props: WP,
    key: String? = null,
    prefix: String = ""
) = child(Field::class) {
    val name = listOf(prefix, property.name).filter { it.isNotEmpty() }.joinToString(".")
    attrs {
        this.name = name
        this.component = component
        this.props = props
        key?.let { this.key = key }
    }
}

fun <
    FieldValue,
    P : _BaseFieldArrayProps<*, FieldValue>,
    C : Component<WrappedFieldArrayProps<FieldValue>, RState>
    > RBuilder.fieldArray(name: String, component: KClass<C>) =

    child(FieldArray::class as KClass<Component<P, RState>>) {
        attrs {
            this.name = name
            this.component = component.js as JsClass<Component<WrappedFieldArrayProps<FieldValue>, RState>>
        }
    }

fun <
    FieldValue,
    P : _BaseFieldArrayProps<*, FieldValue>,
    C : FunctionalComponent<WrappedFieldArrayProps<FieldValue>>
    > RBuilder.fieldArray(name: String, component: C) =

    child(FieldArray::class as KClass<Component<P, RState>>) {
        attrs {
            this.name = name
            // component's type is JsClass<Component<WrappedFieldArrayProps<FieldValue>, RState>>
            // FunctionalComponent and Component classes don't have a common supertype, thus it seems there's
            // no way of defining `component` without using a Union type, support of which Kotlin lacks.
            this.component = component.unsafeCast<JsClass<Component<WrappedFieldArrayProps<FieldValue>, RState>>>()
        }
    }

fun <
    FieldValue,
    P : _BaseFieldArrayProps<*, FieldValue>,
    C : Component<WrappedFieldArrayProps<FieldValue>, RState>
    > RBuilder.fieldArray(property: KProperty<Collection<FieldValue>>, component: KClass<C>) =

    child(FieldArray::class as KClass<Component<P, RState>>) {
        attrs {
            this.name = property.name
            this.component = component.js as JsClass<Component<WrappedFieldArrayProps<FieldValue>, RState>>
        }
    }

fun <
    FieldValue,
    P : _BaseFieldArrayProps<*, FieldValue>,
    C : Component<WrappedFieldArrayProps<FieldValue>, RState>
    > RBuilder.fieldArray(property: KProperty<Array<FieldValue>>, component: KClass<C>) =

    child(FieldArray::class as KClass<Component<P, RState>>) {
        attrs {
            this.name = property.name
            this.component = component.js as JsClass<Component<WrappedFieldArrayProps<FieldValue>, RState>>
        }
    }
