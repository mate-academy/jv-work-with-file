package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String CSV_FILE_SEPERATOR = ",";
    private static final String OPER_TYPE_SUPPLY = "supply";
    private static final String OPER_TYPE_BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readDataFromFile(fromFileName);

        String report = generateReportFromData(dataFromFile);

        writeDataToFile(report, toFileName);
    }

    private String readDataFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {

            String lineFromFile = bufferedReader.readLine();
            while (lineFromFile != null) {
                stringBuilder.append(lineFromFile).append(System.lineSeparator());

                lineFromFile = bufferedReader.readLine();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File " + fromFileName + " not found!", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }

        return stringBuilder.toString();
    }

    private String generateReportFromData(String data) {
        int groupSupplyCount = 0;
        int groupBuyCount = 0;
        StringBuilder result = new StringBuilder();

        String[] recordsFromData = data.split(System.lineSeparator());
        for (String record : recordsFromData) {
            String[] parseRecord = record.split(CSV_FILE_SEPERATOR);

            String group = parseRecord[0];
            int groupCount = convertStringToNumber(parseRecord[1]);

            if (group.equals(OPER_TYPE_SUPPLY)) {
                groupSupplyCount += groupCount;
            } else if (group.equals(OPER_TYPE_BUY)) {
                groupBuyCount += groupCount;
            }
        }

        result.append(OPER_TYPE_SUPPLY).append(",").append(groupSupplyCount)
                .append(System.lineSeparator())
                .append(OPER_TYPE_BUY).append(",").append(groupBuyCount)
                .append(System.lineSeparator())
                .append("result,").append(groupSupplyCount - groupBuyCount);

        return result.toString();
    }

    private void writeDataToFile(String data, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file: " + toFileName, e);
        }
    }

    private int convertStringToNumber(String parseValue) {
        try {
            return Integer.parseInt(parseValue);
        } catch (NumberFormatException e) {
            throw new RuntimeException("We cann't parse number from the string:" + parseValue);
        }
    }
}
