package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(fromFileName);
        try {
            BufferedReader info = new BufferedReader(new FileReader(file));
            int digitInfo = info.read();
            while (digitInfo != -1) {
                stringBuilder.append((char) digitInfo);
                digitInfo = info.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file from file", e);
        }

        String[] rows = stringBuilder.toString().split("\n");
        int supply = 0;
        int buy = 0;
        for (String row : rows) {
            String[] values = row.split(",");
            if (values[0].equals("supply")) {
                supply += Integer.parseInt(values[1]);
            } else {
                buy += Integer.parseInt(values[1]);
            }
        }
        int result = supply - buy;
        String report = "supply," + supply + "\n"
                + "buy," + buy + "\n"
                + "result," + result;
        File file2 = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file2))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
