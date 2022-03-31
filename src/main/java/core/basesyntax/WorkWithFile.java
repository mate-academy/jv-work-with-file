package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String stringFromFile = bufferedReader.readLine();
            while (stringFromFile != null) {
                String[] info = stringFromFile.split(",");
                if (info[0].equals("supply")) {
                    supply += Integer.parseInt(info[1]);
                }
                if (info[0].equals("buy")) {
                    buy += Integer.parseInt(info[1]);
                }
                stringFromFile = bufferedReader.readLine();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error: File not found!" + toFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Error IOException: File not found" + toFileName, e);
        }
        result = supply - buy;
        String allResult = outputInfo(supply, buy, result);
        writeToFile(toFileName, allResult);
    }

    private String outputInfo(int supply, int buy, int result) {
        return new
                StringBuilder("supply").append(",").append(supply).append(System.lineSeparator())
                .append("buy").append(",").append(buy).append(System.lineSeparator())
                .append("result").append(",").append(result).toString();
    }

    private void writeToFile(String toFileName, String allResult) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(allResult);
        } catch (IOException e) {
            throw new RuntimeException("Some mistake with writing to file " + toFileName, e);
        }
    }

}
