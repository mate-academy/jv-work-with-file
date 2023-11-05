package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, generateReport(readContent(fromFileName)));
    }

    private String readContent(String fromFileName) {
        final StringBuilder stringBuilder = new StringBuilder();
        String value;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant read file" + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private List<String> generateReport(String rawContent) {

        String[] receivedData = rawContent.split(System.lineSeparator());
        List<String> report = new ArrayList<>();
        int supplySum = 0;
        int buySum = 0;

        for (int j = 0; j < receivedData.length; j++) {
            String[] operation = receivedData[j].split(",");
            if (operation[0].equals("buy")) {
                buySum += Integer.parseInt(operation[1]);
            } else if (operation[0].equals("supply")) {
                supplySum += Integer.parseInt(operation[1]);
            }
        }
        report.add("supply," + supplySum);
        report.add("buy," + buySum);
        report.add("result," + (supplySum - buySum));
        return report;
    }

    private void writeToFile(String toFileName, List<String> report) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can`t create new file", e);
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            for (String record : report) {
                bufferedWriter.write(record + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant write to file " + toFileName, e);
        }
    }
}

