package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader newReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = newReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = newReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file" + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String makeReport(String fromFileName) {
        int supply = 0;
        int buy = 0;
        String[] splittedLine = readFromFile(fromFileName).split("\\W+");
        for (int i = 0; i < splittedLine.length - 1; i += 2) {
            int quantity = Integer.parseInt(splittedLine[i + 1]);
            if (splittedLine[i].equals("supply")) {
                supply += quantity;
            } else {
                buy += quantity;
            }
        }
        int result = supply - buy;
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("supply,").append(supply)
                .append(System.lineSeparator()).append("buy,").append(buy)
                .append(System.lineSeparator()).append("result,").append(result);
        return reportBuilder.toString();
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String report = makeReport(fromFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(report);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write the data to the file" + toFileName, e);
        }
    }
}
