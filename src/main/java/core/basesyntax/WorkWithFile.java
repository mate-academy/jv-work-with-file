package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilderReader = new StringBuilder();
        int supply = 0;
        int buy = 0;
        int result = 0;
        String report = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilderReader.append(value).append(",");
                value = reader.readLine();
            }
            String [] fromStringBuilderReader = stringBuilderReader.toString().split(",");
            for (int i = 0; i < fromStringBuilderReader.length; i++) {
                if (fromStringBuilderReader[i].equals("supply")) {
                    supply += Integer.parseInt(fromStringBuilderReader[i + 1]);
                }
                if (fromStringBuilderReader[i].equals("buy")) {
                    buy += Integer.parseInt(fromStringBuilderReader[i + 1]);
                }
            }
            result = supply - buy;
            report = "supply," + supply + System.lineSeparator()
                    + "buy," + buy + System.lineSeparator()
                    + "result," + result;
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find the file", e);
        }
    }
}
