package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int supply = 0;
    private int buy = 0;
    private int result = 0;
    private final String split = ",";
    private int index = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            String[] split = builder.toString().replaceAll("\r\n", ",").split(",");
            for (String test : split) {
                if (test.equals("supply")) {
                    supply = supply + Integer.parseInt(split[index]);
                    index += 2;
                }
                if (test.equals("buy")) {
                    buy = buy + Integer.parseInt(split[index]);
                    index += 2;
                }
            }
            result = supply - buy;
        } catch (IOException e) {
            throw new RuntimeException("Can't read this file", e);
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true));
            writer.write("supply," + supply + System.lineSeparator());
            writer.flush();
            writer.write("buy," + buy + System.lineSeparator());
            writer.flush();
            writer.write("result," + result + System.lineSeparator());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file");
        }
    }
}
