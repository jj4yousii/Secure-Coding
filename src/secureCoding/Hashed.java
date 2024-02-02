package secureCoding;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
public class Hashed 
{
	public static void main(String[] args) 
	{
		String string="It4d0ri@#"; //input string to be hashed
		System.out.println(getHash(string)); //print the hash of the entered string
	}
	//method to get the hash value of a string using the SHA-256 algorithm
	private static String getHash(String hashValue) 
	{
        String result = "";
        try
        {
        	//creating a messageDigest instance using the SHA-256 algorithm
            MessageDigest message = MessageDigest.getInstance("SHA-256");
            result = encode(message.digest(hashValue.getBytes(StandardCharsets.UTF_8))); //encode the hashed bytes to a base64 string
        } 
        catch (NoSuchAlgorithmException e) 
        {
        	//handling the case where the specified algorithm doesnt exist
            System.out.println("Erorr: the specified algorithm SHA-256 doesnt exist.");
        }
        return result;
    }
	//method to encode a byte array to a base64 string
    private static String encode(byte[] data) 
    {
        return Base64.getEncoder().encodeToString(data);
    }
}
