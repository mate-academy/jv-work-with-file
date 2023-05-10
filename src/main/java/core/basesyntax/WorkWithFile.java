package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SUPPLY_IDENTIFIER = "supply";
    public static final int SUPPLY_POSITION = 0;
    public static final String BUY_IDENTIFIER = "buy";
    public static final int BUY_POSITION = 1;
    public static final String RESULT_IDENTIFIER = "result";
    public static final int RESULT_POSITION = 2;
    public static final int NAME_POSITION = 0;
    public static final int VALUE_POSITION = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] statistic = readStatistic(fromFileName);
        String[] summedStatistic = sumStatistic(statistic);
        writeStatistic(summedStatistic, toFileName);
    }

    public String[] readStatistic(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fromFileName));
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found!");
        } catch (IOException e) {
            throw new RuntimeException("IO error!");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException("Buffer reader closing error!");
                }
            }
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    private void writeStatistic(String[] statistic, String toFileName) {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            for (String content : statistic) {
                bufferedWriter.write(content);
                bufferedWriter.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private String[] sumStatistic(String[] statistic) {
        String[] summedStatistic = {SUPPLY_IDENTIFIER, BUY_IDENTIFIER, RESULT_IDENTIFIER};
        int[] values = new int[summedStatistic.length];
        for (int i = 0; i < statistic.length; i++) {
            String[] content = statistic[i].split(",");
            switch (content[NAME_POSITION]) {
                case SUPPLY_IDENTIFIER:
                    values[SUPPLY_POSITION] += Integer.valueOf(content[VALUE_POSITION]);
                    break;
                case BUY_IDENTIFIER:
                    values[BUY_POSITION] += Integer.valueOf(content[VALUE_POSITION]);
                    break;
                default:
                    break;
            }
        }
        values[RESULT_POSITION] = values[SUPPLY_POSITION] - values[BUY_POSITION];
        for (int i = 0; i < summedStatistic.length; i++) {
            summedStatistic[i] = summedStatistic[i] + "," + values[i];
        }
        return summedStatistic;
    }
}
