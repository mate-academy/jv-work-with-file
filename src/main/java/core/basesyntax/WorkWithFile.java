package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
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
            throw new RuntimeException("Can't read file from file" + fromFile, e);
        }
        return stringBuilder.toString();
    }

    public void writeToFile(String toFile, String text) {
        File file = new File(toFile);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFile, e);
        }
    }

    public String createReport(String dataFormFile) {
        String[] rows = dataFormFile.split("\n");
        int supply = calculation(rows, 0, 0)[0];
        int buy = calculation(rows, 0, 0)[1];
        int result = calculation(rows, 0, 0)[2];
        return "supply," + supply + "\n"
                + "buy," + buy + "\n"
                + "result," + result;
    }

    private int[] calculation(String[] rows, int supply, int buy) {
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
