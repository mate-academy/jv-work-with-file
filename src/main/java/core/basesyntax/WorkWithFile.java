package core.basesyntax;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        Path inputFile = Paths.get(fromFileName);
        final int[] supply = {0};
        final int[] buy = {0};
        final int indexOperation = 0;
        final int indexValue = 1;

        try (Stream<String> lines = Files.lines(inputFile)) {
            lines.forEach(line -> {
                String[] parts = line.split(",");
                if ("supply".equals(parts[indexOperation])) {
                    supply[0] += Integer.parseInt(parts[indexValue]);
                } else if ("buy".equals(parts[indexOperation])) {
                    buy[0] += Integer.parseInt(parts[indexValue]);
                }
            });
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
        String result = String.format("supply,%d%nbuy,%d%nresult,%d", supply[0], buy[0],
                supply[0] - buy[0]);
        Path outputFile = Paths.get(toFileName);
        try {
            Files.write(outputFile, result.getBytes(StandardCharsets.UTF_8));
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }
}
