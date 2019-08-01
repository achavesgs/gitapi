package com.achavesgs.gitapi.repository

import com.achavesgs.gitapi.entities.GitRepositoryDTO
import com.achavesgs.gitapi.entities.UserProfileDTO
import com.achavesgs.gitapi.repository.domain.GitRepository
import com.achavesgs.gitapi.repository.domain.GitUserProfile
import com.achavesgs.gitapi.repository.domain.toDTO
import com.achavesgs.gitapi.service.interfaces.GitClient
import org.springframework.stereotype.Component
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate



@Component
class GitClientImpl: GitClient {

    var rt = RestTemplate()

    override fun getUserProfile(username: String): UserProfileDTO? {

        val userProfile = rt.getForObject("https://api.github.com/users/$username", GitUserProfile::class.java)
        return userProfile?.toDTO()
    }

    override fun getAllRepositories(username: String): List<GitRepositoryDTO>? {
        val response = rt.exchange<List<GitRepository>>(
                "https://api.github.com/users/$username/repos",
                HttpMethod.GET,
                null,
                object : ParameterizedTypeReference<List<GitRepository>>() {

                }
        )
        return gitRepositoryToList(response?.body)
    }

    internal fun gitRepositoryToList(repoList: List<GitRepository>?): List<GitRepositoryDTO>?{
        return repoList?.map { i -> i.toDTO() }?.toList()
    }
}