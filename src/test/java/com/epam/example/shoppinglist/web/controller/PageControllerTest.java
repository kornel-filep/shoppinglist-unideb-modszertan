package com.epam.example.shoppinglist.web.controller;

import com.epam.example.shoppinglist.web.domain.UserView;
import com.epam.example.shoppinglist.web.service.UserServiceInterface;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.testng.Assert.assertEquals;

public class PageControllerTest {

    @Mock
    private UserServiceInterface userService;

    @Mock
    private Model model;
    @InjectMocks
    private PageController underTest;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void tearDown() {
        userService = null;
        underTest = null;
        model = null;
    }

    @Test
    private void testHomePageShouldReturnHomePageNameAndAddUserInfoToModel() {
        // given
        String expected = "UserListPage";
        List<UserView> expectedUserList = List.of(UserView.builder()
            .userName("kfilep")
            .build()
        );
        given(userService.getAllUser()).willReturn(expectedUserList);
        // when

        String actual = underTest.homePage(model);

        // then
        assertEquals(actual, expected);
        then(model).should().addAttribute("users", expectedUserList);
        then(userService).should().getAllUser();
    }


}
