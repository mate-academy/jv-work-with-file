package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE = 0;
    private static final int AMOUNT = 1;
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder stringBuilder = new StringBuilder();
            String value = reader.readLine();

            while (value != null) {
                stringBuilder.append(value).append("\n");
                value = reader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file",e);
        }
    }

    private String generateReport(String string) {
        int resultSupply = 0;
        int resultBuy = 0;
        int result;

        String[] lines = string.split("\n");
        for (String line : lines) {
            String[] data = line.split(SEPARATOR);
            if (data.length == 2) {
                if (data[OPERATION_TYPE].equals("supply")) {
                    resultSupply += Integer.parseInt(data[AMOUNT]);
                } else if (data[OPERATION_TYPE].equals("buy")) {
                    resultBuy += Integer.parseInt(data[AMOUNT]);
                }
            }
        }

        result = resultSupply - resultBuy;

        return "supply," + resultSupply + "\n"
                + "buy," + resultBuy + "\n"
                + "result," + result;
    }

    private void writeToFile(String toFileName, String report) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write(report);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't writer file", e);
        }
    }
}
