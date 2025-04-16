package core.basesyntax;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReportWriter {
    public String writeReport(String fromFileName, String report) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fromFileName))) {
            bufferedReader.readLine(); // пропустить первую строку

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Unable to read file" + fromFileName, e);
        }
    }
}
