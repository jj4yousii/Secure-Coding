package secureCoding;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LoginTest2 {

	@Test
    public void testValidDoctorLogin1() 
	{
        HealthClinic healthClinic = new HealthClinic();
        String Doctor = "ahmed";
        String Pass= "Y3!stheg0@t";
        String hashedPass = HealthClinic.getTheHash(Pass);
        String userType = "Doctor";
        HealthClinic.ReadFile(Doctor, hashedPass, userType);
        
    }
	@Test
	public void testValidDoctorLogin2() 
	{
        HealthClinic healthClinic = new HealthClinic();
        String Doctor = "abdullah";
        String Pass= "ImR3t@rded";
        String hashedPass = HealthClinic.getTheHash(Pass);
        String userType = "Doctor";
        HealthClinic.ReadFile(Doctor, hashedPass, userType);
    }
}
