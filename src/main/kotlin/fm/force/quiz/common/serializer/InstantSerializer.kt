package fm.force.quiz.common.serializer

import kotlin.js.Date
import kotlinx.serialization.*

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
