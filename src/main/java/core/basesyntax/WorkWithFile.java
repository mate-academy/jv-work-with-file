package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int CONST_VALUE = 0;
    private static final int STEP_SIZE = 2;
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    public String readFile(String fromFileName) {
        StringBuilder fileContents = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                String[] matchLines = readLine.split("\\W+");
                for (String word : matchLines) {
                    fileContents.append(word).append(" ");
                }
                readLine = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("file" + fromFileName + "doesn't exist", e);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file " + fromFileName, e);
        }
        return fileContents.toString();
    }

    public String createReport(String report) {
        int supplyCounter = CONST_VALUE;
        int buyCounter = CONST_VALUE;

        String[] parts = report.split("\\W+");

        for (int i = CONST_VALUE; i < parts.length; i += STEP_SIZE) {
            String operation = parts[i];
            int value = Integer.parseInt(parts[i + 1]);

            switch (operation) {
                case SUPPLY_OPERATION:
                    supplyCounter += value;
                    break;
                case BUY_OPERATION:
                    buyCounter += value;
                    break;
                default:
            }
        }

        int result = supplyCounter - buyCounter;

        return "supply," + supplyCounter + System.lineSeparator()
                + "buy," + buyCounter + System.lineSeparator()
                + "result," + result;
    }

    public void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file " + toFileName, e);
        }
    }
}
