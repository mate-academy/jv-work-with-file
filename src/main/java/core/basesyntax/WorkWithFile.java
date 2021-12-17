package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, generateReport(readContent(fromFileName)));
    }

    private String readContent(String fromFileName) {
        final StringBuilder stringBuilder = new StringBuilder();
        String value;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
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

    private ArrayList<String> generateReport(String rawContent) {
        String[] receivedData = rawContent.split(System.lineSeparator());
        ArrayList<String> report = new ArrayList<>();
        int supplySum = 0;
        int buySum = 0;
        for (int i = 0; i < receivedData.length; i++) {
            String operation = receivedData[i].substring(0, receivedData[i].indexOf(","));
            int sumByOperration = 0;
            for (int j = 0; j < receivedData.length; j++) {
                if (operation.equals(receivedData[j]
                        .substring(0, receivedData[j].indexOf(",")))) {
                    sumByOperration += Integer.parseInt(receivedData[j]
                            .substring(receivedData[j].indexOf(",") + 1));
                }
            }
            if (operation.equals("supply")) {
                supplySum = sumByOperration;
            } else if (operation.equals("buy")) {
                buySum = sumByOperration;
            }
        }
        report.add("supply," + supplySum);
        report.add("buy," + buySum);
        report.add("report," + (supplySum - buySum));
        return report;
    }

    private void writeToFile(String toFileName, ArrayList<String> report) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can`t create new file", e);
        }
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter((new FileWriter(toFileName, true)));
            for (String record : report) {
                bufferedWriter.write(record + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file" + toFileName, e);
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can`t close bufferedWriter");
                }
            }
        }
    }
}

