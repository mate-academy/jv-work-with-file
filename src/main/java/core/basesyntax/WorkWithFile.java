package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private static final String SPECIAL_DELIMITER = "!Delimiter@";
    public void getStatistic(String fromFileName, String toFileName) {
        File readFromFile = new File(fromFileName);
        File writeToFile = new File(toFileName);
        String[] fileArray = simplificationArray(convertFileToStringArray(readFromFile));
        writeStringToFile(fileArray, writeToFile);
    }
    private void writeStringToFile (String[] stringFrom, File fileTo){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTo, true))) {
            for (String string:stringFrom) {
                bufferedWriter.write(string);
                bufferedWriter.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write sting to file ", e);
        }
    }
    private String[] convertFileToStringArray(File file) {
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder string = new StringBuilder();
            while (reader.readLine() != null) {
                string.append(reader.readLine()).append(SPECIAL_DELIMITER);
            }
            String[] resultArray = string.toString().split(SPECIAL_DELIMITER);
            return resultArray;
        } catch (IOException e) {
            throw new RuntimeException("Can't rewrite file content to string array ", e);
        }
    }
    private String[] simplificationArray(String[] array) {
        StringBuilder checkerString = new StringBuilder();
        for (String str:array) {
            if (!checkerString.toString().contains(str)) {
                int commaIndex = str.charAt(',');
                checkerString.append(str.substring(0, commaIndex)).append(SPECIAL_DELIMITER);
            }
        }
        String[] reportArray = checkerString.toString().split(SPECIAL_DELIMITER);
        for (int i = 0; i < reportArray.length; ++i) {
            int sumNumber = 0;
            for (int k = 0; k < array.length; ++k) {
                if (array[k].contains(reportArray[i])) {
                    int commaIndex = array[k].charAt(',') + 1;
                    sumNumber += Integer.parseInt(array[k].substring(commaIndex));
                }
            }
            reportArray[i].concat(String.format(",%d", sumNumber));
        }
        return reportArray;
    }
}
