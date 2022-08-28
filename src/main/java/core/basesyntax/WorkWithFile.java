package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final int OPERATION_TYPE = 0;
    private static final int AMMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private List<String> readFromFile(String fromFileName) {
        try {
            return Files.readAllLines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file", e);
        }
    }

    private void writeToFile(String toFileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Couldn`t write data to file", e);
        }
    }

    private String createReport(List<String> data) {
        int totalSupply = 0;
        int totalBuy = 0;
        for (String line: data) {
            String[] split = line.split(",");
            if (split[OPERATION_TYPE].equals("supply")) {
                totalSupply += Integer.parseInt(split[AMMOUNT]);
            } else {
                totalBuy += Integer.parseInt(split[AMMOUNT]);
            }
        }
        return "supply," + totalSupply + System.lineSeparator() + "buy," + totalBuy
                + System.lineSeparator() + "result," + (totalSupply - totalBuy);
    }
}
