package com.achavesgs.gitapi

import com.achavesgs.gitapi.entities.UserProfileDTO
import com.achavesgs.gitapi.repository.GitClientImpl
import com.achavesgs.gitapi.repository.domain.GitUserProfile
import com.achavesgs.gitapi.service.interfaces.GitClient
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.boot.test.web.client.TestRestTemplate
import java.net.URI
import org.springframework.web.client.RestTemplate
import org.mockito.Mock
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime
import java.time.Month


@RunWith(MockitoJUnitRunner::class)
@SpringBootTest
class GitClientImplTest {

    @InjectMocks
    private val client: GitClientImpl? = null

    @Mock
    private val restTemplate: RestTemplate? = null

    @Before
    fun initMock(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getUserProfileTest(){
        val userProfile = UserProfileDTO( id = 1, login = "user1", name = "Usuário 1", avatar_url = "https://api.github.com/users/user1",
                html_url = "https://github.com/usuario1")

        val user = GitUserProfile (id = 26801558, login = "achavesgs", node_id = "MDQ6VXNlcjI2ODAxNTU4", avatar_url = "avatar_url",
                gravatar_id= "",
                url= "https://api.github.com/users/achavesgs",
                html_url= "https://github.com/achavesgs",
                followers_url= "https://api.github.com/users/achavesgs/followers",
                following_url= "https://api.github.com/users/achavesgs/following{/other_user}",
                gists_url= "https://api.github.com/users/achavesgs/gists{/gist_id}",
                starred_url= "https://api.github.com/users/achavesgs/starred{/owner}{/repo}",
                subscriptions_url= "https://api.github.com/users/achavesgs/subscriptions",
                organizations_url= "https://api.github.com/users/achavesgs/orgs",
                repos_url= "https://api.github.com/users/achavesgs/repos",
                events_url= "https://api.github.com/users/achavesgs/events{/privacy}",
                received_events_url= "https://api.github.com/users/achavesgs/received_events",
                type= "User",
                site_admin= false,
                name= null,
                company= null,
                blog= "",
                location= null,
                email= null,
                hireable= null,
                bio= null,
                public_repos= 23,
                public_gists= 0,
                followers= 0,
                following= 0,
                created_at= LocalDateTime.of(2017,Month.MARCH,30,19,33,53),
                updated_at= LocalDateTime.of(2019, Month.JULY,31,1,13,14)
        )

        Mockito.`when`(restTemplate?.getForEntity("https://api.github.com/users/achavesgs",GitUserProfile::class.java)).thenReturn(ResponseEntity<GitUserProfile>(user, HttpStatus.OK))
        val response = client?.getUserProfile("achavesgs")
        Assert.assertThat(response?.avatar_url, IsEqual.equalTo("avatar_url"))
    }

}