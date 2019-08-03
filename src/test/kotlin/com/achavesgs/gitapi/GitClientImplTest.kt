package com.achavesgs.gitapi

import com.achavesgs.gitapi.repository.GitClientImpl
import com.achavesgs.gitapi.repository.domain.GitUserProfile
import com.fasterxml.jackson.databind.ObjectMapper
import io.restassured.RestAssured.expect
import org.assertj.core.api.Assertions
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest
import org.springframework.test.context.junit4.SpringRunner
import org.junit.runner.RunWith
import org.springframework.http.MediaType
import org.springframework.test.web.client.match.MockRestRequestMatchers
import org.springframework.test.web.client.response.MockRestResponseCreators
import java.time.LocalDateTime
import java.time.Month
import org.springframework.web.client.RestTemplate




@RunWith(SpringRunner::class)
@RestClientTest(GitClientImpl::class)
class GitClientImplTest {

    @Autowired
    private val client: GitClientImpl? = null

    @Autowired
    private var server: MockRestServiceServer? = null

    @Autowired
    private val template: RestTemplate? = null

    @Autowired
    private val objectMapper: ObjectMapper? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        val userProfile = objectMapper?.writeValueAsString(GitUserProfile (id = 26801558, login = "achavesgs", node_id = "MDQ6VXNlcjI2ODAxNTU4", avatar_url = "avatar_url",
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
                created_at= LocalDateTime.of(2017, Month.MARCH,30,19,33,53),
                updated_at= LocalDateTime.of(2019, Month.JULY,31,1,13,14)
        ))

        server?.expect(MockRestRequestMatchers.requestTo("https://api.github.com/users"))
                ?.andRespond(MockRestResponseCreators.withSuccess(userProfile!!, MediaType.APPLICATION_JSON))
    }

    @Test
    @Throws(Exception::class)
    fun whenCallingGetUserDetails_thenClientMakesCorrectCall() {

        val details = this.client?.getUserProfile("achavesgs")

        Assertions.assertThat(details?.id==26801558.toLong())
    }
}