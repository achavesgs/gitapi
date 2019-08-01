package com.achavesgs.gitapi.repository.domain

import com.achavesgs.gitapi.entities.UserProfileDTO
import java.time.LocalDateTime

data class GitUserProfile (
        val id: Long?,
        val login: String?,
        val node_id: String?,
        val avatar_url: String?,
        val gravatar_id: String?,
        val url: String?,
        val html_url: String?,
        val followers_url: String?,
        val following_url: String?,
        val gists_url: String?,
        val starred_url: String?,
        val subscriptions_url: String?,
        val organizations_url: String?,
        val repos_url: String?,
        val events_url: String?,
        val received_events_url: String?,
        val type: String?,
        val site_admin: Boolean?,
        val name: String?,
        val company: String?,
        val blog: String?,
        val location: String?,
        val email: String?,
        val hireable: String?,
        val bio: String?,
        val public_repos: Long?,
        val public_gists: Int?,
        val followers: Long?,
        val following: Long?,
        val created_at: LocalDateTime?,
        val updated_at: LocalDateTime?
)

fun GitUserProfile.toDTO(): UserProfileDTO{
    return UserProfileDTO(
            id = id,
            login = login,
            name = name,
            avatar_url = avatar_url,
            html_url = html_url
    )
}