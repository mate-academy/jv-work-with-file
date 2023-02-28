package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File readFromFile = new File(fromFileName);
        String[] fileArray = convertFileToStringArray(readFromFile);
    }
    private static String[] convertFileToStringArray(File file) {
        final String delimiter = "$Delimiter$";

        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder string = new StringBuilder();
            while (reader.readLine() != null) {
                string.append(reader.readLine()).append(delimiter);
            }
            String[] resultArray = string.toString().split(delimiter);
            return resultArray;
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong.", e);
        }
    }
}
