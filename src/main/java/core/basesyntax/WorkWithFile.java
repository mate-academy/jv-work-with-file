package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPECIAL_DELIMITER = "!Delimiter@";
    private static final String NON_DIGIT = "\\D";
    private static final String COMMA_DIGIT = ",%d";
    private static final int FIRST_POSITION = 0;
    private static final int SECOND_POSITION = 1;
    private static final String EMPTY_STRING = "";
    private static final char COMMA = ',';

    public void getStatistic(String fromFileName, String toFileName) {
        File readFromFile = new File(fromFileName);
        File writeToFile = new File(toFileName);
        String[] fileArray = simplificateArray(convertFileToStringArray(readFromFile));
        if (!fileArray[FIRST_POSITION].contains("supply")) {
            String replaceString = fileArray[SECOND_POSITION];
            fileArray[SECOND_POSITION] = fileArray[FIRST_POSITION];
            fileArray[FIRST_POSITION] = replaceString;
        }
        int firstNumber = Integer.parseInt(fileArray[FIRST_POSITION]
                .replaceAll(NON_DIGIT, EMPTY_STRING));
        int secondNumber = Integer.parseInt(fileArray[SECOND_POSITION]
                .replaceAll(NON_DIGIT, EMPTY_STRING));
        String attachString = String.format("result,%d", firstNumber - secondNumber);
        writeStringToFile(fileArray, writeToFile);
    }

    private void writeStringToFile(String[] stringFrom, File fileTo) {
        if (!stringFrom[FIRST_POSITION].contains("supply")) {
            String replaceString = stringFrom[SECOND_POSITION];
            stringFrom[SECOND_POSITION] = stringFrom[FIRST_POSITION];
            stringFrom[FIRST_POSITION] = replaceString;
        }
        int firstNumber = Integer.parseInt(stringFrom[FIRST_POSITION]
                .replaceAll(NON_DIGIT, EMPTY_STRING));
        int secondNumber = Integer.parseInt(stringFrom[SECOND_POSITION]
                .replaceAll(NON_DIGIT, EMPTY_STRING));
        try (FileWriter fileWriter = new FileWriter(fileTo)) {
            fileWriter.write("");
        } catch (IOException e) {
            throw new RuntimeException("Can't clear a file content", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTo, true))) {
            for (String string:stringFrom) {
                bufferedWriter.write(string);
                bufferedWriter.write(System.lineSeparator());
            }
            bufferedWriter.write(String.format("result,%d", firstNumber - secondNumber));

        } catch (IOException e) {
            throw new RuntimeException("Can't write sting to file ", e);
        }
    }

    private String[] convertFileToStringArray(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder string = new StringBuilder();
            String nextLine = reader.readLine();
            while (nextLine != null) {
                string.append(nextLine).append(SPECIAL_DELIMITER);
                nextLine = reader.readLine();
            }
            String[] resultArray = string.toString().split(SPECIAL_DELIMITER);
            return resultArray;
        } catch (IOException e) {
            throw new RuntimeException("Can't rewrite file content to string array ", e);
        }
    }

    private String[] simplificateArray(String[] array) {
        StringBuilder checkerString = new StringBuilder();
        for (String str:array) {
            int commaIndex = str.indexOf(COMMA);
            String stringOption = str.substring(0, commaIndex);
            if (!(checkerString.toString().contains(stringOption))) {
                checkerString.append(stringOption).append(SPECIAL_DELIMITER);
            }
        }
        String[] reportArray = checkerString.toString().split(SPECIAL_DELIMITER);
        for (int i = 0; i < reportArray.length; ++i) {
            int sumNumber = 0;
            for (int k = 0; k < array.length; ++k) {
                if (array[k].contains(reportArray[i])) {
                    int commaIndex = array[k].indexOf(COMMA) + 1;
                    sumNumber += Integer.parseInt(array[k].substring(commaIndex));
                }
            }

            reportArray[i] = reportArray[i].concat(String.format(COMMA_DIGIT, sumNumber));
        }
        return reportArray;
    }
}
