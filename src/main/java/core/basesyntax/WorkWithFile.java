package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int NUMBER_OF_COLUMNS = 2;
    private static final int COLUMN_WITH_NAME = 0;
    private static final int COLUMN_WITH_SUM = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File fileFrom = new File(fromFileName);
        //split by "," lines from fromFileName to String
        String[] lineSeparator = new String[NUMBER_OF_COLUMNS];

        //will count daily sums of supplies and buys to write it later to toFileName
        int dailySupply = 0;
        int dailyBuy = 0;
        int dailyResult = 0;

        //reading from file fromFileName, split lines, count sums of supply, buy, result
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileFrom));
            String lineInFromFile = bufferedReader.readLine();
            System.out.println(lineInFromFile);
            while (lineInFromFile != null) {
                lineSeparator = lineInFromFile.split(",");
                if (lineSeparator[COLUMN_WITH_NAME].equals("supply")) {
                    dailySupply += Integer.parseInt(lineSeparator[COLUMN_WITH_SUM]);
                } else if (lineSeparator[COLUMN_WITH_NAME].equals("buy")) {
                    dailyBuy += Integer.parseInt(lineSeparator[COLUMN_WITH_SUM]);
                }
                lineInFromFile = bufferedReader.readLine();
                System.out.println(lineInFromFile);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        dailyResult = dailySupply - dailyBuy;

        //collect all information to stringBuilder before write it to toFileName
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(dailySupply).append(System.lineSeparator())
                .append("buy,").append(dailyBuy).append(System.lineSeparator())
                .append("result,").append(dailyResult);

        //write report information to file toFileName
        File fileTo = new File(toFileName);
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(fileTo, true));
            bufferedWriter.write(stringBuilder.toString());

        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can't close the file", e);
                }
            }
        }
    }
}
