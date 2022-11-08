package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_OF_NUMBER = 1;
    private static final int SUPPLY_POSITION = 0;
    private static final int BUY_POSITION = 1;
    private static final int RESULT_POSITION = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        WorkWithFile worker = new WorkWithFile();
        worker.writeData(worker.createReport(worker.readFromFile(fromFileName)), toFileName);
    }

    public String readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read a file", e);
        }
        return builder.toString();
    }

    public int[] createReport(String string) {
        int buy = 0;
        int supply = 0;
        int result;
        String[] split = string.split(System.lineSeparator());
        for (int i = 0; i < split.length; i++) {
            if (split[i].startsWith("buy")) {
                buy += Integer.parseInt(split[i].substring(split[i].indexOf(",")
                        + INDEX_OF_NUMBER));
            } else if (split[i].startsWith("supply")) {
                supply += Integer.parseInt(split[i].substring(split[i].indexOf(",")
                        + INDEX_OF_NUMBER));
            }
        }
        result = supply - buy;
        int []resultData = new int[]{supply, buy, result};
        return resultData;
    }

    public void writeData(int[] array, String toFileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true));
            writer.write("supply," + array[SUPPLY_POSITION] + System.lineSeparator()
                    + "buy," + array[BUY_POSITION] + System.lineSeparator()
                    + "result," + array[RESULT_POSITION]);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to the file", e);
        }
    }
}
