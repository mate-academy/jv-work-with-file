package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        saveDataToFile(toFileName, readFile(fromFileName));
    }

    private int[] readFile(String fileInput) {
        int[] result = new int[2];
        String sourseLine;
        String[] sourseLineArr;
        File inputFile = new File(fileInput);
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            sourseLine = reader.readLine();
            while (sourseLine != null) {
                sourseLineArr = sourseLine.split(",");
                if (sourseLineArr[0].equals("supply")) {
                    result[0] += Integer.parseInt(sourseLineArr[1]);
                } else if (sourseLineArr[0].equals("buy")) {

                    result[1] += Integer.parseInt(sourseLineArr[1]);
                }
                sourseLine = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(" Can`t open file " + fileInput, e);
        } catch (IOException e) {
            throw new RuntimeException(" Can`t read file" + fileInput, e);
        }
        return result;
    }

    private void saveDataToFile(String outputFile, int[] data) {
        File outFile = new File(outputFile);
        StringBuilder outStringBuilder = new StringBuilder();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            writer.write(outStringBuilder.append("supply,")
                    .append(data[0]).append(System.lineSeparator())
                    .append("buy,").append(data[1])
                    .append(System.lineSeparator())
                    .append("result,").append(data[0] - data[1])
                    .append(System.lineSeparator()).toString());
        } catch (IOException e) {
            throw new RuntimeException(" Can`t create file" + outputFile, e);
        }

    }
}
