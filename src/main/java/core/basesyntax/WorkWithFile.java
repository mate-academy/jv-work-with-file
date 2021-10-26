package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int[] array = readAndCalculateData(fromFileName);
        String report = createReport(array);
        writeDataToNewFile(report, toFileName);
    }

    private int[] readAndCalculateData(String fromFileName) {
        int supply = 0;
        int buy = 0;
        String str;
        int index;
        int[] result;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            while ((str = reader.readLine()) != null) {
                index = str.indexOf(",");
                String name = str.substring(0, index);
                int number = Integer.parseInt(str.substring(index + 1));
                if (name.equals("supply")) {
                    supply += number;
                } else if (name.equals("buy")) {
                    buy += number;
                }
            }
            int thirdElement = supply - buy;
            result = new int[]{supply, buy, thirdElement};
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly read data from file ", e);
        }
        return result;
    }

    private String createReport(int[] array) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(array[0]).append(System.lineSeparator())
                .append("buy,").append(array[1]).append(System.lineSeparator())
                .append("result,").append(array[2]);
        return stringBuilder.toString();
    }

    private void writeDataToNewFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly write data to file ", e);
        }
    }
}
