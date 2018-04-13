package com.example.postsapi.controllers;


import com.example.postsapi.models.Post;
import com.example.postsapi.models.User;
import com.example.postsapi.repositories.PostRepository;
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
@WebMvcTest(PostController.class)
public class PostControllerTest {

    private Post newPost;
    private Post updatedSecondPost;

    @MockBean
    private PostRepository mockPostRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper jsonObjectMapper;

    @Before
    public void setUp() {

        User firstUser = new User(
                "username1",
                "some first name",
                "some last name",
                false
        );

        User secondUser = new User(
                "username2",
                "some other first name",
                "some other last name",
                false
        );

        Post firstPost = new Post(
                "title1",
                "content1",
                1L,
                "username1"
        );

        Post secondPost = new Post(
                "title2",
                "content2",
                2L,
                "username2"
        );

        Iterable<Post> mockPosts =
                Stream.of(firstPost, secondPost).collect(Collectors.toList());

        given(mockPostRepository.findAll()).willReturn(mockPosts);
        given(mockPostRepository.findOne(1L)).willReturn(firstPost);
        given(mockPostRepository.findOne(6L)).willReturn(null);

        doAnswer(invocation -> {
            throw new EmptyResultDataAccessException("ERROR MESSAGE FROM MOCK!!!", 1234);
        }).when(mockPostRepository).delete(6L);

        newPost = new Post(
                "title3",
                "content3",
                3L,
                "username3"
        );
        given(mockPostRepository.save(newPost)).willReturn(newPost);

        updatedSecondPost = new Post(
                "utitle2",
                "ucontent2",
                1L,
                "username1"
        );
        given(mockPostRepository.save(updatedSecondPost)).willReturn(updatedSecondPost);
    }

    // Find All Tests
    @Test
    public void findAllPosts_success_returnsStatusOK() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllPosts_success_returnAllPostsAsJSON() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void findAllPosts_success_returnTitleForEachPost() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].title", is("title1")));
    }

    @Test
    public void findAllPosts_success_returnContentForEachPost() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].content", is("content1")));
    }

    @Test
    public void findAllPosts_success_returnUserForEachPost() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].userid", is(1)));
    }


    // Find post by id tests
    @Test
    public void findPostById_success_returnsStatusOK() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void findPostById_success_returnTitle() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.title", is("title1")));
    }

    @Test
    public void findPostById_success_returnContent() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.content", is("content1")));
    }

    @Test
    public void findPostById_success_returnUser() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.userid", is(1)));
    }

    @Test
    public void findPostById_failure_userNotFoundReturns404() throws Exception {

        this.mockMvc
                .perform(get("/6"))
                .andExpect(status().isNotFound());
    }


    // Delete post by id tests
    @Test
    public void deletePostById_success_returnsStatusOk() throws Exception {

        this.mockMvc
                .perform(delete("/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deletePostById_success_deletesViaRepository() throws Exception {

        this.mockMvc.perform(delete("/1"));

        verify(mockPostRepository, times(1)).delete(1L);
    }

    @Test
    public void deletePostById_failure_userNotFoundReturns404() throws Exception {

        this.mockMvc
                .perform(delete("/6"))
                .andExpect(status().isNotFound());
    }


    // Create post tests
    @Test
    public void createPost_success_returnsStatusOk() throws Exception {

        this.mockMvc
                .perform(
                        post("/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newPost))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void createPost_success_returnsTitle() throws Exception {

        this.mockMvc
                .perform(
                        post("/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newPost))
                )
                .andExpect(jsonPath("$.title", is("title3")));
    }

    @Test
    public void createPost_success_returnsContent() throws Exception {

        this.mockMvc
                .perform(
                        post("/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newPost))
                )
                .andExpect(jsonPath("$.content", is("content3")));
    }

    @Test
    public void createPost_success_returnsUser() throws Exception {

        this.mockMvc
                .perform(
                        post("/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newPost))
                )
                .andExpect(jsonPath("$.userid", is(3)));
    }

    // Update post by id
    @Test
    public void updatePostById_success_returnsStatusOk() throws Exception {

        this.mockMvc
                .perform(
                        patch("/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondPost))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void updatePostById_success_returnsUpdatedTitle() throws Exception {

        this.mockMvc
                .perform(
                        patch("/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondPost))
                )
                .andExpect(jsonPath("$.title", is("utitle2")));
    }

    @Test
    public void updatePostById_success_returnsUpdatedContent() throws Exception {

        this.mockMvc
                .perform(
                        patch("/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondPost))
                )
                .andExpect(jsonPath("$.content", is("ucontent2")));
    }

    @Test
    public void updatePostById_success_returnsUpdatedUser() throws Exception {

        this.mockMvc
                .perform(
                        patch("/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondPost))
                )
                .andExpect(jsonPath("$.userid", is(1)));
    }

    @Test
    public void updatePostById_failure_userNotFoundReturns404() throws Exception {

        this.mockMvc
                .perform(
                        patch("/6")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondPost))
                )
                .andExpect(status().isNotFound());
    }


}
