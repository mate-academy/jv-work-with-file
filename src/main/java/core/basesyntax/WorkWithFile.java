package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        createFileWithGroupedData(toFileName, createGroupedData(extractDataFromFile(fromFileName)));
    }

    private static String extractDataFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String dataFromFile = reader.readLine();
            while (dataFromFile != null) {
                builder.append(dataFromFile).append(" ");
                dataFromFile = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read file " + fileName);
        }
        return builder.toString();
    }

    private static String[] createGroupedData(String dataInLine) {
        String[] values = dataInLine.split(" ");
        int sumOfSupply = 0;
        int sumOfBuy = 0;
        for (String value : values) {
            String[] splitValue = value.split(",");
            if (splitValue[0].equals(SUPPLY)) {
                sumOfSupply += Integer.parseInt(splitValue[1]);
            }
            if (splitValue[0].equals(BUY)) {
                sumOfBuy += Integer.parseInt(splitValue[1]);
            }
        }
        String[] groupedData = new String[3];
        groupedData[0] = SUPPLY + "," + sumOfSupply;
        groupedData[1] = BUY + "," + sumOfBuy;
        groupedData[2] = RESULT + "," + (sumOfSupply - sumOfBuy);
        return groupedData;
    }

    private static void createFileWithGroupedData(String nameOfReportFile, String[] data) {
        File file = new File(nameOfReportFile);
        for (String value : data) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(value);
                writer.write(System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException("Can not write data to file", e);
            }
        }
    }
}
