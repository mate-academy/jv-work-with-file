package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_BUY = "buy";
    private static final String DEMILITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int totalBuy = 0;
        int totalSupply = 0;
        File file = new File(fromFileName);

        try (FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            String [] data;

            while ((line = bufferedReader.readLine()) != null) {
                data = line.split(DEMILITER);
                if (data[0].equals(OPERATION_BUY)) {
                    totalBuy += Integer.parseInt(data[1]);
                } else {
                    totalSupply += Integer.parseInt(data[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant get data from file - " + fromFileName, e);
        }
        writeInResult(toFileName, totalSupply, totalBuy);
    }

    private String createReport(int totalSupply, int totalBuy) {
        StringBuilder report = new StringBuilder();
        int result = totalSupply - totalBuy;

        report.append("supply,").append(totalSupply)
                .append(System.lineSeparator())
                .append("buy,").append(totalBuy)
                .append(System.lineSeparator())
                .append("result,").append(result);

        return report.toString();
    }

    private void writeInResult(String toFile, int totalSupply, int totalBuy) {
        String reportToWrite = createReport(totalSupply, totalBuy);
        File inputFile = new File(toFile);

        try (FileWriter fileWriter = new FileWriter(inputFile)) {
            fileWriter.write(reportToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFile, e);
        }
    }
}
