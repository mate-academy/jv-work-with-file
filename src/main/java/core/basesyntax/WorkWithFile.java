package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private int supply;
    private int buy;

    public void getStatistic(String fromFileName, String toFileName) {
        readFile(fromFileName);
        writeFile(toFileName);
        cleanBuffered();
    }

    private void writeFile(String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + supply
                    + "\r\n" + "buy," + buy
                    + "\r\n" + "result," + (supply - buy));
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file " + toFileName, e);
        }
    }

    private void readFile(String fromFileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String info = reader.readLine();
            while (info != null) {
                String[] strings = info.split(",");
                if (strings[0].equals("supply")) {
                    supply += Integer.parseInt(strings[1]);
                } else if (strings[0].equals("buy")) {
                    buy += Integer.parseInt(strings[1]);
                }
                info = reader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    private void cleanBuffered() {
        supply = 0;
        buy = 0;
    }
}

