package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SUPPLY_KEYWORD = "supply";
    public static final String BUY_KEYWORD = "buy";
    public static final int KEYWORD_INDEX = 0;
    public static final int COUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        BufferedReader bufferedReader = null;
        StringBuilder builder = new StringBuilder();

        try {
            bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String text = bufferedReader.readLine();

            int result = 0;
            int supply = 0;
            int buy = 0;

            while (text != null) {
                String[] split = text.split("\\W+");
                if (split[KEYWORD_INDEX].equals(SUPPLY_KEYWORD)) {
                    supply = supply + Integer.parseInt(split[COUNT_INDEX]);
                } else if (split[KEYWORD_INDEX].equals(BUY_KEYWORD)) {
                    buy = buy + Integer.parseInt(split[COUNT_INDEX]);
                }
                result = supply - buy;
                text = bufferedReader.readLine();
            }
            builder.append("supply,").append(supply).append(System.lineSeparator())
                    .append("buy,").append(buy).append(System.lineSeparator())
                    .append("result,").append(result);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data to file", e);
        }

        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true));
            bufferedWriter.write(builder.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }
    }
}
