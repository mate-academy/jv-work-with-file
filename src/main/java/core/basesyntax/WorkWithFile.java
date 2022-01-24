package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String report = readFile(fromFileName);
        writeToFile(toFileName, report);
    }

    private void writeToFile(String fileToWrite, String dataToBeWritten) {
        File resultedInfo = new File(fileToWrite);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultedInfo))) {
            writer.write(dataToBeWritten);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    private String readFile(String incomeFile) {
        int buySum = 0;
        int supplySum = 0;
        File incomeInfo = new File(incomeFile);
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(incomeInfo))) {
            line = reader.readLine();
            while (line != null) {
                if (line.split(",")[0].equals("supply")) {
                    supplySum += Integer.parseInt(line.split(",")[1]);
                }
                if (line.split(",")[0].equals("buy")) {
                    buySum += Integer.parseInt(line.split(",")[1]);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file", e);
        }
        StringBuilder builder = new StringBuilder()
                .append("supply,").append(supplySum).append(System.lineSeparator())
                .append("buy,").append(buySum).append(System.lineSeparator())
                .append("result,").append(supplySum - buySum);
        return builder.toString();
    }
}
