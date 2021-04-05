package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFileName = dataFromFileName(fromFileName);
        writerToFile(dataFromFileName, toFileName);
    }

    private String dataFromFileName(String fromFileName) {
        int supply = 0;
        int buy = 0;
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] resultNumbers = value.split(",");
                if (resultNumbers[0].equals(SUPPLY)) {
                    supply += Integer.parseInt(resultNumbers[1]);
                }
                if (resultNumbers[0].equals(BUY)) {
                    buy += Integer.parseInt(resultNumbers[1]);
                }
                value = bufferedReader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        stringBuilder.append(SUPPLY).append(",").append(supply)
                .append(System.lineSeparator())
                .append(BUY).append(",").append(buy)
                .append(System.lineSeparator())
                .append(RESULT).append(",").append(supply-buy);
        return stringBuilder.toString();
    }

    private void writerToFile(String resultData, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(resultData);
        } catch (IOException e) {
            throw new RuntimeException("Can't open file", e);
        }
    }

}
