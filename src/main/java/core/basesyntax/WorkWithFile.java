package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String FIRST_PARAMETR = "supply";
    private static final String SECOND_PARAMETR = "buy";
    private static final String THIRD_PARAMETR = "result";
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String stringsFromFile = readFromFile(fromFileName);
        String report = createReport(stringsFromFile);
        writeReport(report, toFileName);
    }

    private String readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(';');
                value = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fileName + ".", e);
        }
    }

    private String createReport(String readFromFile) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] splitted = readFromFile.split(";");
        int supplySum = 0;
        int buySum = 0;
        for (String element : splitted) {
            supplySum += element.charAt(0) == FIRST_PARAMETR.charAt(0) ? Integer.parseInt(element
                    .substring(FIRST_PARAMETR.length() + 1)) : 0;
            buySum += element.charAt(0) == SECOND_PARAMETR.charAt(0) ? Integer.parseInt(element
                    .substring(SECOND_PARAMETR.length() + 1)) : 0;
        }
        return stringBuilder.append(FIRST_PARAMETR).append(SEPARATOR).append(supplySum)
                .append(System.lineSeparator())
                .append(SECOND_PARAMETR).append(SEPARATOR).append(buySum)
                .append(System.lineSeparator())
                .append(THIRD_PARAMETR).append(SEPARATOR).append(supplySum - buySum).toString();
    }

    private void writeReport(String report, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + fileName + ".", e);
        }
    }
}
