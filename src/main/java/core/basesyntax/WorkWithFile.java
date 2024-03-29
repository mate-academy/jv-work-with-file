package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int OPERATION_TYPE = 0;
    public static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder fromFileContent = new StringBuilder();
        StringBuilder toFileContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                fromFileContent.append(value)
                        .append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("cannot read fromFileName: " + fromFileName, e);
        }

        toFileContent = makeReport(fromFileContent.toString().split(System.lineSeparator()));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(toFileContent.toString());
        } catch (IOException e) {
            throw new RuntimeException("cannot write toFileName: " + toFileName, e);
        }
    }

    public StringBuilder makeReport(String[] fromFileLines) {
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String fromFileLine : fromFileLines) {
            String[] separatedLine = fromFileLine.split(",");
            if (separatedLine[OPERATION_TYPE].equals(OperationTypes.SUPPLY.toString())) {
                supplyAmount += Integer.parseInt(separatedLine[AMOUNT]);
            } else {
                buyAmount += Integer.parseInt(separatedLine[AMOUNT]);
            }
        }

        return new StringBuilder("supply,")
                .append(supplyAmount)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buyAmount)
                .append(System.lineSeparator())
                .append("result,")
                .append(supplyAmount - buyAmount);
    }
}
