package core.basesyntax;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final int TYPE = 0;
    private static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        Path fileInput = Path.of(fromFileName);
        Path fileOutput = Path.of(toFileName);

        try (BufferedReader reader = Files.newBufferedReader(fileInput)) {
            String input = reader.readLine();
            int buy = 0;
            int supply = 0;

            while (input != null) {
                String[] parsedValues = input.split(",");

                if ("supply".equals(parsedValues[TYPE])) {
                    supply += Integer.parseInt(parsedValues[AMOUNT]);
                } else {
                    buy += Integer.parseInt(parsedValues[AMOUNT]);
                }
                input = reader.readLine();
            }
            int result = supply - buy;

            Files.writeString(fileOutput, "supply," + supply + System.lineSeparator()
                             + "buy," + buy + System.lineSeparator()
                             + "result," + result);
        } catch (IOException e) {
            throw new RuntimeException("cant open file.", e);
        }
    }
}
