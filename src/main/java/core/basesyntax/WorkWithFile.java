package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class WorkWithFile {
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

    private String[] createReport(ArrayList<String> operationsList) {
        int supplyOperations = 0;
        int buyOperations = 0;

        for (String operation : operationsList) {
            String[] partsOfOperation = operation.split(",");
            String type = partsOfOperation[0];
            int quantity = Integer.parseInt(partsOfOperation[1]);

            if (type.equals("buy")) {
                buyOperations += quantity;
            } else {
                supplyOperations += quantity;
            }
        }

        return new String[]{
                "supply," + supplyOperations,
                "buy," + buyOperations,
                "result," + (supplyOperations - buyOperations)
        };
    }

    private void writeDataToFile(String fileName, String[] operations) {
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create a file: ", e);
        }

        try {
            String joinedOperations = String.join(System.lineSeparator(), operations);
            Files.write(file.toPath(), joinedOperations.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file: ", e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        ArrayList<String> data = readFile(fromFileName);
        String[] operations = createReport(data);
        writeDataToFile(toFileName, operations);
    }
}
