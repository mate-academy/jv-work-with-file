package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_COLUMN = 0;
    private static final int AMOUNT_COLUMN = 1;
    private static final int SUM_APPLAY_COLUMN = 0;
    private static final int SUM_BUY_COLUMN = 1;
    private static final int SUM_RES_COLUMN = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder content = createReport(countResults(readFile(fromFileName)));
        writeFile(toFileName, content);
    }

    public int[] countResults(StringBuilder stringBuilder) {
        String[] records = stringBuilder.toString().split(System.lineSeparator());
        int amountSupply = 0;
        int amountBuy = 0;
        for (String record : records) {
            String[] columns = record.split(",");
            for (int i = 0; i < columns.length; i++) {
                if (columns[OPERATION_TYPE_COLUMN].equals("supply")) {
                    amountSupply += Integer.parseInt(columns[AMOUNT_COLUMN]);
                    break;
                } else {
                    amountBuy += Integer.parseInt(columns[AMOUNT_COLUMN]);
                    break;
                }
            }
        }
        return new int[]{amountSupply, amountBuy, amountSupply - amountBuy};
    }

    public StringBuilder createReport(int[] data) {
        StringBuilder stringBuilder = new StringBuilder("");
        return stringBuilder.append("supply,")
                .append(data[SUM_APPLAY_COLUMN])
                .append(System.lineSeparator())
                .append("buy,")
                .append(data[SUM_BUY_COLUMN])
                .append(System.lineSeparator())
                .append("result,")
                .append(data[SUM_RES_COLUMN]);
    }

    public StringBuilder readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder("");
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value;
            while ((value = reader.readLine()) != null) {
                stringBuilder.append(value).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("can't read file", e);
        }
        return stringBuilder;
    }

    public void writeFile(String toFileName, StringBuilder content) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true))) {
            bufferedWriter.append(content);
        } catch (IOException e) {
            throw new RuntimeException("can't write file", e);
        }
    }
}
