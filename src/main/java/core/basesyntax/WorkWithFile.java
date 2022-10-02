package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String SPLIT_FOR_DATA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private String[] dataFromFile;
    private int sumOfSupply = 0;
    private int sumOfBuy = 0;
    private StringBuilder report;

    public void getStatistic(String fromFileName, String toFileName) {
        dataFromFile = readFile(fromFileName);
        getData();
        getReport();
        writeInFile(toFileName);
    }

    private void getReport() {
        report = new StringBuilder();
        report.append(SUPPLY).append(SPLIT_FOR_DATA).append(sumOfSupply)
                .append(System.lineSeparator())
                .append(BUY).append(SPLIT_FOR_DATA).append(sumOfBuy).append(System.lineSeparator())
                .append("result").append(SPLIT_FOR_DATA).append(sumOfSupply - sumOfBuy);
    }

    private void getData() {
        for (int i = 0; i < dataFromFile.length; i += 2) {
            if (dataFromFile[i].equals(SUPPLY)) {
                sumOfSupply += getIntValue(dataFromFile[i + 1]);
            } else if (dataFromFile[i].equals(BUY)) {
                sumOfBuy += getIntValue(dataFromFile[i + 1]);
            } else {
                throw new RuntimeException("Wrong data in file");
            }
        }
    }

    private int getIntValue(String value) {
        return Integer.parseInt(value);
    }

    private String[] readFile(String fromFileName) {
        String line = "";
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(SPLIT_FOR_DATA);
            }
        } catch (Exception e) {
            throw new RuntimeException("Can`t read data from file", e);
        }
        return stringBuilder.toString().split(SPLIT_FOR_DATA);
    }

    private void writeInFile(String toFileName) {
        Path fileName = Path.of(toFileName);
        try {
            Files.writeString(fileName, report.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }
    }
}
