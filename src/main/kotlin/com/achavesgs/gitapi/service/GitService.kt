package com.achavesgs.gitapi.service

import com.achavesgs.gitapi.entities.GitRepositoryDTO
import com.achavesgs.gitapi.entities.UserProfileDTO
import com.achavesgs.gitapi.exception.UserNotFoundException
import com.achavesgs.gitapi.service.interfaces.GitClient
import org.springframework.stereotype.Service

@Service
class GitService(val client: GitClient) {

    fun getUserProfile(username: String): UserProfileDTO? {
        val user = client.getUserProfile(username)
        if (user == null) throw UserNotFoundException()
        return user
    }

    fun getAllRepositories(username: String): List<GitRepositoryDTO>? {
        return client.getAllRepositories(username)
    }
}