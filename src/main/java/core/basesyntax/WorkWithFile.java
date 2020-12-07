package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final int OPERATION = 0;
    private static final int AMOUNT = 1;
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, calculateReport(fromFileName));
    }

    private String calculateReport(String fileName) {
        StringBuilder builder = new StringBuilder();
        int buy = 0;
        int supply = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] token = line.split(DELIMITER);
                if (BUY.equals(token[OPERATION])) {
                    buy += Integer.parseInt(token[AMOUNT]);
                } else if (SUPPLY.equals(token[OPERATION])) {
                    supply += Integer.parseInt(token[AMOUNT]);
                }
            }
            builder.append("supply,").append(supply).append(System.lineSeparator());
            builder.append("buy,").append(buy).append(System.lineSeparator());
            builder.append("result,").append(supply - buy);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File with name: " + fileName + " not found");
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fileName + ", cause " + e);
        }
        return builder.toString();
    }

    private void writeToFile(String fileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File with name: " + fileName + " not found");
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + fileName + ", cause: " + e);
        }
    }
}
