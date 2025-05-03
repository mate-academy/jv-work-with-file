package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File from = new File(fromFileName);
        File to = new File(toFileName);
        String fileData = fileReader(from);
        String[] rows = fileData.split(System.lineSeparator());
        int supplyValue = 0;
        int buyValue = 0;
        for (String row : rows) {
            String[] values = row.split(",");
            if (values[0].equals("supply")) {
                supplyValue += Integer.parseInt(values[1]);
            } else if (values[0].equals("buy")) {
                buyValue += Integer.parseInt(values[1]);
            } else {
                throw new RuntimeException("Wrong name value " + values[0]);
            }
        }
        String report = "supply," + supplyValue + System.lineSeparator()
                + "buy," + buyValue + System.lineSeparator()
                + "result," + (supplyValue - buyValue);
        fileWriter(to, report);
    }

    private String fileReader(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file " + file.getName());
        }
        return stringBuilder.toString();
    }

    private void fileWriter(File file, String input) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (String line : input.split(System.lineSeparator())) {
                bufferedWriter.write(line + System.lineSeparator());
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file " + file);
        }
    }
}
