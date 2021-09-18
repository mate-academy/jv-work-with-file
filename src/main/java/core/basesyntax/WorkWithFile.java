package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            int supply = 0;
            int buy = 0;
            int result = 0;
            for (String readAllLine : Files.readAllLines(new File(fromFileName).toPath())) {
                String[] split = readAllLine.split(",");
                int i = Integer.parseInt(split[1]);
                if (readAllLine.contains("supply")) {
                    supply += i;
                } else {
                    buy += i;
                }
                result = supply - buy;
            }
            String s = String.valueOf(supply);
            String b = String.valueOf(buy);
            String.valueOf(result);
            stringBuilder.append("supply")
                    .append(",")
                    .append(s)
                    .append(System.lineSeparator())
                    .append("buy")
                    .append(",")
                    .append(b)
                    .append(System.lineSeparator())
                    .append("result")
                    .append(",")
                    .append(result);
            writeToFile(toFileName, stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(String toFileName, String data) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create a file", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }
}
