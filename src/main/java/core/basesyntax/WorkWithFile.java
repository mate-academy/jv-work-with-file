package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = getReport(data);
        writerToFile(report, toFileName);
    }

    private String readFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String elementsInFile = reader.readLine();
            while (elementsInFile != null) {
                stringBuilder.append(elementsInFile).append(System.lineSeparator());
                elementsInFile = reader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fileName, e);
        }
    }

    private String getReport(String data) {
        int supply = 0;
        int buy = 0;
        String[] operationData = data.split(System.lineSeparator());
        for (String str : operationData) {
            String[] values = str.split("\\W+");
            if (values[OPERATION_INDEX].equals(SUPPLY)) {
                supply += Integer.parseInt(values[AMOUNT_INDEX]);
            } else if (values[OPERATION_INDEX].equals(BUY)) {
                buy += Integer.parseInt(values[AMOUNT_INDEX]);
            }
        }
        int result = supply - buy;
        StringBuilder report = new StringBuilder("supply,")
                .append(supply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buy)
                .append(System.lineSeparator())
                .append("result,")
                .append(result);
        return report.toString();
    }

    private void writerToFile(String result, String toFileName) {
        try (FileWriter writer = new FileWriter(toFileName)) {
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + toFileName, e);
        }
    }
}
