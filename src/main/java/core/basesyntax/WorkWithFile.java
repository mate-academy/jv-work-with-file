package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE = 0;
    private static final int OPERATION_TYPE_RESULT = 2;
    private static final int OPERATION_TYPE_BUY = 1;
    private static final int OPERATION_TYPE_SUPPLY = 0;
    private static final int AMOUNT = 1;
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) throws IOException {
        String[] resultFromRead = readFromFile(fromFileName);
        String data = infoGathering(resultFromRead);
        writeToFile(toFileName, data);
    }

    public String[] readFromFile(String fromFileName)
            throws IOException {
        int supplyCounter = 0;
        int buyCounter = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] dataFromFile = line.split(COMMA);
                int amount = Integer.parseInt(dataFromFile[AMOUNT]);

                if (dataFromFile[OPERATION_TYPE].equals(OPERATION_SUPPLY)) {
                    supplyCounter += amount;
                } else {
                    buyCounter += amount;
                }

            }
        } catch (IOException e) {
            throw new IOException("File can not be open", e);
        }

        return new String[]{String.valueOf(supplyCounter), String.valueOf(buyCounter),
                String.valueOf(supplyCounter - buyCounter)};
    }

    public void writeToFile(String toFileName, String data) throws IOException {
        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            fileWriter.write(data);
        } catch (IOException e) {
            throw new IOException("can not write to file", e);
        }
    }

    public String infoGathering(String[] result) {
        return new StringBuilder(OPERATION_SUPPLY).append(COMMA)
                .append(result[OPERATION_TYPE_SUPPLY]).append(System.lineSeparator())
                .append(OPERATION_BUY).append(COMMA).append(result[OPERATION_TYPE_BUY])
                .append(System.lineSeparator()).append(OPERATION_RESULT).append(COMMA)
                .append(result[OPERATION_TYPE_RESULT]).append(System.lineSeparator()).toString();
    }
}
