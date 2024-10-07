package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {

        writeResultFile(toFileName, readAndCalculatedFile(fromFileName));
    }

    private String readAndCalculatedFile(String fileName) {
        int[] results = new int[3];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] array = line.split(",");
                if (array.length == 2) {
                    String type = array[0].trim();
                    int value = Integer.parseInt(array[1].trim());

                    if (type.equals("supply")) {
                        results[0] += value;
                    } else if (type.equals("buy")) {
                        results[1] += value;
                    }
                }
            }

            results[2] = results[0] - results[1];
            return convertResult(results);

        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
    }

    private void writeResultFile(String toFile, String result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String convertResult(int[] array) {
        StringBuilder resultsBuilder = new StringBuilder();

        return resultsBuilder.append("supply,").append(array[0]).append(System.lineSeparator())
            .append("buy,").append(array[1]).append(System.lineSeparator())
            .append("result,").append(array[2]).toString();
    }
}
