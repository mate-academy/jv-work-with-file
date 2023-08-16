package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final int INDEX_OF_OPERATION = 0;
    private static final int INDEX_OF_NUMBER = 1;
    private static final String BUY_OPERATION = "buy";
    private static final String SUPPLY_OPERATION = "supply";

    public String[] readFromFile(String fromFileName) {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = fileReader.readLine();
            while (line != null) {
                lines.add(line);
                line = fileReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error occurred while reading from file");
        }
        return lines.toArray(new String[0]);
    }

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyCount = 0;
        int buyCount = 0;
        String[] lines = readFromFile(fromFileName);
        for (String line : lines) {
            String[] splited = line.split(SEPARATOR);
            if (splited[INDEX_OF_OPERATION].equals(SUPPLY_OPERATION)) {
                supplyCount += Integer.parseInt(splited[INDEX_OF_NUMBER]);
            } else if (splited[INDEX_OF_OPERATION].equals(BUY_OPERATION)) {
                buyCount += Integer.parseInt(splited[INDEX_OF_NUMBER]);
            }
        }
        StringBuilder report = new StringBuilder();
        report.append("supply,").append(supplyCount).append(System.lineSeparator())
                .append("buy,").append(buyCount).append(System.lineSeparator())
                .append("result,").append(supplyCount - buyCount);
        writeToFile(toFileName, report.toString());
    }

    private static void writeToFile(String toFileName, String message) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(toFileName))) {
            fileWriter.write(message);
        } catch (IOException e) {
            System.out.println("Error occurred while writing to file");
        }
    }
}
