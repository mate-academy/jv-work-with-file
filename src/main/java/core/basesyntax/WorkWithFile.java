package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFileName = readDataFromFile(fromFileName);
        int sumOfSupplies = 0;
        int sumOfBuying = 0;
        for (String row : dataFromFileName) {
            String[] operationData = row.split(",");
            if (operationData[OPERATION_TYPE_INDEX].equals("supply")) {
                sumOfSupplies += Integer.parseInt(operationData[AMOUNT_INDEX]);
            } else {
                sumOfBuying += Integer.parseInt(operationData[AMOUNT_INDEX]);
            }
        }
        int result = sumOfSupplies - sumOfBuying;
        String report = generateReport(sumOfSupplies, sumOfBuying, result);
        writeReportToFile(toFileName, report);
    }

    private String[] readDataFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName);
        }
    }

    private String generateReport(int sumOfSupplies, int sumOfBuying, int result) {
        return String.format("supply,%d%nbuy,%d%nresult,%d", sumOfSupplies, sumOfBuying, result);
    }

    private void writeReportToFile(String fileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }
}
