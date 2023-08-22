package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String readString = readFile(fromFileName);
        int[] sums = createReport(readString);
        String statistic = generateStatistic(sums[0], sums[1]);
        writeToFile(toFileName, statistic);
    }

    private int[] createReport(String readString) {
        if (!readString.isEmpty()) {
            int[] sums = new int[2];

            String[] stringInput = readString.split("\\W+");
            for (int i = 0; i < stringInput.length / 2; i++) {
                if (stringInput[i * 2].equals("supply")) {
                    sums[0] += Integer.parseInt(stringInput[i * 2 + 1]);
                } else if (stringInput[i * 2].equals("buy")) {
                    sums[1] += Integer.parseInt(stringInput[i * 2 + 1]);
                }
            }
            return sums;
        }
        return new int[0];
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

    private String generateStatistic(int supplySum, int buySum) {
        StringBuilder generatedStatistic = new StringBuilder();
        generatedStatistic.append("supply,")
                .append(supplySum)
                .append("\nbuy,")
                .append(buySum)
                .append("\nresult,")
                .append(supplySum - buySum);
        return generatedStatistic.toString();
    }

    private void writeToFile(String toFileName, String result) {
        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            fileWriter.write(String.valueOf(result));
        } catch (IOException e) {
            throw new RuntimeException("cant write to file", e);
        }
    }
}
