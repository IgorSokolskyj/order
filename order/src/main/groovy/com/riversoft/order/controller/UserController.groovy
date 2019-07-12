package com.riversoft.order.controller

import com.riversoft.order.model.UserModel
import com.riversoft.order.service.UserService
import groovy.util.logging.Slf4j
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

import javax.validation.Valid

@Slf4j
@RequestMapping("/api/user")
@EnableSwagger2
@RestController

class UserController {

    @Autowired

    private UserService userService

    @ApiOperation(value = "Add user")
    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)

    List<UserModel> addUser(@RequestBody @Valid UserModel user) {

        log.info("received user : id - ${user.userId}, name - ${user.userName}")

        return userService.addUser(user)

    }

    @ApiOperation(value = "Get an user by Id")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)

    List<UserModel> getUsers(@RequestParam(required = false) String userId) {

        if (userId) {

            log.info("give to product user by id : ${userId}")

            return userService.searchUserById(userId)

        } else {

            log.info("give product all users")

            return userService.getAllUsers()

        }

    }

    @ApiOperation(value = "Delete user by Id")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)

    UserModel deleteUser(@RequestParam String userId) {

        log.info("received user = [$userId] name id to delete this event")

        return userService.deleteUser(userId)

    }

    @ApiOperation(value = "Update user by Id")
    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)

    UserModel updateUserName(@RequestBody UserModel user, @PathVariable String userId, @RequestHeader(value = "ver-api") int verApi) {

        if (verApi < 10) {

            throw new IllegalStateException("sorry")

        } else {

            log.info("received update name by id : ${userId}")

            return userService.updateUserName(userId, user.userName)

        }
    }

}