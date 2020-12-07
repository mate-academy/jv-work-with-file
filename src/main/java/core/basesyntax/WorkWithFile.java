package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class WorkWithFile {

    private static final int NAME_INDEX = 0;
    private static final int NUMBER_INDEX = 1;
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(readFromFile(fromFileName), toFileName);
    }

    private Report readFromFile(String fileName) {
        Report report = new Report();
        int sumBuy = 0;
        int sumSupply = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] readFile = line.split(" ");
                for (String s : readFile) {
                    String[] dividedInfo = s.split(SEPARATOR);
                    if (dividedInfo[NAME_INDEX].equals("buy")) {
                        sumBuy += Integer.parseInt(dividedInfo[NUMBER_INDEX]);
                    } else {
                        sumSupply += Integer.parseInt(dividedInfo[NUMBER_INDEX]);
                    }
                }
            }
            report.setSumBuy(sumBuy);
            report.setSumSupply(sumSupply);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file!", e);
        }
        return report;
    }

    private void writeToFile(Report report, String toFileName) {
        try (PrintWriter writer = new PrintWriter(toFileName)) {
            StringBuilder writeInfo = new StringBuilder();
            writeInfo.append("supply")
                    .append(SEPARATOR)
                    .append(report.getSumSupply())
                    .append(System.lineSeparator());

            writeInfo.append("buy")
                    .append(SEPARATOR)
                    .append(report.getSumBuy())
                    .append(System.lineSeparator());

            writeInfo.append("result")
                    .append(SEPARATOR)
                    .append(report.getResult())
                    .append(System.lineSeparator());

            writer.write(writeInfo.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file!", e);
        }
    }
}
