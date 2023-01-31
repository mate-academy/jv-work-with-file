package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private int supply;
    private int buy;

    public void getStatistic(String fromFileName, String toFileName) {
        String line = null;
        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fromFileName)))) {
            line = reader.readLine();
            while (line != null) {
                if (line.contains("supply")) {
                    supply += getAmount(line);
                } else {
                    buy += getAmount(line);
                }
                line = reader.readLine();
            }
        } catch (IOException exception) {
            throw new RuntimeException("File don't read", exception);
        }
        builder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy);
        line = builder.toString();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(toFileName)))) {
            writer.write(line);
        } catch (IOException exception) {
            throw new RuntimeException("File don't write", exception);
        }
    }

    public int getAmount(String line) {
        String[] amount = line.split(",");
        return Integer.parseInt(amount[1]);
    }
}
