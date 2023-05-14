package core.basesyntax;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorkWithFile {
    private final static String SUPPLY = "supply";
    private final static String BUY = "buy";
    private final static String RESULT = "result";
    private final static String DELIMITER = ",";
    private final static int keyIndex = 0;
    private final static int valueIndex = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        Stream<String> stream = readLines(fromFileName);
        String toWrite = getReport(stream);
        writeLines(toFileName, toWrite);
    }

    private Stream<String> readLines(String fromFileName) {
        Stream<String> streamOfLines = null;
        try {
            streamOfLines = Files.lines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Lines were not read",e);
        }
        return streamOfLines;
    }

    private void writeLines(String toFileName, String forWriting) {
        try {
            Files.write(Path.of(toFileName), forWriting.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException("Your trying to write bytes to file was not successful", e);
        }
    }

    private String getReport(Stream<String> stream) {
        Map<String, Integer> reportMap = stream
                .map(e -> e.split(DELIMITER))
                .collect(Collectors.groupingBy(e -> e[keyIndex],
                        Collectors.summingInt(e -> Integer.valueOf(e[valueIndex]))));
        int supply = reportMap.values().stream().max(Integer::compare).get();
        int buy = reportMap.values().stream().min(Integer::compare).get();
        int result = supply - buy;
        StringBuilder builder = new StringBuilder()
                .append(SUPPLY).append(DELIMITER).append(supply).append(System.lineSeparator())
                .append(BUY).append(DELIMITER).append(buy).append(System.lineSeparator())
                .append(RESULT).append(DELIMITER).append(result);
        return builder.toString();
    }
}
