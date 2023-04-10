package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private final Map<String, List<Integer>> transactions = new HashMap<>();
    private final Map<String, String> results = new HashMap<>();

    public void getStatistic(String inputFileName, String outputFileName) {
        if (results.containsKey(inputFileName)) {
            writeToFile(outputFileName, results.get(inputFileName));
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {

            processTransactions(reader);

            int totalBuy = calculateTotal(BUY);
            int totalSupply = calculateTotal(SUPPLY);
            int result = totalSupply - totalBuy;

            StringBuilder resultString = new StringBuilder();
            resultString.append(SUPPLY).append(",").append(totalSupply).append("\n")
                    .append(BUY).append(",").append(totalBuy).append("\n")
                    .append(RESULT).append(",").append(result).append("\n");

            writer.write(resultString.toString());
            results.put(inputFileName, resultString.toString());

        } catch (IOException e) {
            throw new RuntimeException("Error processing file: " + inputFileName, e);
        }
    }

    private void processTransactions(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String type = parts[0];
            int amount = Integer.parseInt(parts[1]);
            if (!transactions.containsKey(type)) {
                transactions.put(type, new ArrayList<>());
            }
            transactions.get(type).add(amount);
        }
    }

    private int calculateTotal(String type) {
        List<Integer> amounts = transactions.get(type);
        if (amounts == null) {
            return 0;
        }
        return amounts.stream().mapToInt(Integer::intValue).sum();
    }

    private void writeToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + fileName, e);
        }
    }
}


