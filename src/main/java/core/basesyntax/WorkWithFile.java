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
        String newReport = getReport(fromFileName);
        writerToFile(newReport, toFileName);
    }

    public int readFile(String fileName, String values) {
        int result = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String elementsInFile = reader.readLine();
            while (elementsInFile != null) {
                String[] data = elementsInFile.split("\\W+");
                if (values.equals(data[OPERATION_INDEX])) {
                    result += Integer.parseInt(data[AMOUNT_INDEX]);
                }
                elementsInFile = reader.readLine();
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
    }

    public String getReport(String fromFileName) {
        int supply = readFile(fromFileName, SUPPLY);
        int buy = readFile(fromFileName, BUY);
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

    public void writerToFile(String result, String toFileName) {
        try (FileWriter writer = new FileWriter(toFileName)) {
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
