package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION_TYPE = "supply";
    private static final String BUY_OPERATION_TYPE = "buy";
    private static final String RESULT = "result";
    private static final int FIELD_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeFile(toFileName, report);
    }

    private String readFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String lineFromFile = bufferedReader.readLine();
            while (lineFromFile != null) {
                stringBuilder.append(lineFromFile).append(System.lineSeparator());
                lineFromFile = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return stringBuilder.toString();
    }

    private String createReport(String dataFromFile) {
        int totalAmountOfSupplyOperation = 0;
        int totalAmountOfBuyOperation = 0;
        String[] linesFromFile = dataFromFile.split(System.lineSeparator());
        for (String lineFromFile : linesFromFile) {
            String[] dataFieldElements = lineFromFile.split(",");
            int valueOfField = Integer.parseInt(dataFieldElements[VALUE_INDEX]);
            switch (dataFieldElements[FIELD_INDEX]) {
                case SUPPLY_OPERATION_TYPE:
                    totalAmountOfSupplyOperation += valueOfField;
                    break;
                case BUY_OPERATION_TYPE:
                    totalAmountOfBuyOperation += valueOfField;
                    break;
                default:
                    break;
            }
        }
        int result = totalAmountOfSupplyOperation - totalAmountOfBuyOperation;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY_OPERATION_TYPE).append(",")
                .append(totalAmountOfSupplyOperation).append(System.lineSeparator())
                .append(BUY_OPERATION_TYPE).append(",").append(totalAmountOfBuyOperation)
                .append(System.lineSeparator()).append(RESULT).append(",")
                .append(result);
        return stringBuilder.toString();
    }

    private void writeFile(String fileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }
}
