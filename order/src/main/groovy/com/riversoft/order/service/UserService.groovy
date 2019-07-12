package com.riversoft.order.service

import groovy.util.logging.Slf4j
import com.riversoft.order.model.UserModel
import org.springframework.stereotype.Service

@Slf4j
@Service

class UserService {

    /**initialization List to save orders
     */

    private List<UserModel> users = []

    /**add user to object
     * @param user
     * @return
     */

    List<UserModel> addUser(UserModel user) {

        users.add(user)

        def allUsers = users.findAll()

        log.info("save users : id - ${user.userId}, name - ${user.userName}")

        log.info("All users : ${allUsers.userName}")

        return allUsers

    }

    /**give all Users
     * @return
     */

    List<UserModel> getAllUsers() {

        return users.findAll()

    }

    /**give User by Id
     * @param userId
     * @return
     */

    List<UserModel> searchUserById(String userId) {

        def foundUser = users.findAll { it.userId == userId }

        if(!foundUser) { throw new IllegalStateException("Sorry dude but i dont have id like that : ${userId}") }

        return foundUser

    }

    /**delete User by Id
     * @param userId
     * @return
     */

    UserModel deleteUser(String userId) {

        def userToDelete = users.find { it.userId == userId }

        if(!userToDelete) { throw new IllegalStateException("Sorry dude but i dont have id like that : ${userId}") }

        users.remove(userToDelete)

        log.info("Delete user : id - ${userToDelete.userId}")

        return userToDelete

    }


    /**update User name by Id
     * @param userId
     * @param userName
     * @return
     */

    UserModel updateUserName(String userId, String userName) {

        def foundUserById = users.find {it.userId == userId}

        if(!foundUserById) { throw new IllegalStateException("Sorry dude but i dont have id like that : ${userId}") }

        foundUserById.userName = userName

        log.info( "change event name for : ${foundUserById.userId}")

        return foundUserById

    }

}