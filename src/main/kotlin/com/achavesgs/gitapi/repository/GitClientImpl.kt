package com.achavesgs.gitapi.repository

import com.achavesgs.gitapi.entities.GitRepositoryDTO
import com.achavesgs.gitapi.entities.UserProfileDTO
import com.achavesgs.gitapi.exception.APIException
import com.achavesgs.gitapi.exception.NotFoundException
import com.achavesgs.gitapi.exception.UserNotFoundException
import com.achavesgs.gitapi.repository.domain.GitRepository
import com.achavesgs.gitapi.repository.domain.GitUserProfile
import com.achavesgs.gitapi.repository.domain.toDTO
import com.achavesgs.gitapi.service.interfaces.GitClient
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate



@Component
class GitClientImpl: GitClient {

    var logger = LoggerFactory.getLogger(GitClientImpl::class.java)

    @Value("\${git_path}")
    protected val gitPath = ""

    var rt = RestTemplate()

    override fun getUserProfile(username: String): UserProfileDTO? {

        val response = rt.exchange<GitUserProfile>(
                "$gitPath/$username",
                HttpMethod.GET,
                null,
                object : ParameterizedTypeReference<GitUserProfile>() {

                }
        )

        if(response.statusCode.value() in 200..299)
            return response.body?.toDTO()
        else if (response.statusCode.value() == 404)
            throw UserNotFoundException()
        else
            logger.error("Erro ao retornar user profile. ")
            throw APIException()
    }

    override fun getAllRepositories(username: String): List<GitRepositoryDTO>? {
        val response = rt.exchange<List<GitRepository>>(
                "$gitPath/$username/repos",
                HttpMethod.GET,
                null,
                object : ParameterizedTypeReference<List<GitRepository>>() {

                }
        )

        if (response.statusCode.value() in 200..299)
            return gitRepositoryToList(response?.body)
        else if (response.statusCode.value() == 400)
            throw NotFoundException()
        else
            run {
                logger.error("Erro ao retornar user repositorios do usuario. ")
                throw APIException()
            }


    }

    internal fun gitRepositoryToList(repoList: List<GitRepository>?): List<GitRepositoryDTO>?{
        return repoList?.map { i -> i.toDTO() }?.toList()
    }
}