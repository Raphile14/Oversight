package oversight.engine.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Raphael Dalangin
 */
public class FileUtils {
    
    // Build string path of file
    public static String loadAsString(String path) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader( new InputStreamReader(FileUtils.class.getResourceAsStream(path)))) {
            String line = "";            
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (Exception e) {
            System.err.println("Could'nt find file at " + path);
        }
        return result.toString();
    }    
}

