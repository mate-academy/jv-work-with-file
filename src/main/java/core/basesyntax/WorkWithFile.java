package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder fromFileContent = new StringBuilder();
        StringBuilder toFileContent = new StringBuilder();
        String[] operationTypes = new String[]{"supply", "buy"};

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                fromFileContent.append(value)
                        .append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("cannot read fromFileName", e);
        }

        String[] fromFileLines = fromFileContent.toString().split(System.lineSeparator());
        int result = 0;
        for (String operationType : operationTypes) {
            int amount = 0;
            toFileContent.append(operationType)
                    .append(",");

            for (String fromFileLine : fromFileLines) {
                String[] separatedLine = fromFileLine.split(",");
                if (separatedLine[0].equals(operationType)) {
                    amount += Integer.parseInt(separatedLine[1]);
                }
            }

            if (operationType.equals("supply")) {
                result += amount;
            } else {
                result -= amount;
            }

            toFileContent.append(amount)
                    .append(System.lineSeparator());
        }

        toFileContent.append("result,")
                .append(String.valueOf(result));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(toFileContent.toString());
        } catch (IOException e) {
            throw new RuntimeException("cannot write toFileName", e);
        }
    }
}
