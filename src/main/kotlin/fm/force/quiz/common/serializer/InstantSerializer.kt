package fm.force.quiz.common.serializer

import kotlin.js.Date
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.PrimitiveDescriptor
import kotlinx.serialization.PrimitiveKind
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.Serializer

@Serializer(forClass = Date::class)
object InstantSerializer : KSerializer<Date> {
    override val descriptor: SerialDescriptor =
        PrimitiveDescriptor("yourSerializerUniqueName", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Date) {
        encoder.encodeString(value.toUTCString())
    }

    override fun deserialize(decoder: Decoder): Date {
        return Date(Date.parse(decoder.decodeString()))
    }
}
