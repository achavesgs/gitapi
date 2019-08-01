package com.achavesgs.gitapi.service.interfaces

import com.achavesgs.gitapi.entities.GitRepositoryDTO
import com.achavesgs.gitapi.entities.UserProfileDTO

interface GitClient {

    fun getUserProfile(username: String): UserProfileDTO?

    fun getAllRepositories(username: String): List<GitRepositoryDTO>?
}