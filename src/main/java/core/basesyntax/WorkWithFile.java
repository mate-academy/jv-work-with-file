package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPLIT = ",";
    private static final int ACTION = 0;
    private static final int DATA = 1;
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readDataFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readDataFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        String value;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while ((value = bufferedReader.readLine()) != null) {
                stringBuilder.append(value).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.printf("Can't read from file " + fileName);
        }
        return stringBuilder.toString();
    }

    private String createReport(String dataFromFile) {
        StringBuilder stringBuilder = new StringBuilder();
        int buy = 0;
        int supply = 0;
        String[] lines = dataFromFile.split(System.lineSeparator());
        for (String string : lines) {
            String[] split = string.split(SPLIT);
            if ((split[ACTION]).equals(BUY)) {
                buy += Integer.parseInt(split[DATA]);
            } else {
                supply += Integer.parseInt(split[DATA]);
            }
        }
        stringBuilder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy);
        return stringBuilder.toString();
    }

    private void writeToFile(String report, String fileName) {
        String[] lines = report.split(System.lineSeparator());
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            for (String string : lines) {
                bufferedWriter.write(string);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.printf("Can't write to file " + fileName);
        }
    }
}
