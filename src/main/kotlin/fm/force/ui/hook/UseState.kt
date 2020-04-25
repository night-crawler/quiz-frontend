package fm.force.ui.hook

import kotlin.reflect.KProperty
import react.useState

class UseState<T>(initialValue: T) {
    private val valuePair = useState(initialValue)
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = valuePair.first
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = valuePair.second(value)
}
