package secureCoding;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LoginTest1 
{
	@Test
    public void testValidDoctorLogin1() 
	{
        HealthClinic healthClinic = new HealthClinic();
        String Patient = "obada";
        String Pass= "s1ckP@tient";
        String hashedPass = HealthClinic.getTheHash(Pass);
        String userType = "Patient";
        HealthClinic.ReadFile(Patient, hashedPass, userType);
    }
	@Test
	public void testValidDoctorLogin2() 
	{
        HealthClinic healthClinic = new HealthClinic();
        String Patient = "khaled";
        String Pass= "s1ckP@tientt";
        String hashedPass = HealthClinic.getTheHash(Pass);
        String userType = "Patient";
        HealthClinic.ReadFile(Patient, hashedPass, userType);
    }
}
