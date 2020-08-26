package in.gov.uidai.demoapi.controllers;

import in.gov.uidai.db.DBConnection;
import in.gov.uidai.model.EIDValidation;
import in.gov.uidai.model.EIDValidationImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.SQLException;

@RestController
public class SampleApiController {

    @GetMapping("/getstatus")
    public String getStatus(){
        return "Success";
    }

    @PostMapping("/statuseid")
    public String getStatusOfEid(@RequestBody String eid) throws SQLException, ClassNotFoundException {
        System.out.println("Received eid :" + eid);
        if(eid.length() != 28){
            return Boolean.toString(false);
        }else {
            EIDValidation eidValidation = new EIDValidationImpl() {
                @Override
                public boolean IsSecondHalfValid(String s) {
                    return true;
                }
            };
            Connection connection = DBConnection.getConnection();
            boolean status = eidValidation.IsFirstHalfValid(eid, connection);
            return Boolean.toString(status);
        }
    }


}
