package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(toFileName))) {

            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            int supplySum = 0;
            int buySum = 0;
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                String[] parts = line.split(",");
                int extractedNumber = Integer.parseInt(parts[1]);
                if (parts[0].equals("supply")) {
                    supplySum += extractedNumber;
                } else if (parts[0].equals("buy")) {
                    buySum += extractedNumber;
                }

                line = bufferedReader.readLine();
            }
            int result = supplySum - buySum;
            makeReport(supplySum, buySum, result, bufferedWriter);
        } catch (IOException e) {
            throw new RuntimeException("Error while processing file: "
                    + e.getMessage(), e);
        }
    }

    private static void makeReport(int supplySum, int buySum, int result,
                                   BufferedWriter bufferedWriter) throws IOException {
        String[] report = {"supply," + supplySum + System.lineSeparator(),
                "buy," + buySum + System.lineSeparator(),
                "result," + result + System.lineSeparator()};
        for (String s : report) {
            bufferedWriter.write(s);
        }
    }
}
