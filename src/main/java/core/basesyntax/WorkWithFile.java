package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DIVIDED_CHAR = ",";
    private static final String[] operations = {"supply","buy"};
    private static final int OPERATION_INDEX = 0;
    private static final int AMMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String result = readFromFile(fromFileName);
        String report = getReportFromFile(result);
        writeToFile(toFileName,report);
    }

    private void writeToFile(String toFileName, String dataToWrite) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(dataToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName,e);
        }
    }

    private String readFromFile(String fromFileName) {
        StringBuilder readBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                readBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            return readBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("File can't read data from file " + fromFileName, e);
        }
    }

    private String getReportFromFile(String data) {
        StringBuilder totalReportBuilder = new StringBuilder();
        int totalReport = 0;
        for (String operation:operations) {
            int totalOperation = getTotalOperation(0,operation,data);
            totalReportBuilder.append(operation).append(",").append(totalOperation);
            totalReportBuilder.append(System.lineSeparator());
            totalReport = totalOperation - totalReport;
        }
        totalReportBuilder.append("result").append(",").append(- totalReport);
        return totalReportBuilder.toString();
    }

    private int getTotalOperation(int ammount, String nameOperation, String data) {
        String[] allOperation = data.split(System.lineSeparator());
        for (String name:allOperation) {
            String[] splittedName = name.split(DIVIDED_CHAR);
            if (splittedName[OPERATION_INDEX].equals(nameOperation)) {
                ammount += Integer.parseInt(splittedName[AMMOUNT_INDEX]);
            }
        }
        return ammount;
    }
}
