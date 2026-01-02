package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int STRING_NAME = 0;
    private static final int NUMBERS = 1;

    public void getStatistic(String fromFileName, String toFileName) throws IOException {
        String report;
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String s;
            int numberOfSupplies = 0;
            int numberOfBuys = 0;

            while ((s = br.readLine()) != null) {
                String[] data = s.split(",");
                if (data[STRING_NAME].equals("supply")) {
                    numberOfSupplies += Integer.parseInt(data[NUMBERS]);
                }
                if (data[STRING_NAME].equals("buy")) {
                    numberOfBuys += Integer.parseInt(data[NUMBERS]);
                }
            }

            int result = numberOfSupplies - numberOfBuys;
            report = "supply," + numberOfSupplies + System.lineSeparator()
                    + "buy," + numberOfBuys + System.lineSeparator()
                    + "result," + result;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            bw.write(report);
        }
    }
}

