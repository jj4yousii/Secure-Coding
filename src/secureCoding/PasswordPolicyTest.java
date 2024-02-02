package secureCoding;

import static org.junit.Assert.*;

import org.junit.Test;

public class PasswordPolicyTest {
	@Test
    public void testValidPassword() 
	{
        HealthClinic healthClinic = new HealthClinic();
        String validPass = "Xwy6@f9mm";
        boolean isValid = HealthClinic.passwordPolicy(validPass);
        assertTrue(isValid);
    }
    @Test
    public void testInvalidPassword() 
    {
        HealthClinic healthClinic = new HealthClinic();
        String invalidPass = "badpass";
        boolean inValid = HealthClinic.passwordPolicy(invalidPass);
        assertFalse(inValid);
    }
}
