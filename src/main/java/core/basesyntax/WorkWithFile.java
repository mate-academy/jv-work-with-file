package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    private static final String NAME_OF_FIRST_LINE = "supply";
    private static final String NAME_OF_SECOND_LINE = "buy";
    private static final String NAME_OF_THIRD_LINE = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String entity = systemizeEntity(parseLinesFromFile(fromFileName));
        writeToFile(entity, toFileName);
    }

    private void writeToFile(String entity, String toFileName) {
        File file = new File(toFileName);
        try {
            Files.write(file.toPath(), entity.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException("File cannot be written", e);
        }
    }

    private String systemizeEntity(String[] parsedLines) {
        Map<String, String> consolidatedMap = new HashMap<>();
        for (String line : parsedLines) {
            String[] parts = line.split(",");
            consolidatedMap.put(parts[0], consolidatedMap.get(parts[0]) == null
                    ? parts[1] :
                    String.valueOf(Integer.parseInt(consolidatedMap.get(parts[0]))
                            + Integer.parseInt(parts[1])));
        }
        return tableFormation(consolidatedMap);
    }

    private String tableFormation(Map<String, String> contentMap) {
        String resultSum = String.valueOf(Integer.parseInt(contentMap.get(NAME_OF_FIRST_LINE))
                - Integer.parseInt(contentMap.get(NAME_OF_SECOND_LINE)));
        return new StringBuilder()
                .append(NAME_OF_FIRST_LINE + ",").append(contentMap.get(NAME_OF_FIRST_LINE))
                .append(System.lineSeparator())
                .append(NAME_OF_SECOND_LINE).append(",").append(contentMap.get(NAME_OF_SECOND_LINE))
                .append(System.lineSeparator())
                .append(NAME_OF_THIRD_LINE).append(",").append(resultSum)
                .append(System.lineSeparator()).toString();
    }

    private String[] parseLinesFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder lines = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                lines.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            return lines.toString().split(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("File cannot be read!", e);
        }
    }
}
