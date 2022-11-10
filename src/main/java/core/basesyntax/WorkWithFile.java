package core.basesyntax;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {

    private static final String COMMA_DELIMITER = ",";

    public void getStatistic(String fromFileName,
                             String toFileName) {
        String report;
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));) {
            String lines = bufferedReader.readLine();
            while (lines != null) {
                String[] values = lines.split(COMMA_DELIMITER);
                stringBuilder.append(System.lineSeparator()).append(values[0]);
                int sum = 0;
                for (int i = 1; i < values.length; i++){
                    sum += Integer.parseInt(values[i]);
                }
                stringBuilder.append(",").append(sum);
            }
            report = stringBuilder.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of("src/main/java/core/basesyntax/" + toFileName))) {
            bufferedWriter.write("```csv");
            bufferedWriter.newLine();
            bufferedWriter.write(report);
            bufferedWriter.newLine();
            bufferedWriter.write("```");
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
