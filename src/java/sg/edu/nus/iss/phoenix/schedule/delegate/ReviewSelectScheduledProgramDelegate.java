package sg.edu.nus.iss.phoenix.schedule.delegate;

import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.service.ReviewSelectScheduledProgramService;

import java.util.List;

/**
 * Created by yao on 15/09/16.
 */
public class ReviewSelectScheduledProgramDelegate {

    private ReviewSelectScheduledProgramService service;

    public  ReviewSelectScheduledProgramDelegate(){
        this.service=new ReviewSelectScheduledProgramService();
    }

    public List<ProgramSlot> reviewSelectScheduledProgram() {
        return service.reviewSelectScheduledProgram();
    }
}
