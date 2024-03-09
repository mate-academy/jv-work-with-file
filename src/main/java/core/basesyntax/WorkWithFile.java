package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DATA_SPLITTER = " ";
    private static final String COMMA_SPLITTER = ",";
    private static final int NAME_INDEX = 0;
    private static final int COUNT_INDEX = 1;
    private static final String SEPARATOR = System.lineSeparator();
    private static final int SUPLLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String report = processData(fromFileName);
        writeReportToFile(report, toFileName);
    }

    private String createReport(String supplyCount, String buyCount) {
        StringBuilder report = new StringBuilder();
        int result = Integer.parseInt(supplyCount) - Integer.parseInt(buyCount);
        report.append(SUPPLY).append(COMMA_SPLITTER).append(supplyCount).append(SEPARATOR);
        report.append(BUY).append(COMMA_SPLITTER).append(buyCount).append(SEPARATOR);
        report.append(RESULT).append(COMMA_SPLITTER)
                .append(result).append(SEPARATOR);
        return report.toString();
    }

    private String readFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String input;
            while ((input = bufferedReader.readLine()) != null) {
                stringBuilder.append(input).append(DATA_SPLITTER);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + fileName, e);
        }
        return stringBuilder.toString();
    }

    public String processData(String fromFileName) {
        String fileContent = readFile(fromFileName);
        String[] statistic = fileContent.split(DATA_SPLITTER);
        StringBuilder report = new StringBuilder();
        String[] names = new String[statistic.length];
        String[] number = new String[statistic.length];
        for (int i = 0; i < statistic.length; i++) {
            String[] current = statistic[i].split(COMMA_SPLITTER);
            names[i] = current[NAME_INDEX];
            number[i] = current[COUNT_INDEX];
        }

        int countSupply = 0;
        int countBuy = 0;
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(SUPPLY)) {
                countSupply += Integer.parseInt(number[i]);
            } else {
                countBuy += Integer.parseInt(number[i]);
            }
        }
        report.append(countSupply).append(DATA_SPLITTER).append(countBuy);
        return report.toString();
    }

    public void writeReportToFile(String report, String fileName) {
        File file = new File(fileName);
        String[] splitReport = report.split(DATA_SPLITTER);
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(createReport(splitReport[SUPLLY_INDEX],
                    splitReport[BUY_INDEX]));

        } catch (IOException e) {
            throw new RuntimeException("Can't write in file " + fileName, e);
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("Can't close file " + fileName, e);
            }
        }
    }
}
