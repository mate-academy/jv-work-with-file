package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final int ACTION = 0;
    private static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String action = parts[ACTION];
                String amount = parts[AMOUNT];
                if ("buy".equals(action)) {
                    buy += Integer.parseInt(amount);
                } else if ("supply".equals(action)) {
                    supply += Integer.parseInt(amount);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }

        String content = getContent(supply, buy);
        writeContentToFile(content, toFileName);
    }

    private String getContent(int supply, int buy) {
        StringBuilder sb = new StringBuilder();
        sb.append("supply,").append(supply).append(System.lineSeparator());
        sb.append("buy,").append(buy).append(System.lineSeparator());
        sb.append("result,").append(supply - buy);
        return sb.toString();
    }

    private void writeContentToFile(String content, String fileName) {
        File file = new File(fileName);
        try {
            Files.writeString(file.toPath(), content);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to file", e);
        }
    }
}
