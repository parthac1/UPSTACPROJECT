package org.upgrad.upstac.testrequests.lab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.upgrad.upstac.testrequests.TestRequest;
import org.upgrad.upstac.users.User;
import org.upgrad.upstac.config.security.UserLoggedInService;
import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Validated
public class LabResultService {


    @Autowired
    private LabResultRepository labResultRepository;


    private static Logger logger = LoggerFactory.getLogger(LabResultService.class);

    @Autowired
    private UserLoggedInService userLoggedInService;

    private LabResult createLabResult(User tester, TestRequest testRequest) {
        LabResult result = new LabResult();
        result.setRequest(testRequest);
        User user = userLoggedInService.getLoggedInUser();

        result.setTester(user);
        return saveLabResult(result);

    }

    @Transactional
    LabResult saveLabResult(LabResult labResult) {
        return labResultRepository.save(labResult);
    }



    public LabResult assignForLabTest(TestRequest testRequest, User tester) {

        return createLabResult(tester, testRequest);


    }


    public LabResult updateLabTest(TestRequest testRequest, CreateLabResult createLabResult) {

        LabResult result = new LabResult();
        result.setRequest(testRequest);
        result.setBloodPressure(createLabResult.getBloodPressure());
        result.setComments(createLabResult.getComments());
        result.setHeartBeat(createLabResult.getHeartBeat());
        result.setOxygenLevel(createLabResult.getOxygenLevel());
        result.setTemperature(createLabResult.getTemperature());
        result.setResult(createLabResult.getResult());
        result.setUpdatedOn(LocalDate.now());

        return result;
    }


}
