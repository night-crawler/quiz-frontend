package fm.force.ui.client.dto

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
    val fieldName: String,
    val violatedValue: String? = null,
    override val message: String
) : GenericError()

@Serializable
data class ErrorResponse(
    val exception: String,
    val type: Type = Type.GENERAL,
    val errors: List<GenericError> = emptyList()
) {
    enum class Type {
        VALIDATION, GENERAL, AUTH
    }

    companion object
}
