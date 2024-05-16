<p align="center">
  <img src="https://cdn-icons-png.flaticon.com/512/6295/6295417.png" width="100" />
</p>
<p align="center">
    <h1 align="center">NOTES-APP-API</h1>
</p>
<p align="center">
    <em> `slogan`</em>
</p>
<p align="center">
	<img src="https://img.shields.io/github/license/aribzaman/Notes-App-Api?style=flat&color=0080ff" alt="license">
	<img src="https://img.shields.io/github/last-commit/aribzaman/Notes-App-Api?style=flat&logo=git&logoColor=white&color=0080ff" alt="last-commit">
	<img src="https://img.shields.io/github/languages/top/aribzaman/Notes-App-Api?style=flat&color=0080ff" alt="repo-top-language">
	<img src="https://img.shields.io/github/languages/count/aribzaman/Notes-App-Api?style=flat&color=0080ff" alt="repo-language-count">
<p>
<p align="center">
		<em>Developed with the software and tools below.</em>
</p>
<p align="center">
	<img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=flat&logo=openjdk&logoColor=white" alt="java">
</p>
<hr>

##  Quick Links

> - [ Overview](#-overview)
> - [ Features](#-features)
> - [ Repository Structure](#-repository-structure)
> - [ Modules](#-modules)
> - [ Getting Started](#-getting-started)
>   - [ Installation](#-installation)
>   - [ Running Notes-App-Api](#-running-Notes-App-Api)
>   - [ Tests](#-tests)
> - [ Project Roadmap](#-project-roadmap)
> - [ Contributing](#-contributing)
> - [ License](#-license)
> - [ Acknowledgments](#-acknowledgments)

---

##  Overview

This is the Backend Application for a Google keep Clone Notes taking App made with Springboot in Java 17.

---

##  Features

 `features`

---

##  Repository Structure

```sh
└── Notes-App-Api/
    ├── pom.xml
    └── src
        ├── main
        │   ├── java
        │   │   └── com
        │   │       └── arib
        │   └── resources
        │       └── application.properties
        └── test
            └── java
                └── com
                    └── arib
```

---

##  Modules

<details closed><summary>.</summary>

| File                                                                        | Summary                              |
| ---                                                                         | ---                                  |
| [pom.xml](https://github.com/aribzaman/Notes-App-Api/blob/master/pom.xml)   |  `pom.xml`  |

</details>


<details closed><summary>src.main.java.com.arib.NotesAppApi</summary>

| File                                                                                                                                                 | Summary                                                                                    |
| ---                                                                                                                                                  | ---                                                                                        |
| [NotesAppApiApplication.java](https://github.com/aribzaman/Notes-App-Api/blob/master/src/main/java/com/arib/NotesAppApi/NotesAppApiApplication.java) |  `src/main/java/com/arib/NotesAppApi/NotesAppApiApplication.java` |

</details>

<details closed><summary>src.main.java.com.arib.NotesAppApi.jobs</summary>

| File                                                                                                                                                    | Summary                                                                                        |
| ---                                                                                                                                                     | ---                                                                                            |
| [DeleteNotesFromBinJob.java](https://github.com/aribzaman/Notes-App-Api/blob/master/src/main/java/com/arib/NotesAppApi/jobs/DeleteNotesFromBinJob.java) |  `src/main/java/com/arib/NotesAppApi/jobs/DeleteNotesFromBinJob.java` |

</details>

<details closed><summary>src.main.java.com.arib.NotesAppApi.entities</summary>

| File                                                                                                                        | Summary                                                                            |
| ---                                                                                                                         | ---                                                                                |
| [Notes.java](https://github.com/aribzaman/Notes-App-Api/blob/master/src/main/java/com/arib/NotesAppApi/entities/Notes.java) |  `src/main/java/com/arib/NotesAppApi/entities/Notes.java` |
| [User.java](https://github.com/aribzaman/Notes-App-Api/blob/master/src/main/java/com/arib/NotesAppApi/entities/User.java)   |  `src/main/java/com/arib/NotesAppApi/entities/User.java`  |

</details>

<details closed><summary>src.main.java.com.arib.NotesAppApi.services</summary>

| File                                                                                                                                      | Summary                                                                                   |
| ---                                                                                                                                       | ---                                                                                       |
| [NotesService.java](https://github.com/aribzaman/Notes-App-Api/blob/master/src/main/java/com/arib/NotesAppApi/services/NotesService.java) |  `src/main/java/com/arib/NotesAppApi/services/NotesService.java` |
| [UserService.java](https://github.com/aribzaman/Notes-App-Api/blob/master/src/main/java/com/arib/NotesAppApi/services/UserService.java)   |  `src/main/java/com/arib/NotesAppApi/services/UserService.java`  |

</details>

<details closed><summary>src.main.java.com.arib.NotesAppApi.services.implementation</summary>

| File                                                                                                                                                                                 | Summary                                                                                                                |
| ---                                                                                                                                                                                  | ---                                                                                                                    |
| [NotesServiceImplementation.java](https://github.com/aribzaman/Notes-App-Api/blob/master/src/main/java/com/arib/NotesAppApi/services/implementation/NotesServiceImplementation.java) |  `src/main/java/com/arib/NotesAppApi/services/implementation/NotesServiceImplementation.java` |
| [UserServiceImpl.java](https://github.com/aribzaman/Notes-App-Api/blob/master/src/main/java/com/arib/NotesAppApi/services/implementation/UserServiceImpl.java)                       |  `src/main/java/com/arib/NotesAppApi/services/implementation/UserServiceImpl.java`            |

</details>

<details closed><summary>src.main.java.com.arib.NotesAppApi.controller</summary>

| File                                                                                                                                              | Summary                                                                                        |
| ---                                                                                                                                               | ---                                                                                            |
| [NotesController.java](https://github.com/aribzaman/Notes-App-Api/blob/master/src/main/java/com/arib/NotesAppApi/controller/NotesController.java) |  `src/main/java/com/arib/NotesAppApi/controller/NotesController.java` |
| [UserController.java](https://github.com/aribzaman/Notes-App-Api/blob/master/src/main/java/com/arib/NotesAppApi/controller/UserController.java)   |  `src/main/java/com/arib/NotesAppApi/controller/UserController.java`  |

</details>

<details closed><summary>src.main.java.com.arib.NotesAppApi.dao</summary>

| File                                                                                                                         | Summary                                                                          |
| ---                                                                                                                          | ---                                                                              |
| [UserDao.java](https://github.com/aribzaman/Notes-App-Api/blob/master/src/main/java/com/arib/NotesAppApi/dao/UserDao.java)   |  `src/main/java/com/arib/NotesAppApi/dao/UserDao.java`  |
| [NotesDao.java](https://github.com/aribzaman/Notes-App-Api/blob/master/src/main/java/com/arib/NotesAppApi/dao/NotesDao.java) |  `src/main/java/com/arib/NotesAppApi/dao/NotesDao.java` |

</details>

<details closed><summary>src.main.java.com.arib.NotesAppApi.dto</summary>

| File                                                                                                                                       | Summary                                                                                 |
| ---                                                                                                                                        | ---                                                                                     |
| [NotesDTO.java](https://github.com/aribzaman/Notes-App-Api/blob/master/src/main/java/com/arib/NotesAppApi/dto/NotesDTO.java)               |  `src/main/java/com/arib/NotesAppApi/dto/NotesDTO.java`        |
| [UserDTOMapper.java](https://github.com/aribzaman/Notes-App-Api/blob/master/src/main/java/com/arib/NotesAppApi/dto/UserDTOMapper.java)     |  `src/main/java/com/arib/NotesAppApi/dto/UserDTOMapper.java`   |
| [NotesDTOMapper.java](https://github.com/aribzaman/Notes-App-Api/blob/master/src/main/java/com/arib/NotesAppApi/dto/NotesDTOMapper.java)   |  `src/main/java/com/arib/NotesAppApi/dto/NotesDTOMapper.java`  |
| [UserDTO.java](https://github.com/aribzaman/Notes-App-Api/blob/master/src/main/java/com/arib/NotesAppApi/dto/UserDTO.java)                 |  `src/main/java/com/arib/NotesAppApi/dto/UserDTO.java`         |
| [ResponseMessage.java](https://github.com/aribzaman/Notes-App-Api/blob/master/src/main/java/com/arib/NotesAppApi/dto/ResponseMessage.java) |  `src/main/java/com/arib/NotesAppApi/dto/ResponseMessage.java` |
| [LoginWrapper.java](https://github.com/aribzaman/Notes-App-Api/blob/master/src/main/java/com/arib/NotesAppApi/dto/LoginWrapper.java)       |  `src/main/java/com/arib/NotesAppApi/dto/LoginWrapper.java`    |

</details>

<details closed><summary>src.main.java.com.arib.NotesAppApi.configurations</summary>

| File                                                                                                                                        | Summary                                                                                       |
| ---                                                                                                                                         | ---                                                                                           |
| [CorsConfig.java](https://github.com/aribzaman/Notes-App-Api/blob/master/src/main/java/com/arib/NotesAppApi/configurations/CorsConfig.java) |  `src/main/java/com/arib/NotesAppApi/configurations/CorsConfig.java` |

</details>

<details closed><summary>src.main.java.com.arib.NotesAppApi.exception</summary>

| File                                                                                                                                                                                     | Summary                                                                                                           |
| ---                                                                                                                                                                                      | ---                                                                                                               |
| [ResourceNotFoundException.java](https://github.com/aribzaman/Notes-App-Api/blob/master/src/main/java/com/arib/NotesAppApi/exception/ResourceNotFoundException.java)                     |  `src/main/java/com/arib/NotesAppApi/exception/ResourceNotFoundException.java`           |
| [InvalidJsonDataException.java](https://github.com/aribzaman/Notes-App-Api/blob/master/src/main/java/com/arib/NotesAppApi/exception/InvalidJsonDataException.java)                       |  `src/main/java/com/arib/NotesAppApi/exception/InvalidJsonDataException.java`            |
| [InsufficientAuthenticationException.java](https://github.com/aribzaman/Notes-App-Api/blob/master/src/main/java/com/arib/NotesAppApi/exception/InsufficientAuthenticationException.java) |  `src/main/java/com/arib/NotesAppApi/exception/InsufficientAuthenticationException.java` |
| [ControllerExceptionHandler.java](https://github.com/aribzaman/Notes-App-Api/blob/master/src/main/java/com/arib/NotesAppApi/exception/ControllerExceptionHandler.java)                   |  `src/main/java/com/arib/NotesAppApi/exception/ControllerExceptionHandler.java`          |
| [ErrorMessage.java](https://github.com/aribzaman/Notes-App-Api/blob/master/src/main/java/com/arib/NotesAppApi/exception/ErrorMessage.java)                                               |  `src/main/java/com/arib/NotesAppApi/exception/ErrorMessage.java`                        |
| [WrongDataTypeException.java](https://github.com/aribzaman/Notes-App-Api/blob/master/src/main/java/com/arib/NotesAppApi/exception/WrongDataTypeException.java)                           |  `src/main/java/com/arib/NotesAppApi/exception/WrongDataTypeException.java`              |
| [NumberFormatException.java](https://github.com/aribzaman/Notes-App-Api/blob/master/src/main/java/com/arib/NotesAppApi/exception/NumberFormatException.java)                             |  `src/main/java/com/arib/NotesAppApi/exception/NumberFormatException.java`               |
| [ConstraintViolationException.java](https://github.com/aribzaman/Notes-App-Api/blob/master/src/main/java/com/arib/NotesAppApi/exception/ConstraintViolationException.java)               |  `src/main/java/com/arib/NotesAppApi/exception/ConstraintViolationException.java`        |

</details>

---

##  Getting Started

***Requirements***

Ensure you have the following dependencies installed on your system:

* **Java**: `version 17`
* **Apache Maven**: `3.9.4`
* **MySQL** : `8.0.14`

###  Installation

1. Clone the Notes-App-Api repository:

```sh
git clone https://github.com/aribzaman/Notes-App-Api
```

2. Change to the project directory:

```sh
cd Notes-App-Api
```

3. Install the dependencies:

```sh
mvn clean install
```

###  Running Notes-App-Api

Use the following command to run Notes-App-Api:

```sh
java -jar target/myapp.jar
```

###  Tests

To execute tests, run:

```sh
mvn test
```

##  Contributing

Contributions are welcome! Here are several ways you can contribute:

- **[Submit Pull Requests](https://github.com/aribzaman/Notes-App-Api/blob/main/CONTRIBUTING.md)**: Review open PRs, and submit your own PRs.
- **[Join the Discussions](https://github.com/aribzaman/Notes-App-Api/discussions)**: Share your insights, provide feedback, or ask questions.
- **[Report Issues](https://github.com/aribzaman/Notes-App-Api/issues)**: Submit bugs found or log feature requests for Notes-app-api.

<details closed>
    <summary>Contributing Guidelines</summary>

1. **Fork the Repository**: Start by forking the project repository to your GitHub account.
2. **Clone Locally**: Clone the forked repository to your local machine using a Git client.
   ```sh
   git clone https://github.com/aribzaman/Notes-App-Api
   ```
3. **Create a New Branch**: Always work on a new branch, giving it a descriptive name.
   ```sh
   git checkout -b new-feature-x
   ```
4. **Make Your Changes**: Develop and test your changes locally.
5. **Commit Your Changes**: Commit with a clear message describing your updates.
   ```sh
   git commit -m 'Implemented new feature x.'
   ```
6. **Push to GitHub**: Push the changes to your forked repository.
   ```sh
   git push origin new-feature-x
   ```
7. **Submit a Pull Request**: Create a PR against the original project repository. Clearly describe the changes and their motivations.

Once your PR is reviewed and approved, it will be merged into the main branch.

</details>

---

##  License

This project is protected under the MIT License. For more details, refer to the [LICENSE](https://github.com/aribzaman/Notes-App-Api/blob/main/LICENSE) file.

---

## Contact Information:
For any further queries, feel free to contact me at:

Email: catcharibzaman@gmail.com

LinkedIn : [Arib Zaman](https://www.linkedin.com/in/aribzaman/)
