package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DIVIDE_CHARACTER = ",";
    private static final String FIRST_COLUMN_WORD = "supply";
    private static final String SECOND_COLUMN_WORD = "buy";
    private static final String THIRD_COLUMN_WORD = "result";
    private static final String SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = processData(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder dataFromFile = new StringBuilder();
        File file = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                dataFromFile.append(line).append(DIVIDE_CHARACTER);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return dataFromFile.toString();
    }

    private String processData(String dataFromFile) {
        int supply = 0;
        int buy = 0;
        String[] dataDividedByComma = dataFromFile.split(DIVIDE_CHARACTER);
        for (int i = 0; i < dataDividedByComma.length; i++) {
            switch (dataDividedByComma[i]) {
                case FIRST_COLUMN_WORD:
                    i++;
                    supply += Integer.parseInt(dataDividedByComma[i]);
                    break;
                case SECOND_COLUMN_WORD:
                    i++;
                    buy += Integer.parseInt(dataDividedByComma[i]);
                    break;
                default:
                    break;
            }
        }
        int result = supply - buy;
        return FIRST_COLUMN_WORD + DIVIDE_CHARACTER + supply + SEPARATOR
                + SECOND_COLUMN_WORD + DIVIDE_CHARACTER + buy + SEPARATOR
                + THIRD_COLUMN_WORD + DIVIDE_CHARACTER + result;
    }

    private void writeToFile(String report, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
