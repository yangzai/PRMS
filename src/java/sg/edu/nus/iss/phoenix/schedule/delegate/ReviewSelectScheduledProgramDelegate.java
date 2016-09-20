package sg.edu.nus.iss.phoenix.schedule.delegate;

import sg.edu.nus.iss.phoenix.schedule.service.ReviewSelectScheduledProgramService;

/**
 * Created by yao on 15/09/16.
 */
public class ReviewSelectScheduledProgramDelegate {

    private ReviewSelectScheduledProgramService service;

    public  ReviewSelectScheduledProgramDelegate(){
        this.service=new ReviewSelectScheduledProgramService();
    }
}
