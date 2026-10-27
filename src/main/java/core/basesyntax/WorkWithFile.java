package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        Path path = Path.of(fromFileName);
        try {
            List<String> lines = Files.readAllLines(path);
            int supply = 0;
            int buy = 0;
            StringBuilder generatedReport = new StringBuilder();
            for (String line : lines) {
                String[] parts = line.split(",");
                int amount = Integer.parseInt(parts[1]);
                if (SUPPLY.equals(parts[0])) {
                    supply += amount;
                } else if (BUY.equals(parts[0])) {
                    buy += amount;
                }
            }
            generatedReport.append("supply,").append(supply).append(System.lineSeparator());
            generatedReport.append("buy,").append(buy).append(System.lineSeparator());
            int result = supply - buy;
            generatedReport.append("result,").append(result);

            Files.writeString(Path.of(toFileName), generatedReport);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
