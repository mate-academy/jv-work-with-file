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
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                String[] info = line.split(",");
                if (info[0].equals("supply")) {
                    supply = supply + Integer.parseInt(info[1]);
                }
                if (info[0].equals("buy")) {
                    buy = buy + Integer.parseInt(info[1]);
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't read in file ", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read in file ", e);
        }
        int result = supply - buy;
        String allResult = getInfoOfDay(supply, buy, result);
        writeStatistik(toFileName, allResult);
    }

    private String getInfoOfDay(int supple, int buy, int result) {
        return new StringBuilder("supply").append(",").append(supple).append(System.lineSeparator())
                .append("buy").append(",").append(buy).append(System.lineSeparator())
                .append("result").append(",").append(result).toString();
    }

    private void writeStatistik(String toFileName, String allResult) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(allResult);
        } catch (IOException e) {
            throw new RuntimeException("Can't write in file" + toFileName, e);
        }
    }
}

