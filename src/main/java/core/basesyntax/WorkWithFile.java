package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final int OPERAION_TYPE_POINT = 0;
    private static final int AMMOUNT_POINT = 1;
    private static final String LINE_DELIMITER = System.lineSeparator();
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String inputString = readFile(fromFileName);
        String report = createReport(inputString);
        writeFile(report,toFileName);
    }

    private String readFile(String fromFileName) {
        File file = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
            String inputString = stringBuilder.toString();
            return inputString;
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file",e);
        }
    }

    private String createReport(String inputString) {
        int summOfSupply = 0;
        int summOfBuy = 0;
        String[] resultOfOperation = inputString.split(" ");
        for (int i = 0; i < resultOfOperation.length; i++) {
            String[] dataSplited = resultOfOperation[i].split(DELIMITER);
            if (dataSplited[OPERAION_TYPE_POINT].equals(SUPPLY)) {
                summOfSupply += Integer.valueOf(dataSplited[1]);
            }
            if (dataSplited[OPERAION_TYPE_POINT].equals(BUY)) {
                summOfBuy += Integer.valueOf(dataSplited[AMMOUNT_POINT]);
            }
        }
        int result = summOfSupply - summOfBuy;
        StringBuilder stringBuilderReport = new StringBuilder();
        stringBuilderReport.append(SUPPLY)
                .append(DELIMITER)
                .append(summOfSupply)
                .append(LINE_DELIMITER)
                .append(BUY)
                .append(DELIMITER)
                .append(summOfBuy)
                .append(LINE_DELIMITER)
                .append(RESULT)
                .append(DELIMITER)
                .append(result);
        String report = stringBuilderReport.toString();
        System.out.println(report);
        return report;
    }

    private void writeFile(String report, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file" + toFileName,e);
        }
    }
}

