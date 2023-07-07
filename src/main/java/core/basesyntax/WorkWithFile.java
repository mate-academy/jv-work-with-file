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
        readFile(fromFileName);
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

    private void readFile(String fromFileName) {
        supplySum = 0;
        buySum = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String currentString = bufferedReader.readLine();
            while (currentString != null) {
                String[] splited = currentString.split(COMA);
                for (int i = 0; i < splited.length; i++) {
                    if (splited[i].equals(SUPPLY)) {
                        supplySum += Integer.parseInt(splited[i + 1]);
                    } else if (splited[i].equals(BUY)) {
                        buySum += Integer.parseInt(splited[i + 1]);
                    }
                }
                currentString = bufferedReader.readLine();
            }
        } catch (IOException ex) {
            throw new RuntimeException("Can't read from file" + fromFileName, ex);
        }
    }

    private void writeFile(String toFileName, String report) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException ex) {
            throw new RuntimeException("Can't write in this file!" + file, ex);
        }
    }
}
