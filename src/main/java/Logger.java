import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String LOGGER_FILENAME = System.getProperty("user.dir") + "/src/main/resources/log.txt";
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(String message) {
        String getTime = dateFormat.format(LocalDateTime.now());
        String fullMessage = getTime + ": " + message;
        System.out.println(fullMessage);

        try (PrintWriter out = new PrintWriter(new FileWriter(LOGGER_FILENAME, true))) {
            out.println(fullMessage);
        } catch (IOException e) {
            System.err.println("Could not write to log file: " + e.getMessage());
        }
    }

    public static void logReset() {
        try (FileWriter fw = new FileWriter(LOGGER_FILENAME, false)) {
            System.out.println("Successfully reset the log file");
        } catch (IOException e) {
            System.err.println("Could not reset log file: " + e.getMessage());
        }

    }
    }

