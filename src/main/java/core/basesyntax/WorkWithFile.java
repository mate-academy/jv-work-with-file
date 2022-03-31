package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String resultOfRead = readFromFile(fromFileName);
        int supply = 0;
        int buy = 0;
        String[] split = resultOfRead.split(System.lineSeparator());
        for (String each : split) {
            String[] commaArray = each.split(",");
            if (commaArray[0].equals("supply")) {
                supply += Integer.parseInt(commaArray[1]);
            } else {
                buy += Integer.parseInt(commaArray[1]);
            }
        }
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy);
        String resultStat = resultBuilder.toString();
        write(toFileName, resultStat);
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file" + fromFileName, e);
        }
        return builder.toString();
    }

    private void write(String toFileName, String resultStatistic) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(resultStatistic);
        } catch (IOException e) {
            throw new RuntimeException("Something is going wrong", e);
        }
    }
}


