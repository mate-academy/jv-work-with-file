package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String OPERATION_TYPE_1 = "supply";
    private static final String OPERATION_TYPE_2 = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        File toFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(createReport(readFromFile(fromFileName)));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }

    private String[] readFromFile(String fileName) {
        File fromFile = new File(fileName);
        String[] data;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String value;
            Path path = fromFile.toPath();
            data = new String[(int) Files.lines(path).count()];
            int i = 0;
            while ((value = reader.readLine()) != null) {
                data[i] = value;
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        return data;
    }

    private String createReport(String[] data) {
        int amount1 = 0;
        int amount2 = 0;
        for (String dataLine : data) {
            String[] dataElement = dataLine.split(",");
            if (dataElement[0].equals(OPERATION_TYPE_1)) {
                amount1 = amount1 + Integer.parseInt(dataElement[1]);
            }
            if (dataElement[0].equals(OPERATION_TYPE_2)) {
                amount2 = amount2 + Integer.parseInt(dataElement[1]);
            }
        }
        StringBuilder builder = new StringBuilder();
        return String.valueOf(builder
                .append(OPERATION_TYPE_1).append(",").append(amount1).append(System.lineSeparator())
                .append(OPERATION_TYPE_2).append(",").append(amount2).append(System.lineSeparator())
                .append("result,").append(amount1 - amount2));
    }
}
