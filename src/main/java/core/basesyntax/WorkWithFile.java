package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String statisticsResult = getStatisticsFromFile(fromFileName);
        writeTextToFile(toFileName, statisticsResult);
    }

    private String getStatisticsFromFile(String fromFileName) {
        int buy = 0;
        int supply = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            for (String line; (line = br.readLine()) != null; ) {
                String[] parts = line.split(",");
                if (parts[0].equals("buy")) {
                    buy += Integer.parseInt(parts[1]);
                } else {
                    supply += Integer.parseInt(parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getResult(buy, supply);
    }

    private void writeTextToFile(String toFileName, String text) {
        try (FileWriter writer = new FileWriter(toFileName, false)) {
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private String getResult(int buy, int supply) {
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy);
    }
}
