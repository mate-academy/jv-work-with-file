package core.basesyntax;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
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
        final String Supply = "supply";
        final String Buy = "buy";
        final String Result = "result";
        final String Delimiter = ",";
        Map<String, Integer> csvSum = stream
                .map(e -> e.split(","))
                .collect(Collectors.groupingBy(e -> e[0],
                        Collectors.summingInt(e -> Integer.valueOf(e[1]))));
        int supply = csvSum.values().stream().max(Integer::compare).get();
        int buy = csvSum.values().stream().min(Integer::compare).get();
        int result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Supply).append(Delimiter).append(supply).append(System.lineSeparator())
                .append(Buy).append(Delimiter).append(buy).append(System.lineSeparator())
                .append(Result).append(Delimiter).append(result);
        return stringBuilder.toString();
    }
}
