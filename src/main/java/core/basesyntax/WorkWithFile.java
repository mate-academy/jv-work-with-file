package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final String OPERATION_BUY = "buy";
    private static final String DEMILITER = ",";
    private final StringBuilder report = new StringBuilder();
    private int totalBuy = 0;
    private int totalSupply = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);

        try (FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            String [] data;

            while ((line = bufferedReader.readLine()) != null) {
                data = line.split(DEMILITER);
                if (data[0].equals(OPERATION_BUY)) {
                    totalBuy += convertToInt(data[1]);
                } else {
                    totalSupply += convertToInt(data[1]);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Cant get data from file - " + fromFileName);
        }
        writeInResult(toFileName);
    }

    private int convertToInt(String num) {
        return Integer.parseInt(num);
    }

    private String createReport() {
        int result = totalSupply - totalBuy;

        report.append("supply,").append(totalSupply)
                .append(System.lineSeparator())
                .append("buy,").append(totalBuy)
                .append(System.lineSeparator())
                .append("result,").append(result);

        return report.toString();
    }

    private void writeInResult(String toFile) {
        String reportToWrite = createReport();
        File inputFile = new File(toFile);

        try (FileWriter fileWriter = new FileWriter(inputFile)) {
            fileWriter.write(reportToWrite);
        } catch (Exception e) {
            throw new RuntimeException("Can't write data to the file" + toFile, e);
        }
    }
}
