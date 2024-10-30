package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        readFile(fromFileName, toFileName);
    }

    private void readFile(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        int countSupply = 0;
        int countBuy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] data = value.split(",");
                if (data[OPERATION_INDEX].equals("supply")) {
                    countSupply += Integer.parseInt(data[VALUE_INDEX]);
                } else if (data[OPERATION_INDEX].equals("buy")) {
                    countBuy += Integer.parseInt(data[VALUE_INDEX]);
                }
                value = bufferedReader.readLine();
            }
            writeFile(toFileName, countSupply, countBuy);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFile, e);
        }
    }

    private void writeFile(String toFileName, int countSupply, int countBuy) {
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(createReport(countSupply, countBuy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }

    private String createReport(int countSupply, int countBuy) {
        StringBuilder report = new StringBuilder();
        int result = countSupply - countBuy;
        return report.append("supply,").append(countSupply).append("\n")
                .append("buy,").append(countBuy).append("\n")
                .append("result,").append(result).toString();
    }
}
