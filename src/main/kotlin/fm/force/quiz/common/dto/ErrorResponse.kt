package fm.force.quiz.common.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
abstract class GenericError {
    abstract val message: String
}

@Serializable
@SerialName("ErrorMessage")
data class ErrorMessage(
    override val message: String
) : GenericError()

@Serializable
@SerialName("FieldError")
data class FieldError(
    override val message: String,
    val fieldName: String,
    val violatedValue: String? = null
) : GenericError()

@Serializable
data class ErrorResponse(
    val exception: String,
    val type: Type = Type.GENERAL,
    val errors: List<GenericError> = emptyList()
) {
    enum class Type {
        VALIDATION, GENERAL, AUTH, INTERNAL
    }

    companion object
}
