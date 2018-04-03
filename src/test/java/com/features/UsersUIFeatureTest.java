package com.features;

import com.example.backendusers.models.User;
import com.example.backendusers.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UsersUIFeatureTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository.deleteAll();
    }

    @After
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void shouldAllowFullCrudFunctionalityForAUser() throws Exception {

        User firstUser = new User(
                "someone",
                "Ima",
                "Person"
        );
        firstUser = userRepository.save(firstUser);
        Long firstUserId = firstUser.getId();

        User secondUser = new User(
                "someone_else",
                "Someone",
                "Else"
        );
        secondUser = userRepository.save(secondUser);
        Long secondUserId = secondUser.getId();


        System.setProperty("selenide.browser", "Chrome");

        // Visit the UI in a browser
        open("http://localhost:3000");

        // Visit the users page
        $("#usersLink").click();

        // There should only be two users
        $$("[data-user-display]").shouldHave(size(2));

        $("#user-" + firstUserId + "-user-name").shouldHave(text("someone"));
        $("#user-" + firstUserId + "-first-name").shouldHave(text("Ima"));
        $("#user-" + firstUserId + "-last-name").shouldHave(text("Person"));

        $("#user-" + secondUserId + "-user-name").shouldHave(text("someone_else"));
        $("#user-" + secondUserId + "-first-name").shouldHave(text("Someone"));
        $("#user-" + secondUserId + "-last-name").shouldHave(text("Else"));

        // Visit the new user page
        $("#new-user-link").click();

        // Make sure the link worked and the form is now showing
        $("#new-user-form").should(appear);

        // Add a new user
        $("#new-user-user-name").sendKeys("third_user");
        $("#new-user-first-name").sendKeys("Third");
        $("#new-user-last-name").sendKeys("User");
        $("#new-user-submit").click();

        // Make sure we're now on the users page again
        $("#users-wrapper").should(appear);

        // Now there should be three Users
        $$("[data-user-display]").shouldHave(size(3));

        refresh();

        // Now there should be three Users again after the refresh
        $$("[data-user-display]").shouldHave(size(3));

        // Check that the data is showing up for the third User
        Long thirdUserId = secondUserId + 1;
        $("#user-" + thirdUserId + "-user-name").shouldHave(text("third_user"));
        $("#user-" + thirdUserId + "-first-name").shouldHave(text("Third"));
        $("#user-" + thirdUserId + "-last-name").shouldHave(text("User"));

        // Test Deleting the first user
        $("#user-" + firstUserId).should(exist);
        $$("[data-user-display]").shouldHave(size(3));

        $("#delete-user-" + firstUserId).click();
        $("#user-" + firstUserId).shouldNot(exist);

        $$("[data-user-display]").shouldHave(size(2));

    }

    @Test
    public void shouldAllowLogInAndLogOutFunctionalityForAUser() throws Exception {

        User firstUser = new User(
                "someone",
                "Ima",
                "Person"
        );
        firstUser = userRepository.save(firstUser);
        Long firstUserId = firstUser.getId();

        User secondUser = new User(
                "someone_else",
                "Someone",
                "Else"
        );
        secondUser = userRepository.save(secondUser);
        Long secondUserId = secondUser.getId();


        System.setProperty("selenide.browser", "Chrome");

        // Visit the UI in a browser
        open("http://localhost:3000");

        // Make sure the log in link is visible
        $("#loginLink").should(appear);

        // Make sure the log in link is visible
        $("#logoutLink").shouldNot(appear);

        // Visit the log in page
        $("#loginLink").click();

        // Make sure the link worked and the form is now showing
        $("#login-form").should(appear);

        // Log in
        $("#login-user-name").sendKeys("someone");
        $("#login-password").sendKeys("Third");
        $("#login-submit").click();

        // Make sure we're now on the users page again
        $("#users-wrapper").should(appear);

        // Make sure the log in link is visible
        $("#loginLink").shouldNot(appear);

        // Make sure the log in link is visible
        $("#logoutLink").should(appear);

        // Logout
        $("#logoutLink").click();

        // Make sure the log in link is visible
        $("#loginLink").should(appear);

        // Make sure the log out link is not visible
        $("#logoutLink").shouldNot(appear);

        // Visit the log in page
        $("#loginLink").click();

        // Make sure the link worked and the form is now showing
        $("#login-form").should(appear);

        // log in with bad info
        $("#login-user-name").sendKeys("asd user");
        $("#login-password").sendKeys("Third");
        $("#login-submit").click();

        // Make sure we're now on the users page again
        $("#users-wrapper").should(appear);

        // Make sure the log in link is visible
        $("#loginLink").should(appear);

        // Make sure the log out link is not visible
        $("#logoutLink").shouldNot(appear);

        // Make sure error message is shown
        $("#login-error-message").shouldHave(text("Username or Password Incorrect"));

    }



}