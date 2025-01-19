package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File inputFile = new File(fromFileName);
        File outputFile = new File(toFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(',');
                value = bufferedReader.readLine();
            }
            String[] contentOfTheFile = stringBuilder.toString().split(",");
            String[] operationType = new String[contentOfTheFile.length / 2];
            int[] amount = new int[contentOfTheFile.length / 2];
            int supplyAmount = 0;
            int buyAmount = 0;
            int resultAmount = 0;
            int indexOperationType = 0;
            int indexAmount = 0;
            for (int i = 0; i < contentOfTheFile.length; i++) {
                if (i % 2 == 0) {
                    operationType[indexOperationType] = contentOfTheFile[i];
                    indexOperationType++;
                } else {
                    amount[indexAmount] = Integer.parseInt(contentOfTheFile[i]);
                    indexAmount++;
                }
            }
            for (int i = 0; i < indexOperationType; i++) {
                if (operationType[i].equals("supply")) {
                    supplyAmount += amount[i];
                }
                if (operationType[i].equals("buy")) {
                    buyAmount += amount[i];
                }
            }
            resultAmount = supplyAmount - buyAmount;
            StringBuilder newStringBuilder = new StringBuilder();
            newStringBuilder.append("supply,")
                    .append(supplyAmount).append(System.lineSeparator()).append("buy,")
                    .append(buyAmount).append(System.lineSeparator())
                    .append("result,").append(resultAmount);
            bufferedWriter.write(newStringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't open file");
        }

    }
}
