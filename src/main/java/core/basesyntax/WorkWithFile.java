package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String DATA_SEPARATOR = "\\W+";
    private static final String SUPPLY_CONSTANT_FOR_RESULT = "supply,";
    private static final String BUY_CONSTANT_FOR_RESULT = "buy,";
    private static final String RESULT_CONSTANT = "result,";
    private static final String SPACE = " ";

    public void getStatistic(String fromFileName, String toFileName) {
        String readFile = readFromFile(fromFileName);
        String[] report = creatingReport(readFile);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(LINE_SEPARATOR);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file", e);
        }
        return stringBuilder.toString();
    }

    private String[] creatingReport(String dataFromFile) {
        String[] dataArray = dataFromFile.split(DATA_SEPARATOR);
        int buyValues = 0;
        int supplyValues = 0;
        for (int i = 0; i < dataArray.length; i += 2) {
            if (dataArray[i].equals("buy")) {
                buyValues += Integer.parseInt(dataArray[i + 1]);
            }
            if (dataArray[i].equals("supply")) {
                supplyValues += Integer.parseInt(dataArray[i + 1]);
            }
        }
        int result = supplyValues - buyValues;
        StringBuilder reportBuilder = new StringBuilder();
        return reportBuilder
                .append(SUPPLY_CONSTANT_FOR_RESULT)
                .append(supplyValues)
                .append(LINE_SEPARATOR)
                .append(BUY_CONSTANT_FOR_RESULT)
                .append(buyValues)
                .append(LINE_SEPARATOR)
                .append(RESULT_CONSTANT)
                .append(result)
                .toString().split(SPACE);
    }

    private void writeToFile(String[] creatingReport, String toFileName) {
        File file = new File(toFileName);
        for (String info: creatingReport) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                bufferedWriter.write(info + LINE_SEPARATOR);
            } catch (IOException e) {
                throw new RuntimeException("Can`t write data to file", e);
            }
        }
    }
}
