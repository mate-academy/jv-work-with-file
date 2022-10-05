package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private String getReport(int [] supplyAndResult) {
        int supplySum = supplyAndResult[0];
        int buySum = supplyAndResult[1];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply," + supplySum + System.lineSeparator()
                + "buy," + buySum + System.lineSeparator()
                + "result," + (supplySum - buySum));
        return new String(stringBuilder);
    }

    private int [] readFromFile(String fromFileName) {
        int supplySum = 0;
        int buySum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                String [] line = value.split(",");
                if (line[0].equals("supply")) {
                    supplySum += Integer.parseInt(line[1]);
                } else if (line[0].equals("buy")) {
                    buySum += Integer.parseInt(line[1]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + fromFileName, e);
        }
        return new int [] {supplySum, buySum};
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter ButterWriter = new BufferedWriter(new FileWriter(toFileName))) {
            ButterWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to a new file " + toFileName, e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String reportResult = getReport(readFromFile(fromFileName));
        writeToFile(toFileName, reportResult);
    }
}
