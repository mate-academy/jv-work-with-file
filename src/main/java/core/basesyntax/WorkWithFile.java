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
    private static final String NAME_OF_FIRST_CELL = "supply";
    private static final String NAME_OF_SECOND_CELL = "buy";
    private static final String NAME_OF_THIRD_CELL = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] entity = systemizeEntity(parseLinesFromFile(fromFileName));
        writeToFile(entity, toFileName);
    }

    private void writeToFile(String[] entity, String toFileName) {
        File file = createFile(toFileName);
        for (String line : entity) {
            try {
                Files.write(file.toPath(), line.getBytes(StandardCharsets.UTF_8),
                        StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException("File cannot be written", e);
            }
        }
    }

    private File createFile(String toFileName) {
        File file = new File(toFileName);
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
        return file;
    }

    private String[] systemizeEntity(String[] parsedLines) {
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

    private String[] tableFormation(Map<String, String> consolidatedMap) {
        String resultSum = String.valueOf(Integer.parseInt(consolidatedMap.get(NAME_OF_FIRST_CELL))
                - Integer.parseInt(consolidatedMap.get(NAME_OF_SECOND_CELL)));
        return new String[]{
                NAME_OF_FIRST_CELL + "," + consolidatedMap.get(NAME_OF_FIRST_CELL)
                        + System.lineSeparator(),
                NAME_OF_SECOND_CELL + "," + consolidatedMap.get(NAME_OF_SECOND_CELL)
                        + System.lineSeparator(),
                NAME_OF_THIRD_CELL + "," + resultSum
                        + System.lineSeparator()
        };
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
