package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] rows = readFile(fromFileName).split("\n");
        int supply = calculation(rows, 0, 0)[0];
        int buy = calculation(rows, 0, 0)[1];
        int result = calculation(rows, 0, 0)[2];
        String report = "supply," + supply + "\n"
                + "buy," + buy + "\n"
                + "result," + result;
        writeToFile(toFileName, report);
    }

    public String readFile(String fromFile) {
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(fromFile);
        try {
            BufferedReader info = new BufferedReader(new FileReader(file));
            int digitInfo = info.read();
            while (digitInfo != -1) {
                stringBuilder.append((char) digitInfo);
                digitInfo = info.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file from file", e);
        }
        return stringBuilder.toString();
    }

    public void writeToFile(String toFile, String report) {
        File file2 = new File(toFile);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file2))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }

    public int[] calculation(String[] rows, int supply, int buy) {
        for (String row : rows) {
            String[] values = row.split(",");
            if (values[0].equals("supply")) {
                supply += Integer.parseInt(values[1]);
            } else {
                buy += Integer.parseInt(values[1]);
            }
        }
        int result = supply - buy;
        return new int[]{supply, buy, result};
    }
}
