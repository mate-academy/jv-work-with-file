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

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    public String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                String[] matchLines = readLine.split("\\W+");
                for (String word : matchLines) {
                    stringBuilder.append(word).append(" ");
                }
                readLine = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("file" + fromFileName + "doesn't exist" + e);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file " + fromFileName + " " + e);
        }
        return stringBuilder.toString();
    }

    public String createReport(String report) {
        StringBuilder stringBuilder = new StringBuilder();

        String[] parts = report.split("\\W+");
        String[] names = new String[parts.length / STEP_SIZE];
        int[] sums = new int[parts.length / STEP_SIZE];

        int size = CONST_VALUE;

        for (int i = 0; i < parts.length; i += STEP_SIZE) {
            String name = parts[i];
            int value = Integer.parseInt(parts[i + 1]);

            boolean nameFound = false;
            for (int j = CONST_VALUE; j < size; j++) {
                if (names[j] != null && names[j].equals(name)) {
                    sums[j] += value;
                    nameFound = true;
                    break;
                }
            }
            if (!nameFound) {
                names[size] = name;
                sums[size] = value;
                size++;
            }
        }
        for (int i = CONST_VALUE; i < sums.length; i++) {
            String contName = names[i];
            int contSum = sums[i];
            for (int j = i + 1; j < sums.length; j++) {
                if (sums[i] < sums[j]) {
                    names[i] = names[j];
                    names[j] = contName;

                    sums[i] = sums[j];
                    sums[j] = contSum;
                }
            }
        }

        for (int i = CONST_VALUE; i < size; i++) {
            stringBuilder.append(names[i]).append(",").append(sums[i])
                    .append(System.lineSeparator());
        }

        int sumResult = sums[CONST_VALUE];

        for (int i = 1; i < sums.length; i++) {
            sumResult -= sums[i];
        }
        stringBuilder.append("result,").append(sumResult);

        return stringBuilder.toString();
    }

    public void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file " + toFileName + " " + e);
        }
    }
}
