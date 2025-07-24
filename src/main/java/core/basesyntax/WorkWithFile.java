package core.basesyntax;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WorkWithFile {

    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        List<Transaction> transactions = loadTransactions(fromFileName);
        Map<String, Integer> report = aggregate(transactions);
        writeReport(report, Path.of(toFileName));
    }

    private Map<String, Integer> aggregate(List<Transaction> transactions) {
        Map<String, Integer> sums = transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::type,
                        Collectors.summingInt(Transaction::amount)
                ));

        int supplySum = sums.getOrDefault(SUPPLY, 0);
        int buySum = sums.getOrDefault(BUY, 0);

        // 2) Now build a LinkedHashMap in the exact order you need
        Map<String, Integer> ordered = new LinkedHashMap<>();
        ordered.put(SUPPLY, supplySum);
        ordered.put(BUY, buySum);
        ordered.put(RESULT, supplySum - buySum);

        return ordered;
    }

    void writeReport(Map<String, Integer> groupedReportData, Path outputFile) {
        try (BufferedWriter writer = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8)) {
            for (Map.Entry<String, Integer> entry : groupedReportData.entrySet()) {
                writer.write(entry.getKey());
                writer.write(",");
                writer.write(entry.getValue().toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Problem occurred while writing report", e);
        }
    }

    static List<Transaction> loadTransactions(String inputFile) {
        try {
            return Files.readAllLines(Path.of(inputFile)).stream().map(Transaction::parse).toList();
        } catch (IOException e) {
            throw new RuntimeException("Unable to read data from report file: " + inputFile, e);
        }
    }

    record Transaction(String type, int amount) {
        static Transaction parse(String line) {
            var parts = line.split(",");
            return new Transaction(parts[0], Integer.parseInt(parts[1]));
        }
    }
}
