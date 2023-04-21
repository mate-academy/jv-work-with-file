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
    private static final String COMMA = ",";
    private static final int NAME_INDEX = 0;
    private static final int AMMOUNT_INDEX = 1;

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
            String[] parts = line.split(COMMA);
            consolidatedMap.put(parts[NAME_INDEX], consolidatedMap.get(parts[NAME_INDEX]) == null
                    ? parts[AMMOUNT_INDEX] :
                    String.valueOf(Integer.parseInt(consolidatedMap.get(parts[NAME_INDEX]))
                            + Integer.parseInt(parts[AMMOUNT_INDEX])));
        }
        return tableFormation(consolidatedMap);
    }

    private String tableFormation(Map<String, String> contentMap) {
        String resultSum = String.valueOf(Integer.parseInt(contentMap.get(NAME_OF_FIRST_LINE))
                - Integer.parseInt(contentMap.get(NAME_OF_SECOND_LINE)));
        return NAME_OF_FIRST_LINE + COMMA + contentMap.get(NAME_OF_FIRST_LINE)
                + System.lineSeparator()
                + NAME_OF_SECOND_LINE + COMMA + contentMap.get(NAME_OF_SECOND_LINE)
                + System.lineSeparator()
                + NAME_OF_THIRD_LINE + COMMA + resultSum
                + System.lineSeparator();
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
