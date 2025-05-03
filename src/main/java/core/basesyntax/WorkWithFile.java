package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE = 0;
    private static final int AMOUNT = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA = ",";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String createdReport = createReport(fromFileName);
        writeToFile(toFileName, createdReport);
    }

    private void writeToFile(String toFileName, String data) {
        try (FileWriter writer = new FileWriter(toFileName)) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write into file: " + toFileName + e);
        }
    }

    private String readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String lineFromFile = bufferedReader.readLine();

            while (lineFromFile != null) {
                stringBuilder.append(lineFromFile).append(System.lineSeparator());
                lineFromFile = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read the data from file: " + fromFileName + e);
        }
    }

    private String createReport(String fromFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;
        String[] readData = readFromFile(fromFileName).split(System.lineSeparator());

        for (String line : readData) {
            String[] splitResult = line.split(COMMA);
            if (splitResult[OPERATION_TYPE].equals(SUPPLY)) {
                supplyTotal += Integer.parseInt(splitResult[AMOUNT]);
            } else {
                buyTotal += Integer.parseInt(splitResult[AMOUNT]);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY + COMMA).append(supplyTotal).append(System.lineSeparator());
        stringBuilder.append(BUY + COMMA).append(buyTotal).append(System.lineSeparator());
        stringBuilder.append(RESULT + COMMA).append(supplyTotal - buyTotal);

        return stringBuilder.toString();
    }
}
