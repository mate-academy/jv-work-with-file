package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        int result;
        String report = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                if (str.contains("supply")) {
                    String[] values = str.split(",");
                    int numbers = Integer.parseInt(values[1]);
                    supply += numbers;
                }
                if (str.contains("buy")) {
                    String[] values = str.split(",");
                    int money = Integer.parseInt(values[1]);
                    buy += money;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file ", e);
        }
        result = supply - buy;
        report = "supply," + supply + System.lineSeparator()
                    + "buy," + buy + System.lineSeparator()
                    + "result," + result;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write file", e);
        }
    }
}
