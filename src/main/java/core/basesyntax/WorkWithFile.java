package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int TYPE_OF_OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String COMMA = ",";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String string = readFile(fromFileName);
        writeResultToFile(string, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            String[] result;
            int totalSypply = 0;
            int totalBuy = 0;
            while (value != null) {
                result = value.split(",");
                if (result[TYPE_OF_OPERATION_INDEX].equals(SUPPLY)) {
                    totalSypply += Integer.parseInt(result[AMOUNT_INDEX]);
                }
                if (result[TYPE_OF_OPERATION_INDEX].equals(BUY)) {
                    totalBuy += Integer.parseInt(result[AMOUNT_INDEX]);
                }
                value = bufferedReader.readLine();
            }
            stringBuilder.append(SUPPLY + COMMA)
                    .append(totalSypply)
                    .append(System.lineSeparator())
                    .append(BUY + COMMA)
                    .append(totalBuy)
                    .append(System.lineSeparator())
                    .append(RESULT + COMMA)
                    .append(totalSypply - totalBuy)
                    .append(System.lineSeparator());

            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file" + fromFileName, e);
        }
    }

    private void writeResultToFile(String readFile, String toFileName) {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(readFile);
        } catch (IOException e) {
            throw new RuntimeException("Can't find file " + toFileName, e);
        }
    }
}

