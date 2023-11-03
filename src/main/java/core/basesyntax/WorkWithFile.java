package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String CSV_SEPARATOR = ",";
    private static final int OPERATION_TYPE = 0;
    private static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] fileInfo = readFile(fromFileName).split(System.lineSeparator());
        String report = createReport(fileInfo);
        writeFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        File fileWithInfo = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(fileWithInfo);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String info = bufferedReader.readLine();
            while (info != null) {
                stringBuilder.append(info).append(System.lineSeparator());
                info = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String createReport(String[] fileInfo) {
        String[] lineInfo;
        int numberOfSupply = 0;
        int numberOfBuy = 0;
        int totalNumber = 0;
        StringBuilder report = new StringBuilder();
        for (String info : fileInfo) {
            lineInfo = info.split(CSV_SEPARATOR);
            if (lineInfo[OPERATION_TYPE].equals(SUPPLY)) {
                numberOfSupply += Integer.parseInt(lineInfo[AMOUNT]);
            } else if (lineInfo[OPERATION_TYPE].equals(BUY)) {
                numberOfBuy += Integer.parseInt(lineInfo[AMOUNT]);
            }
        }
        totalNumber = numberOfSupply - numberOfBuy;
        report.append("supply," + numberOfSupply)
                .append(System.lineSeparator())
                .append("buy," + numberOfBuy)
                .append(System.lineSeparator())
                .append("result," + totalNumber);
        return report.toString();
    }

    private void writeFile(String report, String toFileName) {
        try (FileWriter fileWriter = new FileWriter(toFileName);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
