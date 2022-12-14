package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(makeReport(readFromFile(fromFileName)), toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(",");
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + " " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String makeReport(String dataFromFile) {
        int totalSupply = 0;
        int totalBuy = 0;
        StringBuilder reportBuilder = new StringBuilder();
        String[] splittedData = dataFromFile.split(",");
        for (int i = 0; i < splittedData.length; i += 2) {
            if (splittedData[i].equals("supply")) {
                totalSupply += Integer.parseInt(splittedData[i + 1]);
            } else if (splittedData[i].equals("buy")) {
                totalBuy += Integer.parseInt(splittedData[i + 1]);
            }
        }
        return reportBuilder.append("supply" + ",")
                .append(totalSupply)
                .append(System.lineSeparator())
                .append("buy" + ",")
                .append(totalBuy)
                .append(System.lineSeparator())
                .append("result" + ",")
                .append(totalSupply - totalBuy).toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter dataWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            dataWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + " " + toFileName, e);
        }
    }
}
