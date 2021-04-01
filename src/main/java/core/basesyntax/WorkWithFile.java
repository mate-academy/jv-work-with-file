package core.basesyntax;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SPLIT_PATTERN = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int QUANTITY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> fileContent = readFile(fromFileName);
        writeDataToFile(analyzeFileContent(fileContent), toFileName);
    }

    private String analyzeFileContent(List<String> fileContent) {
        StringBuilder builder = new StringBuilder();

        Map<String, Integer> sumSupplyBuy = fileContent.stream()
                .map(data -> data.split(SPLIT_PATTERN))
                .collect(Collectors.groupingBy(st -> st[OPERATION_INDEX],
                        Collectors.summingInt(st -> Integer.parseInt(st[QUANTITY_INDEX]))));

        builder.append(SUPPLY).append(",").append(sumSupplyBuy.get(SUPPLY))
                .append(System.lineSeparator())
                .append(BUY).append(",").append(sumSupplyBuy.get(BUY))
                .append(System.lineSeparator())
                .append(RESULT).append(",")
                .append(sumSupplyBuy.get(SUPPLY) - sumSupplyBuy.get(BUY));

        return builder.toString();
    }

    private void writeDataToFile(String data, String toFileName) {
        try {
            Files.write(Path.of(toFileName), data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }

    private List<String> readFile(String fromFileName) {
        List<String> fileContent;
        try {
            fileContent = Files.readAllLines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
        return fileContent;
    }
}
