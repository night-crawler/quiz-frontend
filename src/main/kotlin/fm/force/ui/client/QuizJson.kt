package fm.force.ui.client

import fm.force.ui.client.dto.DTOMarker
import fm.force.ui.client.dto.ErrorMessage
import fm.force.ui.client.dto.FieldError
import fm.force.ui.client.dto.GenericError
import fm.force.ui.client.dto.InstantSerializer
import fm.force.ui.client.dto.TagFullDTO
import fm.force.ui.client.dto.TagPatchDTO
import fm.force.ui.client.dto.TagRestrictedDTO
import fm.force.ui.client.dto.TopicFullDTO
import fm.force.ui.client.dto.TopicPatchDTO
import fm.force.ui.client.dto.TopicRestrictedDTO
import kotlinx.serialization.internal.LongAsStringSerializer
import kotlin.js.Date
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.modules.SerializersModule

object QuizJson {
    private val defaultSerialModule = SerializersModule {
        polymorphic(GenericError::class) {
            ErrorMessage::class with ErrorMessage.serializer()
            FieldError::class with FieldError.serializer()
        }

        polymorphic(DTOMarker::class) {
            TagFullDTO::class with TagFullDTO.serializer()
            TagRestrictedDTO::class with TagRestrictedDTO.serializer()
            TagPatchDTO::class with TagPatchDTO.serializer()

            TopicFullDTO::class with TopicFullDTO.serializer()
            TopicRestrictedDTO::class with TopicRestrictedDTO.serializer()
            TopicPatchDTO::class with TopicPatchDTO.serializer()
        }
        contextual(Date::class, InstantSerializer)
        contextual(Long::class, LongAsStringSerializer)
    }
    private val defaultJsonConfiguration =
        JsonConfiguration(
            encodeDefaults = true,
            ignoreUnknownKeys = true,
            isLenient = true,
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
