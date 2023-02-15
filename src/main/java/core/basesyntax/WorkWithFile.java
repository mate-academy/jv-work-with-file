package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String data = readData(fromFileName);
        String report = createReport(data);
        writeData(report, toFileName);
    }

    private String readData(String fromFileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder data = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null && !line.isEmpty()) {
                data.append(line).append(" ");
                line = bufferedReader.readLine();
            }
            return data.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File doesn't exist", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file", e);
        }
    }

    private void writeData(String data, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File doesn't exist", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }

    private String createReport(String data) {
        int countSupply = 0;
        int countBuy = 0;
        StringBuilder result = new StringBuilder();
        String[] lines = data.split(" ");
        for (String line : lines) {
            String[] spliter = line.split(",");
            if (spliter[0].equals("supply")) {
                countSupply += Integer.parseInt(spliter[1]);
            } else if (spliter[0].equals("buy")) {
                countBuy += Integer.parseInt(spliter[1]);
            }
        }
        int countedResult = countSupply - countBuy;
        result.append("supply").append(",").append(countSupply).append(System.lineSeparator());
        result.append("buy").append(",").append(countBuy).append(System.lineSeparator());
        result.append("result").append(",").append(countedResult);
        return result.toString();
    }
}
