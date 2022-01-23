package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final int STATISTIC_POSITIONS_COUNT = 6;
    static final int SUPPLY_POSITION_NUMBER = 0;
    static final int BUY_POSITION_NUMBER = 2;
    static final int RESULT_POSITION_NUMBER = 4;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = getDataFromFile(fromFileName);
        String[]statistic = getStatisticPattern();
        StringBuilder stringBuilder = new StringBuilder();
        int sum;
        for (String datum : data) {
            String[] note = datum.split(",");
            sum = Integer.parseInt(note[1]);
            switch (note[0]) {
                case "supply":
                    statistic[SUPPLY_POSITION_NUMBER + 1]
                            = String.valueOf(Integer.parseInt(statistic[SUPPLY_POSITION_NUMBER + 1])
                            + sum);
                    break;
                case "buy":
                    statistic[BUY_POSITION_NUMBER + 1]
                            = String.valueOf(Integer.parseInt(statistic[BUY_POSITION_NUMBER + 1])
                            + sum);
                    break;
                default: break;
            }
        }
        statistic[RESULT_POSITION_NUMBER + 1]
                = String.valueOf(Integer.parseInt(statistic[SUPPLY_POSITION_NUMBER + 1])
                - Integer.parseInt(statistic[BUY_POSITION_NUMBER + 1]));
        for (int i = 0; i < statistic.length; i++) {
            stringBuilder.append(statistic[i]);
            if (i % 2 != 0) {
                stringBuilder.append(System.lineSeparator());
            }
        }
        writeDataToFile(stringBuilder.toString(), toFileName);
    }

    public String[] getStatisticPattern() {
        String[] statistic = new String[STATISTIC_POSITIONS_COUNT];
        for (int i = 0; i < statistic.length; i++) {
            switch (i) {
                case SUPPLY_POSITION_NUMBER: statistic[i] = "supply,";
                    break;
                case (SUPPLY_POSITION_NUMBER + 1): statistic[i] = "0";
                    break;
                case BUY_POSITION_NUMBER: statistic[i] = "buy,";
                    break;
                case BUY_POSITION_NUMBER + 1: statistic[i] = "0";
                    break;
                case RESULT_POSITION_NUMBER: statistic[i] = "result,";
                    break;
                case RESULT_POSITION_NUMBER + 1: statistic[i] = "0";
                    break;
                default: break;
            }
        }
        return statistic;
    }

    public String[] getDataFromFile(String fromFileName) {
        File fileOut = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileOut))) {
            String textLine = bufferedReader.readLine();
            while (textLine != null) {
                stringBuilder.append(textLine).append(System.lineSeparator());
                textLine = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File doesn't exist", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    public void writeDataToFile(String data, String toFileName) {
        File fileTo = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTo, true))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
