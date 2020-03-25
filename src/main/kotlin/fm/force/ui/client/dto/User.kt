package fm.force.ui.client.dto

import kotlinx.serialization.ContextualSerialization
import kotlinx.serialization.Serializable

@Serializable
data class RoleFullDTO(
    val id: Long,
    val name: String
) : DTOFullSerializationMarker

@Serializable
data class UserFullDTO(
    val id: Long,
    val firstName: String = "",
    val lastName: String = "",
    val email: String,
    val isActive: Boolean = false,
    val roles: Collection<RoleFullDTO>,

    @ContextualSerialization
    val createdAt: InstantAlias,
    @ContextualSerialization
    val updatedAt: InstantAlias
) : DTOFullSerializationMarker
