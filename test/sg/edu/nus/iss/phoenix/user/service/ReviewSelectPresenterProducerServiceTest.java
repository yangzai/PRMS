package sg.edu.nus.iss.phoenix.user.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDaoImpl;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.user.entity.Presenter;
import sg.edu.nus.iss.phoenix.user.entity.Producer;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by NguyenTrung on 27/9/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ReviewSelectPresenterProducerServiceTest {
    @Mock
    private UserDaoImpl userDao;
    @InjectMocks
    private ReviewSelectPresenterProducerService service;

    @Before
    public void setUp() {

    }

    @Test
    public void processSelectAllPresenterTest() throws Exception {
        List<User> presenterList = new ArrayList<>();
        Presenter presenter = new Presenter();
        presenter.setAll("1", "123", "presenter1", "presenter");
        presenterList.add(presenter);

        when(userDao.loadAllPresenters()).thenReturn(presenterList);
        assertThat(service.getAllPresenters(), equalTo(presenterList));
        //verifyNoMoreInteractions(userDao);
    }


    @Test
    public void processSelectAllProducerTest() throws Exception {
        List<User> producerList = new ArrayList<>();
        Producer producer = new Producer();
        producer.setAll("1", "123", "presenter1", "producer");
        producerList.add(producer);
        when(userDao.loadAllProducers()).thenReturn(producerList);
        assertThat(service.getAllProducers(), equalTo(producerList));
    }

}
