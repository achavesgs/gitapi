# API Github

## Objetivo
Esta api foi desenvolvida com o intuito de retornar as informações de "User Profile" e "Profile Repositories" de um usuário do Github

## Benefícios
Retornar informações de um usuário do Github de forma mais clara e somente com informações relevantes


## Como utilizar
### API para retornar os dados de um usuário:

#### Request
http://localhost:8090/api/users/{username} - substituir "username" pelo usuário que deseja consultar

#### Response
{
  "id": 0,
  "login": "usuario",
  "name": "Nome do usuário",
  "avatar_url": "https://avatars3.githubusercontent.com/u/0?v=4",
  "html_url": "https://github.com/usuario"
}


### API para retornar lista de repositórios de um usuário

#### Request
http://localhost:8090/api/users/{username}/repos - substituir "username" pelo usuário que deseja consultar

#### Response
[
  {
    "id": 0,
    "name": "Repositorio1",
    "description": "Descrição do Repositório",
    "html_url": "https://github.com/usuario/Repositorio1"
  }
]