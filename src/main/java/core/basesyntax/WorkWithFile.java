package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readDataFromFile(fromFileName);
        String finishReport = createReport(dataFromFile);
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't creat file");
        }
        try {
            Files.write(file.toPath(), finishReport.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write report to file");
        }
    }

    public String createReport(String[]dataForReport) {
        String[] supplyBuyResult = {"supply", "buy", "result"};
        int supplyAmount = 0;
        int buyAmount = 0;
        for (int i = 0; i < dataForReport.length; i++) {
            String[] operationTypeAndAmount = dataForReport[i].split(",");
            String operationType = operationTypeAndAmount[0];
            int amount = Integer.parseInt(operationTypeAndAmount[1]);
            if (operationType.equals(supplyBuyResult[0])) {
                supplyAmount += amount;
            }
            if (operationType.equals(supplyBuyResult[1])) {
                buyAmount += amount;
            }
        }
        int result = supplyAmount - buyAmount;
        return supplyBuyResult[0] + "," + supplyAmount + System.lineSeparator()
                + supplyBuyResult[1] + "," + buyAmount + System.lineSeparator()
                + supplyBuyResult[2] + "," + result;
    }

    public String[] readDataFromFile(String fromFileName) {
        File file = new File(fromFileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(";");
                value = reader.readLine();
            }
            String[] dataFromFile = stringBuilder.toString().split(";");
            return dataFromFile;
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file");
        }
    }
}
