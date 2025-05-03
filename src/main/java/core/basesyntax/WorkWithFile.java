package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class WorkWithFile {
    private static final String SUPPLY_STR = "supply";
    private static final String BUY_STR = "buy";
    private static final String RESULT_STR = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        ArrayList<String> data = readFile(fromFileName);
        String operations = createReport(data);
        writeDataToFile(toFileName, operations);
    }

    private ArrayList<String> readFile(String fileName) {
        File fileToRead = new File(fileName);

        try (BufferedReader br = new BufferedReader(new FileReader(fileToRead))) {
            ArrayList<String> operationsList = new ArrayList<>();
            String line;

            while ((line = br.readLine()) != null) {
                operationsList.add(line);
            }

            return operationsList;
        } catch (IOException e) {
            throw new RuntimeException("File not found: ", e);
        }
    }

    private String createReport(ArrayList<String> operationsList) {

        int supplyOperations = 0;
        int buyOperations = 0;
        StringBuilder builder = new StringBuilder();

        for (String operation : operationsList) {
            String[] partsOfOperation = operation.split(COMMA);
            String type = partsOfOperation[0];
            int quantity = Integer.parseInt(partsOfOperation[1]);

            if (type.equals(BUY_STR)) {
                buyOperations += quantity;
            } else {
                supplyOperations += quantity;
            }
        }

        builder.append(SUPPLY_STR)
                .append(COMMA)
                .append(supplyOperations)
                .append(System.lineSeparator());
        builder.append(BUY_STR)
                .append(COMMA)
                .append(buyOperations)
                .append(System.lineSeparator());
        builder.append(RESULT_STR)
                .append(COMMA)
                .append(supplyOperations - buyOperations)
                .append(System.lineSeparator());

        return builder.toString();
    }

    private void writeDataToFile(String fileName, String operations) {
        try {
            Path path = Paths.get(fileName);
            Files.write(path, operations.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file: ", e);
        }
    }
}
