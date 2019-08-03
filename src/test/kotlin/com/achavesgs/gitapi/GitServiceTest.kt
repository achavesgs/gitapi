package com.achavesgs.gitapi

import com.achavesgs.gitapi.entities.GitRepositoryDTO
import com.achavesgs.gitapi.entities.UserProfileDTO
import com.achavesgs.gitapi.exception.UserNotFoundException
import com.achavesgs.gitapi.repository.GitClientImpl
import com.achavesgs.gitapi.service.GitService
import com.achavesgs.gitapi.service.interfaces.GitClient
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
class GitServiceTest {

    @InjectMocks
    val service: GitService? = null

    @Mock
    val client: GitClient? = null

    @Before
    fun initMock(){
        MockitoAnnotations.initMocks(this)
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
                        name = "user1",
                        description = "user description ",
                        html_url = "https://github.com/usuario1/repo1"
                ))


        Mockito.`when`(client?.getAllRepositories("user1")).thenReturn(repositories)
        Assert.assertNotNull(service?.getAllRepositories("user1"))
    }

    @Test
    fun `testing getUserProfileException`() {
        Assertions.assertThatExceptionOfType(UserNotFoundException::class.java).isThrownBy {
            val userProfile = UserProfileDTO( id = 1, login = "user1", name = "Usuário 1", avatar_url = "https://api.github.com/users/user1",
                    html_url = "https://github.com/usuario1")



            Mockito.`when`(client?.getUserProfile("user1")).thenReturn(userProfile)
            service?.getUserProfile("user1")
        }
    }
}