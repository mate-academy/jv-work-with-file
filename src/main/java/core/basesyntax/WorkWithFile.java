package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(readFromFile(fromFileName), toFileName);
    }

    private Report readFromFile(String fileName) {
        Report report = new Report();
        int sumBuy = 0;
        int sumSupply = 0;
        int result;
        String line;
        StringBuilder dataFromFile = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((line = reader.readLine()) != null) {
                dataFromFile.append(line);
                dataFromFile.append(" ");
            }
            String[] readFile = dataFromFile.toString().split(" ");
            for (int i = 0; i < readFile.length; i++) {
                String[] dividedInfo = readFile[i].split(",", 2);
                if (dividedInfo[0].equals("buy")) {
                    sumBuy += Integer.parseInt(dividedInfo[1]);
                } else {
                    sumSupply += Integer.parseInt(dividedInfo[1]);
                }
            }
            report.setSumBuy(sumBuy);
            report.setSumSupply(sumSupply);
            result = sumSupply - sumBuy;
            report.setResult(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file!", e);
        }
        return report;
    }

    private void writeToFile(Report report, String toFileName) {
        try (PrintWriter writer = new PrintWriter(toFileName)) {
            StringBuilder writeInfo = new StringBuilder();
            writeInfo.append("supply")
                    .append(',')
                    .append(report.getSumSupply())
                    .append(System.lineSeparator());

            writeInfo.append("buy")
                    .append(',')
                    .append(report.getSumBuy())
                    .append(System.lineSeparator());

            writeInfo.append("result")
                    .append(',')
                    .append(report.getResult())
                    .append(System.lineSeparator());

            writer.write(writeInfo.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file!", e);
        }
    }
}
