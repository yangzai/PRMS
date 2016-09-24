package sg.edu.nus.iss.phoenix.user.delegate;

import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.user.entity.Presenter;
import sg.edu.nus.iss.phoenix.user.entity.Producer;
import sg.edu.nus.iss.phoenix.user.service.ReviewSelectPresenterProducerService;
import sg.edu.nus.iss.phoenix.user.service.ReviewSelectUserService;

import java.util.List;

/**
 * Created by gao.haijun on 15/09/16.
 */
public class ReviewSelectPresenterProducerDelegate {
    private ReviewSelectPresenterProducerService service;

    public ReviewSelectPresenterProducerDelegate() {
        super();
        service = new ReviewSelectPresenterProducerService();
    }

    public List<Presenter> getAllPresenters(){
        return service.getAllPresenters();
    }

    public List<Producer> getAllProducers() {
        return service.getAllProducers();
    }
}
