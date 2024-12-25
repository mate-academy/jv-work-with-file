package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {

        if (fromFileName == null || toFileName == null) {
            return;
        }

        Integer supplyCount = 0;
        Integer buyCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String value = br.readLine();
            while (value != null) {
                String [] elements = value.split(",");
                if (elements[0].equals("supply")) {
                    supplyCount += Integer.parseInt(elements[1]);
                } else {
                    buyCount += Integer.parseInt(elements[1]);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("supply").append(",").append(supplyCount).append(System.lineSeparator());
        sb.append("buy").append(",").append(buyCount).append(System.lineSeparator());
        sb.append("result").append(",").append(supplyCount - buyCount)
                        .append(System.lineSeparator());

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            bw.write(sb.toString());
        } catch (Exception e) {
            throw new RuntimeException("Can't write data from the file " + toFileName, e);
        }
    }
}
