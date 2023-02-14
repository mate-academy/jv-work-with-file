package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readData(fromFileName);
        String report = createReport(data);
        writeData(report, toFileName);
    }

    private String readData(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder data = new StringBuilder();
            String line = reader.readLine();
            while (line != null && !line.isEmpty()) {
                data.append(line).append(" ");
                line = reader.readLine();
            }
            return data.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("There is no such file " + fileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
    }

    private void writeData(String data, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }

    private String createReport(String data) {
        StringBuilder report = new StringBuilder();
        int totalSupply = 0;
        int totalBuy = 0;
        String[] info = data.split(" ");
        for (String line : info) {
            String[] array = line.split(",");
            if (array[0].equals(SUPPLY)) {
                totalSupply += Integer.parseInt(array[1]);
            } else if (array[0].equals(BUY)) {
                totalBuy += Integer.parseInt(array[1]);
            }
        }
        int result = totalSupply - totalBuy;
        report.append(SUPPLY).append(",").append(totalSupply).append(System.lineSeparator());
        report.append(BUY).append(",").append(totalBuy).append(System.lineSeparator());
        report.append(RESULT).append(",").append(result);
        return report.toString();
    }
}
