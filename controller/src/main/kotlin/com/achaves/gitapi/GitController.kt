package com.achaves.gitapi

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired

@RestController
@RequestMapping("api/users")
@Api(value = "Github API")
class GitController {
    @Autowired
    private val service: GitService? = null

    @ApiOperation(value = "Find user profile detail")
    @GetMapping(value = "/{username}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUserProfile(@PathVariable username: String): ResponseEntity<*> {
        val user = service!!.getUserProfile(username) ?: return ResponseEntity<Any>(HttpStatus.NOT_FOUND)


        return ResponseEntity<Any>(user, HttpStatus.OK)
    }

    @ApiOperation(value = "List user repositories")
    @RequestMapping(value = "/{username}/repos", method = [RequestMethod.GET])
    fun getAllRepositories(@RequestParam(value = "username") username: String): ResponseEntity<*> {
        val repositories = service!!.getAllRepositories(username)

        return if (repositories.isEmpty()) {
            ResponseEntity<Any>(HttpStatus.NOT_FOUND)
        } else ResponseEntity<List<Repository>>(repositories, HttpStatus.OK)

    }

}