package core.basesyntax;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    public String readFromFile(String fromFileName) {
        File file = new File(fromFileName);

        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new
                FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + "fromFileName:", e);
        }
        return stringBuilder.toString();
    }

    public String createReport(String data) {

        String operationSupply = "supply";
        String operationBuy = "buy";
        int summaSupply = 0;
        int summaBuy = 0;
        final int operationIndex = 0;
        final int amountIndex = 1;

        String[] arrayFromFileName = data.split("\n");
        for (String record : arrayFromFileName) {
            String operation = record.split(",")[operationIndex];
            int amount = parseInt(record.split(",")[amountIndex]);

            if (operation.equals(operationSupply)) {
                summaSupply += amount;
            }
            if (operation.equals(operationBuy)) {
                summaBuy += amount;
            }
        }

        StringBuilder mainString = new StringBuilder();
        String comma = ",";
        String divide = "\n";
        int summaResult = summaSupply - summaBuy;
        mainString.append(operationSupply)
                .append(comma).append(summaSupply)
                .append(divide).append(operationBuy)
                .append(comma).append(summaBuy)
                .append(divide).append("result")
                .append(comma).append(summaResult);

        return mainString.toString();
    }

    public void writeToFile(String toFileName, String createReport) {
        File file = new File(toFileName);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new
                FileWriter(file, true))) {
            bufferedWriter.write(createReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't create the file " + toFileName, e);
        }
    }
}
