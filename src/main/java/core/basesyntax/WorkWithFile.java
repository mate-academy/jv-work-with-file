package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        final String operationBuy = "buy";
        final String operationSupply = "supply";
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String lines;

            String[] value;
            int buy = 0;
            int supply = 0;
            while ((lines = reader.readLine()) != null) {
                value = lines.split(",");
                if (value.length < 2) {
                    continue;
                }
                if (value[0].equals(operationBuy)) {
                    buy += Integer.parseInt(value[1]);
                } else if (value[0].equals(operationSupply)) {
                    supply += Integer.parseInt(value[1]);
                }
            }
            int result = supply - buy;
            writeReport(toFile, supply, buy, result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeReport(File toFile, int supply, int buy, int result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write("supply," + supply);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + buy);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
