package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DATA_SEPARATOR = ",";
    private int buyReport = 0;
    private int supplyReport = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String[][] dataMatrix = readFromFile(fromFileName);
        String report = createReport(dataMatrix);
        writeReportToFile(toFileName, report);
    }

    // read from file method
    private String[][] readFromFile(String fromFileName) {
        String line;
        int rowsCount = 0;
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = br.readLine()) != null) {
                fileContent.append(line).append(System.lineSeparator());
                rowsCount++;
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading from file", e);
        }
        String[] dataArray = fileContent.toString().split(System.lineSeparator());
        String[][] dataMatrix = new String[rowsCount][2];
        rowsCount = 0;
        for (String dataArrayItem : dataArray) {
            dataMatrix[rowsCount] = dataArrayItem.split(DATA_SEPARATOR);
            rowsCount++;
        }
        return dataMatrix;
    }

    // create report method
    private String createReport(String[][] dataMatrix) {
        StringBuilder report = new StringBuilder();
        for (String[] dataMatrixItem : dataMatrix) {
            if (dataMatrixItem[0].equals("buy")) {
                buyReport = buyReport + Integer.parseInt(dataMatrixItem[1]);
            } else if (dataMatrixItem[0].equals("supply")) {
                supplyReport = supplyReport + Integer.parseInt(dataMatrixItem[1]);
            }
        }
        return report.append("supply,").append(supplyReport).append(System.lineSeparator())
                .append("buy,").append(buyReport).append(System.lineSeparator())
                .append("result,").append(supplyReport - buyReport)
                .toString();
    }

    // write to file method
    private void writeReportToFile(String fileName, String report) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error reading from file", e);
        }
    }
}
