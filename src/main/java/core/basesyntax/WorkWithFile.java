package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String DATA_SEPARATOR = ", ";
    private static final String SUPPLY_CONSTANT_FOR_RESULT = "supply,";
    private static final String BUY_CONSTANT_FOR_RESULT = "buy,";
    private static final String RESULT_CONSTANT = "result,";
    private String dataFromFile;
    private String report;

    public String getDataFromFile() {
        return dataFromFile;
    }

    public void setDataFromFile(String dataFromFile) {
        this.dataFromFile = dataFromFile;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        creatingReport();
        writeToFile(toFileName);
    }

    private void readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(LINE_SEPARATOR);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file", e);
        }
        dataFromFile = stringBuilder.toString();
    }

    private void creatingReport() {
        String[] dataArray = dataFromFile.split(DATA_SEPARATOR);
        int buyValues = 0;
        int supplyValues = 0;
        for (int i = 0; i < dataArray.length; i++) {
            if (dataArray[i].equals("buy")) {
                buyValues += Integer.parseInt(dataArray[i + 1]);
            }
            if (dataArray[i].equals("supply")) {
                supplyValues += Integer.parseInt(dataArray[i + 1]);
            }
        }
        int result = supplyValues - buyValues;
        StringBuilder reportBuilder = new StringBuilder();
        report = reportBuilder
                .append(SUPPLY_CONSTANT_FOR_RESULT)
                .append(supplyValues)
                .append(System.lineSeparator())
                .append(BUY_CONSTANT_FOR_RESULT)
                .append(buyValues)
                .append(System.lineSeparator())
                .append(RESULT_CONSTANT)
                .append(result)
                .toString();
    }

    private void writeToFile(String toFileName) {
        File file1 = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file1, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }
    }
}
