package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String RESULT_WORD = "result";
    private static final String SUPPLY_WORD = "supply";
    private static final String BUY_WORD = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataArray = readFromFile(fromFileName);
        int valueSupply = 0;
        int valueBuy = 0;
        for (String s : dataArray) {
            String[] typeAndValue = s.split(",");
            if (typeAndValue[0].equals(BUY_WORD)) {
                valueBuy += Integer.parseInt(typeAndValue[1]);
            }
            if (typeAndValue[0].equals(SUPPLY_WORD)) {
                valueSupply += Integer.parseInt(typeAndValue[1]);
            }
        }
        int result = valueSupply - valueBuy;
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY_WORD).append(",").append(valueSupply).append(System.lineSeparator())
                .append(BUY_WORD).append(",").append(valueBuy).append(System.lineSeparator())
                .append(RESULT_WORD).append(",").append(result);
        writeToFile(builder.toString(), toFileName);
    }

    private void writeToFile(String result, String toFileName) {
        File file = new File(toFileName);
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(result);

        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException("Can't close the file", e);
            }
        }

    }

    public String[] readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String string = bufferedReader.readLine();
            while (string != null) {
                builder.append(string).append(System.lineSeparator());
                string = bufferedReader.readLine();
            }
            return builder.toString().split(System.lineSeparator());

        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file ", e);
        }
    }
}
