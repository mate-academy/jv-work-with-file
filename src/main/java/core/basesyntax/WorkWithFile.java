package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String data = readInputFile(fromFileName);
        String report = "";
        if (!data.isEmpty()) {
            report += calculateStatistic(data);
        }
        if (!report.isEmpty()) {
            writeReportToFile(report, toFileName);
        }
    }

    private String readInputFile(String pathToFile) {
        File file = new File(pathToFile);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            if (line == null) {
                return "";
            }
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + file, e);
        }
    }

    private String calculateStatistic(String inputString) {
        String[] arrayData = inputString.split(System.lineSeparator());
        int suppliesCount = 0;
        int buyCount = 0;
        for (String simpleLine : arrayData) {
            String[] lineToArray = simpleLine.split(",");
            if (lineToArray[0].equals("supply")) {
                suppliesCount += Integer.parseInt(lineToArray[1]);
            } else {
                buyCount += Integer.parseInt(lineToArray[1]);
            }
        }
        return "supply," + suppliesCount + System.lineSeparator()
                + "buy," + buyCount + System.lineSeparator()
                + "result," + (suppliesCount - buyCount);
    }

    private void writeReportToFile(String reportToWrite, String pathToFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathToFile))) {
            bufferedWriter.write(reportToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file" + e);
        }
    }
}
