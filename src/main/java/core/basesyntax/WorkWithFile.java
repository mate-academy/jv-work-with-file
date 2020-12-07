package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        writeReport(sumOfOperation("supply",fromFile),
                    sumOfOperation("buy",fromFile),
                    toFile);
    }

    private int sumOfOperation(String operationName, File fromFileName) {
        int sum = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String readValue = bufferedReader.readLine();
            while (readValue != null) {
                if (readValue.split(",")[0].equals(operationName)) {
                    sum += Integer.parseInt(readValue.split(",")[1]);
                }
                readValue = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        return sum;
    }

    private void writeReport(int supply, int buy, File toFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write("supply,");
            bufferedWriter.write(String.valueOf(supply));
            bufferedWriter.newLine();

            bufferedWriter.write("buy,");
            bufferedWriter.write(String.valueOf(buy));
            bufferedWriter.newLine();

            bufferedWriter.write("result,");
            bufferedWriter.write(String.valueOf(supply - buy));

            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }
}
