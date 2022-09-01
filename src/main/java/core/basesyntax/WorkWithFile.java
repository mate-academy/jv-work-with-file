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
    private static final int OPERATION_TYPE = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private String[] readFromFile(String fromFileName) {
        File infoFile = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(infoFile))) {
            String textFromFileName = reader.readLine();
            while (textFromFileName != null) {
                stringBuilder.append(textFromFileName).append(" ");
                textFromFileName = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t find the file", e);
        }
        return stringBuilder.toString().split(" ");
    }

    private String createReport(String[] lineFromFile) {
        int supply = 0;
        int buy = 0;
        for (String line : lineFromFile) {
            String[] splitLine = line.split(",");
            if (splitLine[OPERATION_TYPE].equals(SUPPLY)) {
                supply += Integer.parseInt(splitLine[AMOUNT_INDEX]);
            } else if (splitLine[OPERATION_TYPE].equals(BUY)) {
                buy += Integer.parseInt(splitLine[AMOUNT_INDEX]);
            }
        }
        return reportOfDate(supply, buy);
    }

    private String reportOfDate(int supply, int buy) {
        int result = supply - buy;
        return SUPPLY + "," + supply + System.lineSeparator()
                + BUY + "," + buy + System.lineSeparator()
                + RESULT + "," + result;
    }

    private void writeToFile(String toFileName, String report) {
        File reportFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(reportFile))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + reportFile, e);
        }
    }
}
