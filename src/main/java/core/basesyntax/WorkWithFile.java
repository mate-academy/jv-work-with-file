package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(createReport(readFromFile(fromFileName)), toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String string = bufferedReader.readLine();
            while (string != null) {
                stringBuilder.append(string).append(COMMA);
                string = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String createReport(String infoFromFile) {
        int sumSupply = 0;
        int sumBuy = 0;
        StringBuilder reportBuilder = new StringBuilder();
        String[] splittedInfo = infoFromFile.split(COMMA);
        for (int i = 0; i < splittedInfo.length; i += 2) {
            if (splittedInfo[i].equals("supply")) {
                sumSupply += Integer.parseInt(splittedInfo[i + 1]);
            } else if (splittedInfo[i].equals("buy")) {
                sumBuy += Integer.parseInt(splittedInfo[i + 1]);
            }
        }
        return reportBuilder.append("supply" + ",")
                .append(sumSupply)
                .append(System.lineSeparator())
                .append("buy" + ",")
                .append(sumBuy)
                .append(System.lineSeparator())
                .append("result" + ",")
                .append(sumSupply - sumBuy).toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter infoWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            infoWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
