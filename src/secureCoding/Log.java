package secureCoding;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
public class Log {
	//logger for logging messages
    final static Logger LOGGER = Logger.getLogger("MyLog");
    static
    {
        try 
        {
        	//create a file handler to write a log message to a file
            FileHandler filehhandler;
            filehhandler= new FileHandler("C:\\Users\\User\\Desktop\\secure coding\\log.log",true);
            LOGGER.addHandler(filehhandler); //add the file handler to the logger
            SimpleFormatter formatter= new SimpleFormatter(); //create a simple formatter to format log messages
            filehhandler.setFormatter(formatter); //set the formatter for the file handler
            LOGGER.setUseParentHandlers(false); //disable the parent handlers to avoid duplicates log inputs

        } 
        catch (IOException e) 
        {
        	//handling the case where there is an issue with the file handler
            System.out.println("The logger here has a problem");
        } 
        catch (SecurityException e) 
        {
        	//handling the case where there is a security issue with the file handler
           System.out.println("The logger here has a problem");
        }
        
    }
    //to log a message at info level
    public static void ToLog(String message)
    {
        LOGGER.log(Level.INFO, message);   
    }
    //to log a message associated exception at warning level
    public static void ToLog(String message, Exception e)
    {
        LOGGER.log(Level.WARNING, message, e);
        
    }
}

