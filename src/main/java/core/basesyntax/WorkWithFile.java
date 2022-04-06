package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String content = readFile(fromFileName);
        writeFile(content, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        int supply = 0;
        int buy = 0;
        int result = 0;
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            String[] split;
            while (line != null) {
                split = line.split(",");
                switch (split[OPERATION_TYPE_INDEX]) {
                    case "buy":
                        buy += Integer.parseInt(split[AMMOUNT_INDEX]);
                        break;
                    default:
                        supply += Integer.parseInt(split[AMMOUNT_INDEX]);
                }
                result = supply - buy;
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file" + fromFileName, e);
        }
        return builder.append("supply," + supply + System.lineSeparator())
                .append("buy," + buy + System.lineSeparator())
                .append("result," + result).toString();
    }

    private void writeFile(String content, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(toFileName))) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }
}
