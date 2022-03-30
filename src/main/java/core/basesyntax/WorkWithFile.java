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
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
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
            throw new RuntimeException(" Problem whis file! ");
        } catch (IOException e) {
            throw new RuntimeException(" Problem whis file! ");
        }
        result = supply - buy;
        String allResult = infoOfDay(supply, buy, result);
        writeStatistik(toFileName, allResult);
    }

    private String infoOfDay(int supple, int buy, int result) {
        return new StringBuilder("supply").append(",").append(supple).append(System.lineSeparator())
                .append("buy").append(",").append(buy).append(System.lineSeparator())
                .append("result").append(",").append(result).toString();
    }

    public void writeStatistik(String toFileName, String allResult) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(allResult);
        } catch (IOException e) {
            throw new RuntimeException("Can't write in file");
        }
    }
}

