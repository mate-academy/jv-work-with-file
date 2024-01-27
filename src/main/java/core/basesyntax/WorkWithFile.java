package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File readFile = new File(fromFileName);
        File writeFile = new File(toFileName);
        StringBuilder builder = new StringBuilder();

        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(readFile));
            while ((line = reader.readLine()) != null) {
                builder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("can't read the file", e);
        }

        String[] infoArr = builder.toString().split("[\\r\\n\\s]+");

        int buy = 0;
        int supply = 0;
        for (String info : infoArr) {
            if (info.startsWith("buy,")) {
                buy += Integer.parseInt(info.substring(4));
            } else if (info.startsWith("supply,")) {
                supply += Integer.parseInt(info.substring(7));
            }
        }

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator()).append("result,")
                .append(supply - buy);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile))) {
            writer.write(reportBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("can't write to the file", e);
        }
    }
}
