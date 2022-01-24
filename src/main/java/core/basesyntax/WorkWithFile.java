package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            if (fromFileName.length() == 0) {
                System.out.println("File is empty!");
            }
            StringBuilder stringBuilder = new StringBuilder();
            String dataFromFileName = bufferedReader.readLine();

            int supplyValue = 0;
            int buyValue = 0;
            int valueForReport = 0;
            String firstOperationType = "supply";
            String secondOperationType = "buy";

            while (dataFromFileName != null) {
                String[] divided = dataFromFileName.split(",");
                if (divided[0].equals(firstOperationType)) {
                    supplyValue += Integer.parseInt(divided[1]);
                } else if (divided[0].equals(secondOperationType)) {
                    buyValue += Integer.parseInt(divided[1]);
                }
                valueForReport = supplyValue - buyValue;
                dataFromFileName = bufferedReader.readLine();
            }

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
                bufferedWriter.write(stringBuilder
                        .append(firstOperationType)
                        .append(",").append(supplyValue)
                        .append(System.lineSeparator())
                        .append(secondOperationType)
                        .append(",")
                        .append(buyValue)
                        .append(System.lineSeparator())
                        .append("result")
                        .append(",")
                        .append(valueForReport)
                        .toString());
            } catch (IOException e) {
                throw new RuntimeException("Can't write to this file!", e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read this file!", e);
        }
    }
}

