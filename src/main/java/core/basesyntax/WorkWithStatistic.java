package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithStatistic {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        int supply = 0;
        int buy = 0;
        int result = 0;
        try {
            for (String readAllLine : Files.readAllLines(new File(fromFileName).toPath())) {
                String[] split = readAllLine.split(",");
                int parseInt = Integer.parseInt(split[1]);
                if (readAllLine.contains("supply")) {
                    supply += parseInt;
                } else {
                    buy += parseInt;
                }
                result = supply - buy;
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from the file", e);
        }
        stringBuilder.append("supply")
                .append(",")
                .append(supply)
                .append(System.lineSeparator())
                .append("buy")
                .append(",")
                .append(buy)
                .append(System.lineSeparator())
                .append("result")
                .append(",")
                .append(result);
        writeToFile(toFileName, stringBuilder.toString());
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
