package core.basesyntax;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class WorkWithFile {
    private static final int INDEX_OPERATION = 0;
    private static final int INDEX_VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String result = createReportFromData(fromFileName);
        writeData(toFileName, result);
    }

    private void writeData(String toFileName, String result) {
        Path outputFile = Paths.get(toFileName);
        try {
            Files.write(outputFile, result.getBytes(StandardCharsets.UTF_8));
        } catch (IOException ioException) {
            throw new RuntimeException(ioException.getMessage());
        }
    }

    private String createReportFromData(String fromFileName) {
        Path inputFile = Paths.get(fromFileName);
        final int[] supply = {0};
        final int[] buy = {0};

        try (Stream<String> lines = Files.lines(inputFile)) {
            lines.forEach(line -> {
                String[] parts = line.split(",");
                if ("supply".equals(parts[INDEX_OPERATION])) {
                    supply[0] += Integer.parseInt(parts[INDEX_VALUE]);
                } else if ("buy".equals(parts[INDEX_OPERATION])) {
                    buy[0] += Integer.parseInt(parts[INDEX_VALUE]);
                }
            });
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
        return new StringBuilder("supply,").append(supply[0])
            .append("\nbuy,").append(buy[0])
            .append("\nresult,").append(supply[0] - buy[0])
            .toString();
    }
}
