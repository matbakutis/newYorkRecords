package com.controllers;


import com.example.backendusers.controllers.UserController;
import com.example.backendusers.models.User;
import com.example.backendusers.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    private User newUser;
    private User updatedSecondUser;

    @MockBean
    private UserRepository mockUserRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper jsonObjectMapper;

    @Before
    public void setUp() {
        User firstUser = new User(
                "someone",
                "Ima",
                "Person"
        );

        User secondUser = new User(
                "someone_else",
                "Someone",
                "Else"
        );

        Iterable<User> mockUsers =
                Stream.of(firstUser, secondUser).collect(Collectors.toList());

        given(mockUserRepository.findAll()).willReturn(mockUsers);
        given(mockUserRepository.findById(1L)).willReturn(java.util.Optional.ofNullable(firstUser));
        given(mockUserRepository.findById(6L)).willReturn(java.util.Optional.ofNullable(null));

        doAnswer(invocation -> {
            throw new EmptyResultDataAccessException("ERROR MESSAGE FROM MOCK!!!", 1234);
        }).when(mockUserRepository).deleteById(6L);

        newUser = new User(
                "new_user_for_create",
                "New",
                "User"
        );
        given(mockUserRepository.save(newUser)).willReturn(newUser);

        updatedSecondUser = new User(
                "updated_username",
                "Updated",
                "Info"
        );
        given(mockUserRepository.save(updatedSecondUser)).willReturn(updatedSecondUser);
    }

    // Find All Tests
    @Test
    public void findAllUsers_success_returnsStatusOK() throws Exception {

        this.mockMvc
                .perform(get("/api/users"))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllUsers_success_returnAllUsersAsJSON() throws Exception {

        this.mockMvc
                .perform(get("/api/users"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void findAllUsers_success_returnUserNameForEachUser() throws Exception {

        this.mockMvc
                .perform(get("/api/users"))
                .andExpect(jsonPath("$[0].userName", is("someone")));
    }

    @Test
    public void findAllUsers_success_returnFirstNameForEachUser() throws Exception {

        this.mockMvc
                .perform(get("/api/users"))
                .andExpect(jsonPath("$[0].firstName", is("Ima")));
    }

    @Test
    public void findAllUsers_success_returnLastNameForEachUser() throws Exception {

        this.mockMvc
                .perform(get("/api/users"))
                .andExpect(jsonPath("$[0].lastName", is("Person")));
    }


    // Find user by id tests
    @Test
    public void findUserById_success_returnsStatusOK() throws Exception {

        this.mockMvc
                .perform(get("/api/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void findUserById_success_returnUserName() throws Exception {

        this.mockMvc
                .perform(get("/api/users/1"))
                .andExpect(jsonPath("$.userName", is("someone")));
    }

    @Test
    public void findUserById_success_returnFirstName() throws Exception {

        this.mockMvc
                .perform(get("/api/users/1"))
                .andExpect(jsonPath("$.firstName", is("Ima")));
    }

    @Test
    public void findUserById_success_returnLastName() throws Exception {

        this.mockMvc
                .perform(get("/api/users/1"))
                .andExpect(jsonPath("$.lastName", is("Person")));
    }

    @Test
    public void findUserById_failure_userNotFoundReturns404() throws Exception {

        this.mockMvc
                .perform(get("/api/users/6"))
                .andExpect(status().isNotFound());
    }


    // Delete user by id tests
    @Test
    public void deleteUserById_success_returnsStatusOk() throws Exception {

        this.mockMvc
                .perform(delete("/api/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUserById_success_deletesViaRepository() throws Exception {

        this.mockMvc.perform(delete("/api/users/1"));

        verify(mockUserRepository, times(1)).deleteById(1L);
    }

    @Test
    public void deleteUserById_failure_userNotFoundReturns404() throws Exception {

        this.mockMvc
                .perform(delete("/api/users/6"))
                .andExpect(status().isNotFound());
    }


    // Create user tests
    @Test
    public void createUser_success_returnsStatusOk() throws Exception {

        this.mockMvc
                .perform(
                        post("/api/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newUser))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void createUser_success_returnsUserName() throws Exception {

        this.mockMvc
                .perform(
                        post("/api/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newUser))
                )
                .andExpect(jsonPath("$.userName", is("new_user_for_create")));
    }

    @Test
    public void createUser_success_returnsFirstName() throws Exception {

        this.mockMvc
                .perform(
                        post("/api/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newUser))
                )
                .andExpect(jsonPath("$.firstName", is("New")));
    }

    @Test
    public void createUser_success_returnsLastName() throws Exception {

        this.mockMvc
                .perform(
                        post("/api/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newUser))
                )
                .andExpect(jsonPath("$.lastName", is("User")));
    }

    // Update user by id
    @Test
    public void updateUserById_success_returnsStatusOk() throws Exception {

        this.mockMvc
                .perform(
                        patch("/api/users/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void updateUserById_success_returnsUpdatedUserName() throws Exception {

        this.mockMvc
                .perform(
                        patch("/api/users/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
                )
                .andExpect(jsonPath("$.userName", is("updated_username")));
    }

    @Test
    public void updateUserById_success_returnsUpdatedFirstName() throws Exception {

        this.mockMvc
                .perform(
                        patch("/api/users/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
                )
                .andExpect(jsonPath("$.firstName", is("Updated")));
    }

    @Test
    public void updateUserById_success_returnsUpdatedLastName() throws Exception {

        this.mockMvc
                .perform(
                        patch("/api/users/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
                )
                .andExpect(jsonPath("$.lastName", is("Info")));
    }

    @Test
    public void updateUserById_failure_userNotFoundReturns404() throws Exception {

        this.mockMvc
                .perform(
                        patch("/apid/users/6")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
                )
                .andExpect(status().isNotFound());
    }


}
