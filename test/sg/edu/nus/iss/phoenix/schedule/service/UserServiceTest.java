package sg.edu.nus.iss.phoenix.schedule.service;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.*;
import org.mockito.internal.matchers.Null;
import org.mockito.runners.MockitoJUnitRunner;
import sg.edu.nus.iss.phoenix.authenticate.dao.RoleDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.RoleDaoImpl;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDaoImpl;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.user.controller.ReturnCode;
import sg.edu.nus.iss.phoenix.user.service.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Haijun on 2016/9/23.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserDaoImpl userDAO;
    @Mock
    private RoleDaoImpl roleDAO;
    @InjectMocks
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    /*
        Examples of TDD with Junit and mockito:
        here illustrate an example for how create testcases to validate codes on service layer
        Created by Gao.Haijun  2050.8.9
    */
    @Test
    public void processCreateUser() throws Exception {
        //
        //Step 1: prepare the object for test inputs before executing create user
        //
        User user = new User();
        Role role = new Role();
        user.setAll("1", "123", "test", null);
        String[] str = {"manager"};
        role.setRole(str[0].trim());
        //
        //Step 2: make stub on the UserDAO and RoleDAO functions, because we only test function on user service,
        //       we assume the DAO component works well. so give a assumption value for return when service call its DAO
        //Note: before this, userDAO and role DAO should be mocked by writing the notation of @mock in the class
        //      the user Service which marked with @InjectMocks will try to inject all mocks into its object before execution
        //
        when(userDAO.searchMatching(user.getId())).thenReturn(null);
        when(roleDAO.searchMatching(str[0].trim())).thenReturn(role);

        //Step 3: after cater all input and stub conditions. the function of create user should be return succeed as expected
        assertThat(ReturnCode.SUCCESS, is(userService.processCreate(user, str)));

        //step 4: verify the searchMatching function for user & role only be executed once seperately
        verify(userDAO).searchMatching(user.getId());
        verify(roleDAO).searchMatching(str[0].trim());

        //step 5: new argumentCaptor object to capture updated user object which will be insert into database right way
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userDAO).create(userCaptor.capture());
        User updatedUser = userCaptor.getValue();

        //step 6: verify all attributes are expected and correct in updated user object
        assertThat(updatedUser.getId(), is("1"));
        assertThat(updatedUser.getName(), is("test"));
        assertThat(updatedUser.getPassword(), is("123"));
        assertThat(updatedUser.getRolesInSring().trim(), equalTo("manager"));

        //step 7: make sure no other interaction with userDAO during execution
        verifyNoMoreInteractions(userDAO);
        verifyNoMoreInteractions(roleDAO);

        //step 8: cleanup object manually <Optional>
    }

    @Test
    public void processCreateUserDuplicate() throws Exception {
        User user = new User();
        user.setAll("2", "123", "test", null);
        when(userDAO.searchMatching(user.getId())).thenReturn(user);
        assertThat(ReturnCode.USER_DUPLICATED, is(userService.processCreate(user, null)));

        verify(userDAO).searchMatching(user.getId());

        verifyNoMoreInteractions(userDAO);
        verifyZeroInteractions(roleDAO);
    }

    @Test
    public void processCreateUserNoRole() throws Exception {
        User user = new User();
        user.setAll("3", "123", "test", null);
        String[] str = {""}; //put null or incorrect name
        when(userDAO.searchMatching(user.getId())).thenReturn(null);
        assertThat("Can we create user without role?", false,
                is(userService.processCreate(user, str) == ReturnCode.SUCCESS));

        verify(userDAO).searchMatching(user.getId());

        //verifyNoMoreInteractions(userDAO);
        verifyZeroInteractions(roleDAO);

    }
}
