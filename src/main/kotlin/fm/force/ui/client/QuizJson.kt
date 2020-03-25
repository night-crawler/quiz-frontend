package fm.force.ui.client

import fm.force.ui.client.dto.ErrorMessage
import fm.force.ui.client.dto.FieldError
import fm.force.ui.client.dto.GenericError
import fm.force.ui.client.dto.InstantSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlin.js.Date

object QuizJson {
    private val defaultSerialModule = SerializersModule {
        polymorphic(GenericError::class) {
            ErrorMessage::class with ErrorMessage.serializer()
            FieldError::class with FieldError.serializer()
        }
        this.contextual(Date::class, InstantSerializer)
    }
    private val defaultJsonConfiguration =
        JsonConfiguration(
            encodeDefaults = true,
            ignoreUnknownKeys = true,
            isLenient = false,
            serializeSpecialFloatingPointValues = false,
            allowStructuredMapKeys = true,
            prettyPrint = false,
            unquotedPrint = false,
            indent = "    ",
            useArrayPolymorphism = false,
            classDiscriminator = "@type"
        )
    val jsonX = Json(defaultJsonConfiguration, defaultSerialModule)
}
