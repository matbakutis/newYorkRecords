package com.example.postsapi.repositories;

import com.example.postsapi.models.Post;
import com.example.postsapi.models.User;
import com.google.common.collect.Iterables;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        postRepository.deleteAll();
        userRepository.deleteAll();
        entityManager.flush();

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

        entityManager.persist(firstUser);
        entityManager.persist(secondUser);

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

        entityManager.persist(firstPost);
        entityManager.persist(secondPost);
        entityManager.flush();
    }

    @Test
    public void findAll_returnsAllPosts() {
        Iterable<Post> postsFromDb = postRepository.findAll();
        assertThat(Iterables.size(postsFromDb), is(2));
    }

    @Test
    public void findAll_returnsTitle() {
        Iterable<Post> postsFromDb = postRepository.findAll();

        String secondPostsTitle = Iterables.get(postsFromDb, 1).getTitle();

        assertThat(secondPostsTitle, is("title2"));
    }

    @Test
    public void findAll_returnsContent() {
        Iterable<Post> postsFromDb = postRepository.findAll();

        String secondPostsContent = Iterables.get(postsFromDb, 1).getContent();

        assertThat(secondPostsContent, is("content2"));
    }

    @Test
    public void findAll_returnsUser() {
        Iterable<Post> postsFromDb = postRepository.findAll();

        Long secondPostsUser = Iterables.get(postsFromDb, 1).getUserid();

        assertThat(secondPostsUser, is(2L));
    }


}
