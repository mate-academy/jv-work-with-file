package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder builder = new StringBuilder();
        int buy = 0;
        int supply = 0;
        final int IN = 1;
        int result;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());

                if (value.startsWith("buy")) {
                    buy += Integer.parseInt(value.substring(value.indexOf(",") + IN));
                } else if (value.startsWith("supply")) {
                    supply += Integer.parseInt(value.substring(value.indexOf(",") + IN));
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read a file", e);
        }

        result = supply - buy;

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true));
            writer.write("supply," + supply + System.lineSeparator()
                        + "buy," + buy + System.lineSeparator()
                        + "result," + result);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to the file", e);
        }
    }
}
