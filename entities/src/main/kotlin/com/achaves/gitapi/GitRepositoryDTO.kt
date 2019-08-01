package com.achaves.gitapi

data class GitRepositoryDTO (
        val id: Int,
        val name:String,
        val description:String,
        val html_url:String
)