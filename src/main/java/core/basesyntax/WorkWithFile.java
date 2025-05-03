package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public static final int OPERATION_TYPE = 0;
    public static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        int supplySum = 0;
        int buySum = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] words = value.split(",");
                if (words[OPERATION_TYPE].equals("supply")) {
                    supplySum += Integer.parseInt(words[AMOUNT]);
                } else if (words[OPERATION_TYPE].equals("buy")) {
                    buySum += Integer.parseInt(words[AMOUNT]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error processing files: can't read file", e);
        }

        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(getReport(supplySum, buySum));
        } catch (IOException e) {
            throw new RuntimeException("Error processing files: can't write in file", e);
        }

    }

    private String getReport(int supply, int buy) {
        int result = (supply - buy);
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result).append(System.lineSeparator());
        return reportBuilder.toString();
    }
}
