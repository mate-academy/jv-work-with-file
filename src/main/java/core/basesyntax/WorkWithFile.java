package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    private static final String SUPPLY_FIELD = "supply";
    private static final String BUY_FIELD = "buy";
    private static final String RESULT_FIELD = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fromFileName))) {
            StringBuilder dataFromFile = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                dataFromFile.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            String[] arrayOfDataFromFile = dataFromFile.toString().split(System.lineSeparator());
            reportCreation(arrayOfDataFromFile,toFileName);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
    }

    private String reportCreation(String[] dataFromFile, String toFileName) {
        int supply = 0;
        int buy = 0;
        for (String line : dataFromFile) {
            if (line.split(COMMA)[0].equals(SUPPLY_FIELD)) {
                supply += Integer.parseInt(line.split(COMMA)[1]);
            } else {
                buy += Integer.parseInt(line.split(COMMA)[1]);
            }
        }

        String report = new StringBuilder().append(SUPPLY_FIELD).append(COMMA)
                .append(supply).append(System.lineSeparator())
                .append(BUY_FIELD).append(COMMA).append(buy).append(System.lineSeparator())
                .append(RESULT_FIELD).append(COMMA).append(supply - buy).toString();

        return writeToFile(report, toFileName);
    }

    private String writeToFile(String report, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
        return report;
    }
}
