package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        readFile(fromFileName, toFileName);
        createReport(fromFileName, toFileName);
        writeToFile(fromFileName, toFileName);
    }

    private String readFile(String fromFileName, String toFileName) {
        int countSupply = 0;
        int countBuy = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] splitString = value.split(",");
                if (splitString[0].equals("supply")) {
                    countSupply += Integer.parseInt(splitString[1]);
                } else if (splitString[0].equals("buy")) {
                    countBuy += Integer.parseInt(splitString[1]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }
        return countSupply + "," + countBuy;
    }

    private String createReport(String fromFileName, String toFileName) {
        String[] split = readFile(fromFileName, toFileName).split(",");
        int result = Integer.parseInt(split[0]) - Integer.parseInt(split[1]);
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(Integer.parseInt(split[0]))
                .append(System.lineSeparator());
        builder.append("buy,").append(Integer.parseInt(split[1]))
        .append(System.lineSeparator());
        builder.append("result,").append(result);
        String report = builder.toString();
        return report;
    }

    private void writeToFile(String fromFileName, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(createReport(fromFileName, toFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
