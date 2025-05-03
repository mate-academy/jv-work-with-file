package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataArray = readFromFile(fromFileName);
        String report = generateReport(dataArray);
        writeToFile(toFileName, report);
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String data = reader.readLine();
            while (data != null) {
                builder.append(data).append(System.lineSeparator());
                data = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Couldn't find the file", e);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read from file", e);
        }
        return builder.toString().split(System.lineSeparator());
    }

    private String generateReport(String[] dataArray) {
        StringBuilder builder = new StringBuilder();
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String data : dataArray) {
            String[] dataPerElement = data.split(",");
            if (dataPerElement[OPERATION_INDEX].equals(SUPPLY)) {
                supplyAmount += Integer.parseInt(dataPerElement[AMOUNT_INDEX]);
            }
            if (dataPerElement[OPERATION_INDEX].equals(BUY)) {
                buyAmount += Integer.parseInt(dataPerElement[AMOUNT_INDEX]);
            }
        }
        int result = supplyAmount - buyAmount;
        return builder.append(supplyAmount)
                .append(System.lineSeparator()).append(buyAmount)
                .append(System.lineSeparator()).append(result).toString();
    }

    private void writeToFile(String toFileName, String report) {
        String[] resultArray = report.split(System.lineSeparator());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + resultArray[SUPPLY_INDEX] + System.lineSeparator());
            writer.write("buy," + resultArray[BUY_INDEX] + System.lineSeparator());
            writer.write("result," + resultArray[RESULT_INDEX]);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't write data to file", e);
        }
    }
}
