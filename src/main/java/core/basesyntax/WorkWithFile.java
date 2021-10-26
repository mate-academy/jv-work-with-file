package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] fileData = readFileData(fromFileName);
        String report = createReport(fileData);
        writeToFile(report, toFileName);
    }

    private String [] readFileData(String fromFileName) {
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

    private String createReport(String[] profitCounter) {
        int supplied = 0;
        int bought = 0;
        String[] stringArray;
        for (String arrayStrings : profitCounter) {
            stringArray = arrayStrings.split(",");
            if (stringArray[0].equals("supply")) {
                supplied += Integer.parseInt(stringArray[1]);
            } else if (stringArray[0].equals("buy")) {
                bought += Integer.parseInt(stringArray[1]);
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
