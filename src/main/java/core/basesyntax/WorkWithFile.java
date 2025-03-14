package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int[] sums;
        String[] buyOrSupply = {"supply", "buy"};
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            sums = new int[buyOrSupply.length];
            String value = reader.readLine();
            while (value != null) {
                String[] fileLine = value.split(",");
                for (int j = 0; j < buyOrSupply.length; j++) {
                    if (fileLine[0].equals(buyOrSupply[j])) {
                        int sum = sums[j];
                        sums[j] = Integer.parseInt(fileLine[1]);
                        sums[j] += sum;
                        value = reader.readLine();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        int result = sums[0] - sums[1];
        StringBuilder report = new StringBuilder();
        report.append(buyOrSupply[0]).append(",").append(sums[0])
                .append(System.lineSeparator()).append(buyOrSupply[1]).append(",")
                .append(sums[1]).append(System.lineSeparator()).append("result").append(",")
                .append(result);
        String finnalyReport = report.toString();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(finnalyReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }
}
