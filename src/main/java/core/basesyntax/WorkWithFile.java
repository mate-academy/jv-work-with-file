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
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private int supplySum = 0;
    private int buySum = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String readedString = readFile(fromFileName);
        calculateStatistics(readedString);
        String report = createReport();
        writeFile(toFileName, report);
    }

    private String createReport() {
        StringBuilder builder = new StringBuilder();
        return builder.append(SUPPLY)
                .append(COMA)
                .append(supplySum)
                .append(LINE_SEPARATOR)
                .append(BUY)
                .append(COMA)
                .append(buySum)
                .append(LINE_SEPARATOR)
                .append(RESULT)
                .append(COMA)
                .append(supplySum - buySum)
                .append(LINE_SEPARATOR).toString();
    }

    private String readFile(String fromFileName) {
        File file = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder builder = new StringBuilder();
            String currentString = bufferedReader.readLine();
            while (currentString != null) {
                builder.append(currentString).append(LINE_SEPARATOR);
                currentString = bufferedReader.readLine();
            }
            return builder.toString();
        } catch (IOException ex) {
            throw new RuntimeException("Can't read from " + file.getName(), ex);
        }
    }

    private void calculateStatistics(String readedString) {
        String[] splited = readedString.split(LINE_SEPARATOR);
        for (int i = 0; i < splited.length; i++) {
            String[] comaSplited = splited[i].split(COMA);
            for (int j = 0; j < comaSplited.length; j++) {
                if (comaSplited[j].equals(SUPPLY)) {
                    supplySum += Integer.parseInt(comaSplited[j + 1]);
                } else if (comaSplited[j].equals(BUY)) {
                    buySum += Integer.parseInt(comaSplited[j + 1]);
                }
            }
        }
    }

    private void writeFile(String toFileName, String report) {
        supplySum = 0;
        buySum = 0;
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException ex) {
            throw new RuntimeException("Can't write in this " + file.getName(), ex);
        }
    }
}
