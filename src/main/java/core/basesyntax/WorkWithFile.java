package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int sumA = 0;
        int sumB = 0;

        try {
            List<String> lines = Files.readAllLines(Paths.get(fromFileName));

            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",\\s*");

                if (parts[0].equals("supply")) {
                    sumA += Integer.parseInt(parts[1].trim());
                } else if (parts[0].equals("buy")) {
                    sumB += Integer.parseInt(parts[1].trim());
                }
            }

            int result = sumA - sumB;
            StringBuilder sb = new StringBuilder();
            sb.append("supply,").append(sumA).append(System.lineSeparator());
            sb.append("buy,").append(sumB).append(System.lineSeparator());
            sb.append("result,").append(result).append(System.lineSeparator());

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
                writer.write(sb.toString());
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read/write file", e);
        }
    }
}
