package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(createReport(readFile(new File(fromFileName))), toFileName);
    }

    private StringBuilder readFile(File file) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read this file", e);
        }
        return builder;
    }

    private String createReport(StringBuilder builder) {
        int buy = 0;
        int supply = 0;
        int result = 0;
        int index = 1;
        final int value1 = 2;
        String[] split = builder.toString().replaceAll("\r\n", ",").split(",");
        for (String test : split) {
            if (test.equals("supply")) {
                supply = supply + Integer.parseInt(split[index]);
                index += value1;
            }
            if (test.equals("buy")) {
                buy = buy + Integer.parseInt(split[index]);
                index += value1;
            }
        }
        result = supply - buy;
        String resul = "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result + System.lineSeparator();
        return resul;
    }

    private void writeToFile(String statistic, String toFileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true));
            writer.write(statistic);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file");
        }
    }
}

