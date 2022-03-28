package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private StringBuilder stringBuilder;

    public WorkWithFile() {
        stringBuilder = new StringBuilder();
    }

    private String readFromFile(String fromFileName) {
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
            int data = Integer.parseInt(splittedLine[i + 1]);
            if (splittedLine[i].equals("supply")) {
                supply += data;
            } else {
                buy += data;
            }
        }
        int result = supply - buy;
        stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(String.valueOf(supply))
                .append(System.lineSeparator()).append("buy,").append(String.valueOf(buy))
                .append(System.lineSeparator()).append("result,").append(String.valueOf(result));
        return stringBuilder.toString();
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String resultData = makeReport(fromFileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(resultData);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write the data to the file" + toFileName, e);
        }
    }
}
