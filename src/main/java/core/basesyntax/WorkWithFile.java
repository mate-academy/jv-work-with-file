package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String OPERATION_TYPE_BUY = "buy";
    private static final String OPERATION_TYPE_SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> records = new ArrayList<>();
        StringBuilder report = new StringBuilder();
        readFromFile(fromFileName, records, report);
        writeToFile(toFileName, report);
    }

    private static void readFromFile(String fromFileName,
                                     List<String> stringFromFile, StringBuilder report) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String read = bufferedReader.readLine();
            while (read != null) {
                stringFromFile.add(read);
                read = bufferedReader.readLine();
            }
            int buy = 0;
            int supply = 0;

            for (String s : stringFromFile) {
                String[] split = s.split(",");
                int digit = Integer.parseInt(split[1]);
                if (split[0].equals(OPERATION_TYPE_BUY)) {
                    buy += digit;
                } else if (split[0].equals(OPERATION_TYPE_SUPPLY)) {
                    supply += digit;
                }
            }

            int result = supply - buy;
            report.append("supply,")
                    .append(supply)
                    .append(System.lineSeparator())
                    .append("buy,")
                    .append(buy)
                    .append(System.lineSeparator())
                    .append("result,")
                    .append(result);

        } catch (IOException e) {
            throw new RuntimeException("Failed to read from file: " + fromFileName, e);
        }
    }

    private static void writeToFile(String toFileName, StringBuilder report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report.toString());
        } catch (IOException e) {
            throw new RuntimeException("Failed to write from file: " + toFileName, e);
        }
    }
}
