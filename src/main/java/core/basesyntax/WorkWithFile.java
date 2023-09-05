package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String data;
        int supplyCount = 0;
        int buyCount = 0;
        int result = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            data = reader.readLine();
            while (data != null) {
                String[] part = data.split(",");
                if (part[0].startsWith("supply")) {
                    supplyCount += Integer.parseInt(part[1]);
                } else if (part[0].startsWith("buy")) {
                    buyCount += Integer.parseInt(part[1]);
                }
                result = supplyCount - buyCount;
                data = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(toFileName));
            StringBuilder builder = new StringBuilder();
            writer.write("supply," + supplyCount + System.lineSeparator()
                    + "buy," + buyCount + System.lineSeparator()
                    + "result," + result + System.lineSeparator());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
