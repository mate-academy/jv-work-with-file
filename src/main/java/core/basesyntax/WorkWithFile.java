package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        final String readString = readFile(fromFileName);
        final String statistic = createReport(readString);
        writeToFile(toFileName, statistic);
    }

    private String createReport(String readString) {
        int supplySum = 0;
        int buySum = 0;

        String[] stringInput = readString.split("\\W+");
        for (int i = 0; i < stringInput.length / 2; i++) {
            if (stringInput[i * 2].equals("supply")) {
                supplySum += Integer.parseInt(stringInput[i * 2 + 1]);
            } else if (stringInput[i * 2].equals("buy")) {
                buySum += Integer.parseInt(stringInput[i * 2 + 1]);
            }
        }

        StringBuilder generatedStatistic = new StringBuilder();
        generatedStatistic.append("supply,")
                .append(supplySum)
                .append("\nbuy,")
                .append(buySum)
                .append("\nresult,")
                .append(supplySum - buySum);

        return generatedStatistic.toString();
    }

    private String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(" ");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("file not found", e);
        } catch (IOException e) {
            throw new RuntimeException("cant read file", e);
        }
        return stringBuilder.toString();
    }

    private void writeToFile(String toFileName, String result) {
        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            fileWriter.write(String.valueOf(result));
        } catch (IOException e) {
            throw new RuntimeException("cant write to file", e);
        }
    }
}
