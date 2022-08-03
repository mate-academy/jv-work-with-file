package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SERVICE_SUPPLY = "supply";
    private static final String SERVICE_BUY = "buy";
    private static final String SERVICE_RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = getStringFromFile(fromFileName);
        String report = makeReport(dataFromFile);
        writeReportToFile(report, toFileName);
    }

    private String[] getStringFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        File fileFrom = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileFrom))) {
            String lineFromFile = bufferedReader.readLine();
            while (lineFromFile != null) {
                stringBuilder.append(lineFromFile).append(System.lineSeparator());
                lineFromFile = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fileFrom, e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    private String makeReport(String[] dataFromFile) {
        int totalSupply = 0;
        int totalBuy = 0;
        for (String data : dataFromFile) {
            String[] lineFromArray = data.split(COMMA);
            if (lineFromArray[0].equals(SERVICE_SUPPLY)) {
                totalSupply += Integer.parseInt(lineFromArray[1]);
            } else if (lineFromArray[0].equals(SERVICE_BUY)) {
                totalBuy += Integer.parseInt(lineFromArray[1]);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SERVICE_SUPPLY).append(COMMA).append(totalSupply)
                .append(System.lineSeparator())
                .append(SERVICE_BUY).append(COMMA).append(totalBuy)
                .append(System.lineSeparator())
                .append(SERVICE_RESULT).append(COMMA).append(totalSupply - totalBuy);
        return stringBuilder.toString();
    }

    private void writeReportToFile(String report, String toFileName) {
        File fileTo = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTo, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + fileTo, e);
        }
    }
}
