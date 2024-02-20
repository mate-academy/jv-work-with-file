package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String WORD_SEPARATOR = " ";
    private static final String BY_WORD_BOUNDARIES = "\\W+";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFile(fromFileName);
        String report = createReport(fileContent);
        writeToFile(toFileName, report);
    }

    private String createReport(String fileContent) {
        String[] dataArray = fileContent.split(BY_WORD_BOUNDARIES);
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < dataArray.length; i += 2) {
            if (dataArray[i].equals(SUPPLY)) {
                supply += Integer.parseInt(dataArray[i + 1]);
            } else if (dataArray[i].equals(BUY)) {
                buy += Integer.parseInt(dataArray[i + 1]);
            }
        }
        return SUPPLY + COMMA + supply + System.lineSeparator()
                + BUY + COMMA + buy + System.lineSeparator()
                + RESULT + COMMA + (supply - buy);
    }

    private void writeToFile(String fileName, String reportString) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, false))) {
            bufferedWriter.write(reportString);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + fileName, e);
        }
    }

    private String readFile(String fileName) {
        StringBuilder fileDataString = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String value = reader.readLine();
            while (value != null) {
                fileDataString.append(value).append(WORD_SEPARATOR);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fileName, e);
        }
        return fileDataString.toString();
    }
}
