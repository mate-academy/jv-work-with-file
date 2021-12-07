package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int supply = 0;
    private int buy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        readFile(fromFileName);
        saveDataToFile(toFileName);
    }

    private void readFile(String fileInput) {
        String sourseLine;
        String[] sourseLineArr;
        File inputFile = new File(fileInput);
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            sourseLine = reader.readLine();
            while (sourseLine != null) {
                sourseLineArr = sourseLine.split(",");
                if (sourseLineArr[0].equals("supply")) {
                    supply += Integer.parseInt(sourseLineArr[1]);
                } else if (sourseLineArr[0].equals("buy")) {

                    buy += Integer.parseInt(sourseLineArr[1]);
                }
                sourseLine = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(" Can`t open file " + fileInput, e);
        } catch (IOException e) {
            throw new RuntimeException(" Can`t read file" + fileInput, e);
        }

    }

    private void saveDataToFile(String outputFile) {
        File outFile = new File(outputFile);
        StringBuilder outStringBuilder = new StringBuilder();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            writer.write(outStringBuilder.append("supply,")
                    .append(supply).append(System.lineSeparator())
                    .append("buy,").append(buy)
                    .append(System.lineSeparator())
                    .append("result,").append(supply - buy)
                    .append(System.lineSeparator()).toString());
        } catch (IOException e) {
            throw new RuntimeException(" Can`t create file" + outputFile, e);
        }

    }
}
