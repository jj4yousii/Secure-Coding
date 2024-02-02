package secureCoding;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;
public class HealthClinic 
{
	private HealthClinic()
	{
		//modified
	}
    //read user information from a specific file and perform actions based on the file
    private static void readTheUserFile(BufferedReader bufferReaderFile, String name, String hashPass, String userType, String type) 
    	throws IOException 
    {
        for (String line;(line = bufferReaderFile.readLine()) != null;) 
        {
            String[] columns = line.split("@");
            if (columns.length >= 2 && columns[0].equalsIgnoreCase(name) && columns[1].equals(hashPass)) 
            {
                if (userType.equals(type))
                {
                    switch (type) 
                    {
                    case "Register":
                        Register(name);
                        break;
                        case "Doctor":
                            Doctor(name);
                            break;
                        case "Patient":
                            Patient(name);
                            break;
                        default:
                            System.out.println("Invalid");
                            break;
                    }
                }
            }
        }
    }
    //method to read the user information from files based on the user type
    private static void ReadFile(String name, String hashPass, String type)
    {
        BufferedReader bufferReaderFile = null;
        try 
        {
            //read the doctor file
            bufferReaderFile = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\secure coding\\doctor.txt"));
            readTheUserFile(bufferReaderFile, name, hashPass, type, "Doctor");
            //read the patient file
            bufferReaderFile = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\secure coding\\patient.txt"));
            readTheUserFile(bufferReaderFile, name, hashPass, type, "Patient");
            //read the register file
            bufferReaderFile = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\secure coding\\register.txt"));
            readTheUserFile(bufferReaderFile, name, hashPass, type, "Register");
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        } 
        finally
        {
        	//close the file reader
            try 
            {
                if (bufferReaderFile != null) 
                {
                    bufferReaderFile.close();
                }
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }
	//the main method where the program starts
    public static void main(String[] args) 
    {
        try(Scanner scanner = new Scanner(System.in)) //modified code
        {
        // 3 login attempts
        for (int count = 0;count != 3; count++) //modified
        {
            String name;
            String pass;
            System.out.println("Enter your username please:");
            name = scanner.nextLine();
            Log.ToLog("Login attempt for the user:" +name); //log the login attempt
            System.out.println("Enter your password please:");
            pass = scanner.nextLine();
            String hashPass = getTheHash(pass);
            String userType;
            System.out.println("Enter the user type (Doctor/Patient/Register):");
            userType = scanner.nextLine();
            ReadFile(name, hashPass, userType); 
        }
        }

    }
    //hash for a given string using the SHA-256 algorithm
    private static String getTheHash(String hashValue) 
    {
        String result = "";
        try
        {
            MessageDigest messageD = MessageDigest.getInstance("SHA-256");
            result = encoding(messageD.digest(hashValue.getBytes(StandardCharsets.UTF_8)));
        }
        catch (NoSuchAlgorithmException e) 
        {
            System.out.println("Error: the algorithm SHA-256 is not found!!");
        }
        return result;
    }
    //method to encode byte data to base64
    private static String encoding(byte[] data) 
    {
        return Base64.getEncoder().encodeToString(data);
    }
    //write the user information to a file
    private static void WriteFile(String nameFile, String name, String hashPass, int phoneNumber, String gender,int age, String UserType) 
    {
    	BufferedWriter bufferWrite = null;
        BufferedReader bufferReader = null;
        try 
        {
            bufferReader = new BufferedReader(new FileReader(nameFile));
            for (String line = bufferReader.readLine(); line != null; line = bufferReader.readLine())  
            {
                String[] columns = line.split("@");
                if (columns.length >= 1 && name.equalsIgnoreCase(columns[0])) //modified
                {
                    System.out.println("The username exists");
                    return;
                }
                if (columns.length >= 3 && columns[2].equals(String.valueOf(phoneNumber))) 
                {
                    System.out.println("The phone number exists");
                    return;
                }
            }
            bufferWrite = new BufferedWriter(new FileWriter(nameFile, true));
            bufferWrite.write(name + "@" + hashPass + "@" + phoneNumber + "@" + gender + "@" + age + "@" + UserType);
            bufferWrite.newLine();
            System.out.println("Added user successfully");
        } 
        catch (IOException | NumberFormatException e) 
        {
            System.out.println("Error: reading the file");
        } 
        finally 
        {
            try 
            {
                if (bufferReader != null) 
                {
                    bufferReader.close();
                }
                if (bufferWrite != null) 
                {
                    bufferWrite.close();
                }
            } 
            catch (IOException e) 
            {
                System.out.println("Error: closing" + e.getMessage());
            }
        }
    }
    //user registration process method
    private static void Register(String user) 
    {
        System.out.println("Welcome you have logged in successfully " + user);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Register for a new user");
        int age;
        int phoneNumber;
        String username;
        String password;
        String gender;
        String userType;
        //collecting the user information
        System.out.println("Enter the user information please");
        System.out.println("Username: ");
        username = scanner.nextLine();
        Log.ToLog("The user tried to login" +username);
        System.out.println("Password of the Registrar : ");
        password = scanner.nextLine();
        //checking if the password entered is valid
        if (passwordPolicy(password)) {
            String hashPass = getTheHash(password);
            System.out.println("Gender of the Registrar: ");
            gender = scanner.nextLine();
            System.out.println("Ageof the Registrar: ");
            age = scanner.nextInt();
            System.out.println("Phone Number of the Registrar: ");
            phoneNumber = scanner.nextInt();
            scanner.nextLine(); 
            System.out.println("Are you a Doctor or a Patient? ");
            userType = scanner.nextLine();
            //calling the isPasswordValid function
            if (userType.equals("Doctor") || userType.equals("Patient"))
            {
                if (passwordPolicy(password)) 
                {
                    //continue with user registration
                    if (userType.equals("Doctor")) 
                    {
                        WriteFile("C:\\Users\\User\\Desktop\\secure coding\\doctor.txt", username, hashPass, phoneNumber, gender, age, userType);
                    } 
                    else if (userType.equals("Patient")) 
                    {
                        WriteFile("C:\\Users\\User\\Desktop\\secure coding\\patient.txt", username, hashPass, phoneNumber, gender, age, userType);
                    }
                } 
                else 
                {
                    System.out.println("Password doesn't meet the criteria. Registration is canceled.");
                }
            }
            else 
            {
                System.err.println("Invalid input");
            }
        } 
        else 
        {
            System.out.println("Password doesnst meet the criteria. Registration is canceled.");
        }
    }
    //method to check is the password meets the right criteria
    private static boolean passwordPolicy(String pass) 
    {
        //checking is the password is between 8-20 characters
        return (pass.length() >= 8 && pass.length() <= 20 && //modified code
            pass.matches(".*[A-Z].*") && //the password should contain at lest one capital letter
            pass.matches(".*[!@#$%^&*()-_=+{};:'\",.<>/?\\[\\]\\\\].*") && //the password should contain at least one symbol
            pass.matches(".*\\d.*"));   //the password should contain at least one digit
    }
    //method for the patient-related actions
    private static void Patient(String name) 
    {
        int patientChoice;
        System.out.println("You have logged in successfully. " + name);
        Scanner scanner = new Scanner(System.in);
        System.out.println("1- View the information of the patient\n");
        System.out.println("2- View the medical information of the patient\n");
        patientChoice = scanner.nextInt();
        if (patientChoice == 1) 
        {
        	viewPatientInformation(name);
        } 
        else if (patientChoice == 2) 
        {
            medicalRecord(name);
        } 
        else
        {
            System.out.println("Invalid choice");
        }
    }
    //method to view patient information
    private static void viewPatientInformation(String name) 
    {
        BufferedReader bufferReader = null;

        try {
            bufferReader = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\secure coding\\patient.txt"));
            for (String line;(line = bufferReader.readLine()) != null;)
            {
                String[] columns = line.split("@");
                if (columns.length >= 5 && name.equals(columns[0])) //modified
                {
                    String patientName = columns[0];
                    int phoneNumber = Integer.parseInt(columns[2]);
                    String gender = columns[3];
                    int age = Integer.parseInt(columns[4]);
                    System.out.println("Patient Information for " +name);
                    System.out.println("Age of the Patient " + age);
                    System.out.println("Name of the Patient: " + patientName);
                    System.out.println("Gender of the Patient: " + gender);
                    System.out.println("Phone Number of the Patient: " + phoneNumber);
                    break;//leave the loop after locating the patient information
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error: reading the file");
        } 
        finally
        {
            try 
            {
                if (bufferReader != null) 
                {
                    bufferReader.close();
                }
            } catch (IOException e) {
                System.err.println("Error: closing");
            }
        }
    }
    //method to view the doctor information
    private static void viewDoctorInformation(String name) 
    {
        BufferedReader doctor = null;
        try {
            doctor = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\secure coding\\Doctor.txt"));
            
            for (String line;(line = doctor.readLine()) != null;) {
                String[] columns = line.split("@");
                if (columns.length >= 5 && name.equals(columns[0])) //modified
                {
                    String dName = columns[0];
                    int phoneNumber = Integer.parseInt(columns[2]);
                    String gender = columns[3];
                    int age = Integer.parseInt(columns[4]);
                    System.out.println("Doctor information for "+name);
                    System.out.println("Name of the Doctor: " + dName);
                    System.out.println("Age of the Doctor: " + age);
                    System.out.println("Gender of the Doctor: " + gender);
                    System.out.println("Phone Number of the Doctor: " + phoneNumber);
                    break; //leave the loop after finding the doctor information
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error: reading the file");
        } 
        finally 
        {
            try {
                if (doctor != null)
                {
                    doctor.close();
                }
            } 
            catch (IOException e) 
            {
                System.err.println("Error closing");
            }
        }
    }
    //method to view the medical records
    private static void medicalRecord(String name) 
    {
        BufferedReader bufferReaderMed = null;
        try {
            bufferReaderMed = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\secure coding\\medicalinformation.txt"));
            String line;
            for (line = bufferReaderMed.readLine(); line != null; line = bufferReaderMed.readLine()) 
            {
                String[] columns = line.split("@");
                if (columns.length >= 3 && name.equals(columns[1])) 
                {
                    String situation = columns[2];
                    String treatment = columns[3];
                    System.out.println("Medical Record for the Patient: " +name);
                    System.out.println("Medical Situation for the Patient: " + situation);
                    System.out.println("Medical Treatment for the Patient: " + treatment);
                    break; //leave the loop after finding the medical record
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error: reading the file");
        } 
        finally
        {
            try 
            {
                if (bufferReaderMed != null) 
                {
                    bufferReaderMed.close();
                }
            }
            catch (IOException e) 
            {
                System.out.println("Error closing");
            }
        }
    }
    //method for the doctor-related actions
    private static void Doctor(String name) 
    {
        int doctorChoice;
        System.out.println("You have logged in successfully " + name);
        Scanner doctor = new Scanner(System.in);
        System.out.println("1- View doctor information \n");
        System.out.println("2- Enter the medical details of a patient\n");
        doctorChoice = doctor.nextInt();
        if (doctorChoice == 1) 
        {
            viewDoctorInformation(name);
        } 
        else if (doctorChoice == 2)
        {
            medInfo(name);
        } 
        else 
        {
            System.out.println("Invalid attempt, please try again");
        }
    }
    //method to enter the medical information by the doctor
    private static void medInfo(String doctorName) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the patient name: ");
        String pName = scanner.nextLine();

        System.out.println("Enter the patient medical situation: ");
        String medicalSituation = scanner.nextLine();

        System.out.println("Enter the patient medical treatment: ");
        String medicalTreatment = scanner.nextLine();

        writeThemedicalInfo("C:\\Users\\User\\Desktop\\secure coding\\medicalinformation.txt", doctorName, pName, medicalSituation, medicalTreatment);
    }
    //method to write the medical information to a file
    private static void writeThemedicalInfo(String fileName, String dcotorName, String patientName, String situation, String treatment)     
    {
        try (BufferedWriter bufferWriterMedical = new BufferedWriter(new FileWriter(fileName, true))) 
        {
        	bufferWriterMedical.write(dcotorName + "@" + patientName + "@" + situation + "@" + treatment);
        	bufferWriterMedical.newLine();
        } catch (IOException e) 
        {
            System.out.println("Error writing the medical information" + e.getMessage());
        }
    }
}