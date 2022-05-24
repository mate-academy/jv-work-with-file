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
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String statistic = readFromFile(fromFileName);
        String[] statisticToArray = statistic.split(SEPARATOR);
        int resultSupply = getResultSupply(statisticToArray);
        int resultBuy = getResultBuy(statisticToArray);
        StringBuilder statisticFromFile = new StringBuilder();
        statisticFromFile.append(SUPPLY).append(SEPARATOR).append(resultSupply)
                .append(System.lineSeparator()).append(BUY)
                .append(SEPARATOR).append(resultBuy)
                .append(LINE_SEPARATOR)
                .append(RESULT).append(SEPARATOR).append(resultSupply - resultBuy);
        writeFile(statisticFromFile.toString(), toFileName);
    }

    private String readFromFile(String fromFileName) {
        File fileFrom = new File(fromFileName);
        StringBuilder dataFromFile = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileFrom))) {
            String line = reader.readLine();
            while (line != null) {
                dataFromFile.append(line).append(SEPARATOR);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
        return dataFromFile.toString();
    }

    private void writeFile(String statisticFromFile, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(statisticFromFile);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }

    private int getResultBuy(String [] fileToArray) {
        int resultBuy = 0;
        for (int i = 0; i < fileToArray.length; i++) {
            if (fileToArray[i].equals(BUY)) {
                resultBuy += Integer.parseInt(fileToArray[i + 1]);
            }
        }
        return resultBuy;
    }

    private int getResultSupply(String [] fileToArray) {
        int resultSupply = 0;
        for (int i = 0; i < fileToArray.length; i++) {
            if (fileToArray[i].equals(SUPPLY)) {
                resultSupply += Integer.parseInt(fileToArray[i + 1]);
            }
        }
        return resultSupply;
    }
}
