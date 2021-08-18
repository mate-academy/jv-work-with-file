package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_SUPPLY = "supply";

    private static String createReport(String[] dataArray) {
        int amountBuy = 0;
        int amountSupply = 0;
        for (int i = 0; i <= dataArray.length - 2; i += 2) {
            if (OPERATION_BUY.equals(dataArray[i])) {
                amountBuy += Integer.parseInt(dataArray[i + 1]);
            } else {
                amountSupply += Integer.parseInt(dataArray[i + 1]);
            }
        }
        return OPERATION_SUPPLY + "," + amountSupply
                + System.lineSeparator() + OPERATION_BUY + ","
                + amountBuy + System.lineSeparator()
                + "result" + "," + (amountSupply - amountBuy);
    }

    private void writeToFile(String toFileName, String report) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write(report);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        String inputData;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder builder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(",");
                value = reader.readLine();
            }
            inputData = builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        writeToFile(toFileName, createReport(inputData.split(",")));
    }
}
