package sg.edu.nus.iss.phoenix.user.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDaoImpl;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by NguyenTrung on 27/9/16.
 */

@RunWith(MockitoJUnitRunner.class)
public class ReviewSelectUserServiceTest {
    @Mock
    private UserDaoImpl userDAO;
    @InjectMocks
    private ReviewSelectUserService reviewSelectUserService;


    @Before
    public void setup() throws Exception {

    }

    @Test
    public void processTestLoadingAllUsers() throws Exception {
        User user1 = new User();
        Role role = new Role();
        user1.setAll("1", "123", "test", null);
        String[] str = {"manager"};
        role.setRole(str[0].trim());
        List<User> userList = new ArrayList<>();
        userList.add(user1);

        when(userDAO.loadAll()).thenReturn(userList);

        List<User> loadedList = reviewSelectUserService.getAllUsers();
        System.out.println(reviewSelectUserService.getAllUsers().size());
        assertThat(loadedList.size(),equalTo(userList.size()));

    }
}
