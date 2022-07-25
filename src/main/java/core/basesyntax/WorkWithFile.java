package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    //services in daily report
    private static final String SERVICE_SUPPLY = "supply";
    private static final String SERVICE_BUY = "buy";
    private static final String SERVICE_RESULT = "result";
    //daily report line structure
    private static final int NUMBER_OF_COLUMNS = 2;
    private static final int COLUMN_WITH_NAME = 0;
    private static final int COLUMN_WITH_SUM = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = getStringFromFile(fromFileName);
        String report = makeReport(dataFromFile);
        writeReportToFile(report, toFileName);
    }

    //read file, return array of Strings from the file
    //each of the elements of array is a line in a fromFileName
    private String[] getStringFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        File fileFrom = new File(fromFileName);

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileFrom));
            String lineFromFile = bufferedReader.readLine();
            while (lineFromFile != null) {
                stringBuilder.append(lineFromFile).append(" ");
                lineFromFile = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fileFrom, e);
        }
        //return array of Strings, each of the elements is a line in a file
        return stringBuilder.toString().split(" ");
    }

    private String makeReport(String[] dataFromFile) {
        int dailySupply = 0;
        int dailyBuy = 0;
        int dailyResult = 0;
        String[] lineFromArray = new String[NUMBER_OF_COLUMNS];

        for (String data : dataFromFile) {
            lineFromArray = data.split(",");
            if (lineFromArray[COLUMN_WITH_NAME].equals(SERVICE_SUPPLY)) {
                dailySupply += Integer.parseInt(lineFromArray[COLUMN_WITH_SUM]);
            } else if (lineFromArray[COLUMN_WITH_NAME].equals(SERVICE_BUY)) {
                dailyBuy += Integer.parseInt(lineFromArray[COLUMN_WITH_SUM]);
            }
        }
        dailyResult = dailySupply - dailyBuy;

        //collect all report information to stringBuilder
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SERVICE_SUPPLY).append(",").append(dailySupply)
                .append(System.lineSeparator())
                .append(SERVICE_BUY).append(",").append(dailyBuy)
                .append(System.lineSeparator())
                .append(SERVICE_RESULT).append(",").append(dailyResult);

        //return String with the report
        return stringBuilder.toString();
    }

    //write to toFileName info from the String report
    private void writeReportToFile(String report, String toFileName) {
        File fileTo = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTo, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + fileTo, e);
        }
    }
}
