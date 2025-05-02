package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMA = ",";
    private static final String WHITESPACE_COMA_SPLIT = "[,\\s]";

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFileContent(fromFileName);
        String statisticReport = calculateStatistic(fileContent);
        writeToFile(toFileName, statisticReport);
    }

    private String calculateStatistic(String fileContent) {
        String[] arrayFromFile = fileContent.split(WHITESPACE_COMA_SPLIT);
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < arrayFromFile.length; i = i + 2) {
            if (arrayFromFile[i].equals(SUPPLY)) {
                supply += Integer.parseInt(arrayFromFile[i + 1]);
            }
            if (arrayFromFile[i].equals(BUY)) {
                buy += Integer.parseInt(arrayFromFile[i + 1]);
            }
        }
        int result = supply - buy;

        return SUPPLY + COMA + supply + System.lineSeparator()
                + BUY + COMA + buy + System.lineSeparator()
                + RESULT + COMA + result;
    }

    private String readFileContent(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file: " + fileName + e);
        }
        return stringBuilder.toString();
    }

    private void writeToFile(String fileName, String data) {
        File file = new File(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(data);
        } catch (IOException e) {
            System.out.println("Can't write to file " + fileName + e);
        }
    }
}
