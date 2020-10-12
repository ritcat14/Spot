package SpotJava.core.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import SpotJava.JavaMain;

public class Files {

    private Files() {}

    public static String[] extractDataFromResource(String resource) {

        String[] results = null;
        List<String> arrayResults = new ArrayList<>();
        BufferedReader reader = null;
        InputStreamReader streamReader = null;

        try {
            streamReader = new InputStreamReader(getFileFromResourceAsStream(resource));
            reader = new BufferedReader(streamReader);

            String line;
            while ((line = reader.readLine()) != null) {
                arrayResults.add(line);
            }
            
            if (streamReader != null) streamReader.close();
            if (reader != null) reader.close();
        } catch (Exception e) {
            System.err.println("Could not load resource:" + resource);
            e.printStackTrace();
        }
        return arrayResults.toArray(new String[0]);
    }

    private static InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = JavaMain.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }
    
}