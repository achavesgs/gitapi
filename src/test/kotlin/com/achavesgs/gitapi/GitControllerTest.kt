package com.achavesgs.gitapi

import com.achavesgs.gitapi.controller.GitController
import com.achavesgs.gitapi.entities.GitRepositoryDTO
import com.achavesgs.gitapi.entities.UserProfileDTO
import com.achavesgs.gitapi.repository.GitClientImpl
import com.achavesgs.gitapi.service.GitService
import org.assertj.core.api.Assertions
import org.hamcrest.Matcher
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class GitControllerTest {

    @InjectMocks
    val controller: GitController? = null

    @Mock
    val service: GitService? = null

    @Before
    fun initMock(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getUserProfileTest(){

        val userProfile = UserProfileDTO( id = 1, login = "user1", name = "Usuário 1", avatar_url = "https://api.github.com/users/user1",
        html_url = "https://github.com/usuario1")



        Mockito.`when`(service?.getUserProfile("user1")).thenReturn(userProfile)
        Assert.assertNotNull(controller?.getUserProfile("user1"))
    }

    @Test
    fun getAllRepositoriesTest(){

        val repositories = listOf(GitRepositoryDTO(
                id = 1,
                name = "user1",
                description = "user description ",
                html_url = "https://github.com/usuario1"
        ),
                GitRepositoryDTO(
                        id = 2,
                        name = "user2",
                        description = "user description ",
                        html_url = "https://github.com/usuario2"
                ))

        Mockito.`when`(service?.getAllRepositories("user1")).thenReturn(repositories)
        Assert.assertNotNull(controller?.getAllRepositories("user1"))
    }
}