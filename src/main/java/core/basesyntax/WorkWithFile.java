package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int [] values = readFromfile(fromFileName);
        String report = createReport(values);
        writeToFile(toFileName, report);

    }

    private int[] readFromfile(String fileName) {
        int supplySum = 0;
        int buySum = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] values = line.split(",");
                if (values[0].equals("supply")) {
                    supplySum += Integer.parseInt(values[1]);
                } else {
                    buySum += Integer.parseInt(values[1]);
                }
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + e);
        }
        return new int[] {supplySum, buySum};
    }

    private String createReport(int[] values) {
        int result = values[0] - values[1];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(values[0]).append(System.lineSeparator());
        stringBuilder.append("buy,").append(values[1]).append(System.lineSeparator());
        stringBuilder.append("result,").append(result).append(System.lineSeparator());
        return stringBuilder.toString();
    }

    private void writeToFile(String fileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);
        } catch (IOException ex) {
            throw new RuntimeException("Can't write data to file" + ex);
        }

    }
}
