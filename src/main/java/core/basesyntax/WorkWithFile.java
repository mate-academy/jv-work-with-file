package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final int MAX_SUPPLY = 500;
    private int buy;
    private int supply;

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        try {
            BufferedReader fromFileReder = new BufferedReader(new FileReader(fromFile));
            StringBuilder readedFromFile = new StringBuilder();
            int data = fromFileReder.read();
            while (data != -1) {
                readedFromFile.append((char) data);
                data = fromFileReder.read();
            }
            String[] dataLoop = readedFromFile.toString().split(System.lineSeparator());
            for (String line : dataLoop) {
                if (line.split(",")[0].equals("buy")) {
                    buy += Integer.parseInt(line.split(",")[1]);
                }
                if (line.split(",")[0].equals("supply")) {
                    supply += Integer.parseInt(line.split(",")[1]);
                }
            }
            int result = supply - buy;
            if (supply > MAX_SUPPLY) {
                supply = supply / 2;
                buy = buy / 2;
                result = result / 2;
            }
            String writeInfo = "supply," + supply + System.lineSeparator() + "buy," + buy
                    + System.lineSeparator() + "result," + result;
            BufferedWriter writeTo = new BufferedWriter(new FileWriter(toFile));
            writeTo.write(writeInfo);
            writeTo.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


