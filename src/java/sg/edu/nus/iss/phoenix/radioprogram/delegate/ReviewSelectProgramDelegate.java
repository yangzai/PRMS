package sg.edu.nus.iss.phoenix.radioprogram.delegate;

import java.util.List;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.radioprogram.service.ReviewSelectProgramService;

public class ReviewSelectProgramDelegate {
    private ReviewSelectProgramService service;
    
	public ReviewSelectProgramDelegate() {
		service = new ReviewSelectProgramService();
	}
	
	public List<RadioProgram> reviewSelectRadioProgram() {
		return service.reviewSelectRadioProgram();	
	}

}
