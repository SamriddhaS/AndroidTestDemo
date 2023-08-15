package com.example.androidtestdemo.repo

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserLoginUseCaseTest {

    @Mock
    lateinit var repository: Repository

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun loginUserUsingNameAndPassword_withInvalidUserName() {
        Mockito
            .`when`(repository.userLogin(anyString(), anyString()))
            .thenReturn(LOGIN_STATUS.INVALID_USER_NAME)
        val userLoginUseCase = UserLoginUseCase(repository)
        val result = userLoginUseCase.loginUserUsingNameAndPassword("dasdsad","dsadasd")
        assertEquals(1,result)
    }

    @Test
    fun loginUserUsingNameAndPassword_withInvalidPassword() {
        Mockito
            .`when`(repository.userLogin(anyString(), anyString()))
            .thenReturn(LOGIN_STATUS.INVALID_PASSWORD)
        val userLoginUseCase = UserLoginUseCase(repository)
        val result = userLoginUseCase.loginUserUsingNameAndPassword("dasdsad","dsadasd")
        assertEquals(2,result)
    }

    @Test
    fun loginUserUsingNameAndPassword_withSuccess() {
        Mockito
            .`when`(repository.userLogin(anyString(), anyString()))
            .thenReturn(LOGIN_STATUS.LOGIN_SUCCESS)
        val userLoginUseCase = UserLoginUseCase(repository)
        val result = userLoginUseCase.loginUserUsingNameAndPassword("dasdsad","dsadasd")
        assertEquals(3,result)
    }
}