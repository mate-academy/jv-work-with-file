package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";

    private static final String OPERATION_BUY = "buy";

    private static final String OPERATION_RESULT = "result";

    private static final String COMA_SEPARATOR = ",";

    private static final String TEXT_OF_EXCEPTION = "File not found";

    private static final String REGEX = "\\W";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyAmount = 0;
        int buyAmount = 0;
        StringBuilder builder = new StringBuilder();

        readFromFile(fromFileName, supplyAmount, buyAmount, builder);
        writeStringToFile(toFileName, builder);

    }

    private void writeStringToFile(String toFileName, StringBuilder builder) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException(TEXT_OF_EXCEPTION);
        }
    }

    private void readFromFile(String fromFileName, int supplyAmount, int buyAmount,
                              StringBuilder builder) {
        String stringWithData;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((stringWithData = bufferedReader.readLine()) != null) {
                String[] arrayString = stringWithData.split(REGEX);
                if (arrayString[0].equals(OPERATION_SUPPLY)) {
                    supplyAmount += Integer.parseInt(arrayString[1]);
                } else {
                    buyAmount += Integer.parseInt(arrayString[1]);
                }
            }

            builder.append(OPERATION_SUPPLY).append(COMA_SEPARATOR).append(supplyAmount)
                    .append(System.lineSeparator())
                    .append(OPERATION_BUY).append(COMA_SEPARATOR).append(buyAmount)
                    .append(System.lineSeparator())
                    .append(OPERATION_RESULT).append(COMA_SEPARATOR)
                    .append(supplyAmount - buyAmount);

        } catch (IOException e) {
            throw new RuntimeException(TEXT_OF_EXCEPTION);
        }
    }
}
