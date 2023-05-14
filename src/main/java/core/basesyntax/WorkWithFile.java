package core.basesyntax;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        Stream<String> str = null;
        try {
            str = Files.lines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String toWrite = getString(str);
        try {
            Files.write(Path.of(toFileName), toWrite.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException("Your trying to write bytes to file was not successful", e);
        }
    }

    private String getString(Stream<String> stream) {
        final String SUPPLY = "supply";
        final String BUY = "buy";
        final String RESULT = "result";
        final String DELIMITER = ",";
        Map<String, Integer> csvSum = stream
                .map(e -> e.split(","))
                .collect(Collectors.groupingBy(e -> e[0], Collectors.summingInt(e -> Integer.valueOf(e[1]))));
        int supply = csvSum.values().stream().max(Integer::compare).get();
        int buy = csvSum.values().stream().min(Integer::compare).get();
        int result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(DELIMITER).append(supply).append(System.lineSeparator())
                .append(BUY).append(DELIMITER).append(buy).append(System.lineSeparator())
                .append(RESULT).append(DELIMITER).append(result);
        return stringBuilder.toString();
    }
}
