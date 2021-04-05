package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkWithFile {
    private static final int OPERATION_NAME = 0;
    private static final int OPERATION_AMOUNT = 1;
    private static final String REGEX = "[\\W]";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] result = readFromFile(fromFileName);
        writeToFile(toFileName, dataProcessing(result));

    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> strings;

        try {
            strings = Files.readAllLines(Path.of(fromFileName));
            if (strings.toString().equals("[]")) {
                return new String[0];
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't open file! " + fromFileName, e);
        }

        for (String line : strings.toString()
                .substring(1, strings.toString().length() - 1)
                .split("\n")) {
            stringBuilder.append(line)
                    .append(" ");

        }
        return stringBuilder.toString().split(" ");
    }

    private void writeToFile(String toFile, String dataToWriting) {
        File file = new File(toFile);
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            bufferedWriter.write(dataToWriting);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFile, e);
        }
    }

    private String dataProcessing(String[] data) {
        Map<String, Integer> result = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();

        for (String line : data) {
            String[] buffer = line.split(",");
            if (result.containsKey(buffer[OPERATION_NAME])) {
                result.put(buffer[OPERATION_NAME], result.get(buffer[OPERATION_NAME])
                        + Integer.parseInt(buffer[OPERATION_AMOUNT]));
            } else {
                result.put(buffer[OPERATION_NAME], Integer.parseInt(buffer[OPERATION_AMOUNT]));
            }
        }

        result.put("result", result.get("supply") - result.get("buy"));
        stringBuilder.append("supply,")
                .append(result.get("supply"))
                .append(System.lineSeparator())
                .append("buy,")
                .append(result.get("buy"))
                .append(System.lineSeparator())
                .append("result,")
                .append(result.get("result"));

        return stringBuilder.toString();
    }

}
