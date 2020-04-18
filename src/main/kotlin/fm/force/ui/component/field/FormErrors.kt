package fm.force.ui.component.field

import fm.force.quiz.common.dto.FieldError
import fm.force.ui.util.DynamicIterator
import kotlinx.css.*
import react.RBuilder
import react.RProps
import react.child
import react.dom.li
import react.dom.ul
import react.functionalComponent
import styled.css
import styled.styledDiv

interface FieldErrorsProps : RProps {
    var fieldErrors: List<FieldError>
}

val FieldErrors = functionalComponent<FieldErrorsProps> { props ->
    styledDiv {
        css {
            margin = "0"
            padding = "0"
            color = Color.red
            fontSize = LinearDimension.inherit
        }
        ul {
            DynamicIterator<FieldError>(props.fieldErrors).asSequence().forEach { fieldError ->
                li {
                    +fieldError.message
                }
            }
        }
    }
}

fun RBuilder.fieldErrors(fieldErrors: List<FieldError>) = child(FieldErrors) {
    attrs {
        this.fieldErrors = fieldErrors
    }
}
