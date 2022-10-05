package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int buy = 0;
    private int supply = 0;
    private int result = 0;
    private int[] res = {supply, buy, result};

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(calculate(readFile(new File(fromFileName))), toFileName);
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

    private int[] calculate(StringBuilder builder) {
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
        int[] res = {supply, buy, result};
        return res;
    }

    private void writeToFile(int[] res, String toFileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true));
            writer.write("supply," + res[0] + System.lineSeparator());
            writer.flush();
            writer.write("buy," + res[1] + System.lineSeparator());
            writer.flush();
            writer.write("result," + res[2] + System.lineSeparator());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file");
        }
    }
}

