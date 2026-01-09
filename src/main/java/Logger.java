import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // logs String message into a log.txt file with the local-time
    public static void log(String message) {
        String getTime = dateFormat.format(LocalDateTime.now());
        String fullMessage = getTime + ": " + message;
        System.out.println(fullMessage);

        try (PrintWriter out = new PrintWriter(new FileWriter("log.txt", true))) {
            out.println(fullMessage);
        } catch (IOException e) {
            System.err.println("Could not write to log file: " + e.getMessage());
        }
    }

    // deletes all messages in the log.txt file
    public static void logReset() {
        try (FileWriter fw = new FileWriter("log.txt", false)) {
            System.out.println("Successfully reset the log file");
        } catch (IOException e) {
            System.err.println("Could not reset log file: " + e.getMessage());
        }

    }
    }

