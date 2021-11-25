package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int DEFAULT_ZERO = 0;
    private static final int DEFAULT_ONE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] fileData = readFileData(fromFileName);
        String report = createReport(fileData);
        writeToFile(report, toFileName);
    }

    private String[] readFileData(String fromFileName) {
        StringBuilder fileLines = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String fromFile = reader.readLine();
            while (fromFile != null) {
                fileLines.append(fromFile).append(" ");
                fromFile = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Data information can not be received.", e);
        }
        return fileLines.toString().split(" ");
    }

    private void writeToFile(String returnData, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(returnData);
        } catch (IOException e) {
            throw new RuntimeException("Data information can not be written" + fileName, e);
        }
    }

    private String createReport(String[] data) {
        int supplied = 0;
        int bought = 0;
        String[] stringArray;
        for (String arrayStrings : data) {
            stringArray = arrayStrings.split(",");
            if (stringArray[DEFAULT_ZERO].equals("supply")) {
                supplied += Integer.parseInt(stringArray[DEFAULT_ONE]);
            } else if (stringArray[DEFAULT_ZERO].equals("buy")) {
                bought += Integer.parseInt(stringArray[DEFAULT_ONE]);
            }
        }
        int finalStatistics = supplied - bought;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply").append(",").append(supplied).append(System.lineSeparator())
                .append("buy").append(",").append(bought).append(System.lineSeparator())
                .append("result,").append(finalStatistics).append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
