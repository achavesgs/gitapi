package com.achavesgs.gitapi.controller

import com.achavesgs.gitapi.entities.GitRepositoryDTO
import com.achavesgs.gitapi.service.GitService
import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.GetMapping
import io.swagger.annotations.ApiOperation
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestMethod




@RestController
@RequestMapping("api/users")
@Api(value = "Github API")
class GitController(val service: GitService) {

    @ApiOperation(value = "Find user profile detail")
    @GetMapping(value = "/{username}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUserProfile(@PathVariable username: String): ResponseEntity<*> {

        val user = service.getUserProfile(username)


        return ResponseEntity<Any>(user, HttpStatus.OK)
    }

    @ApiOperation(value = "List user repositories")
    @RequestMapping(value = "/{username}/repos", method = [RequestMethod.GET])
    fun getAllRepositories(@RequestParam(value = "username") username: String): ResponseEntity<*> {
        val repositories = service.getAllRepositories(username)

        if (repositories == null) {
            return ResponseEntity<Any>(HttpStatus.NOT_FOUND)
            } else {
            return ResponseEntity<List<GitRepositoryDTO>>(repositories, HttpStatus.OK)
        }

    }

}