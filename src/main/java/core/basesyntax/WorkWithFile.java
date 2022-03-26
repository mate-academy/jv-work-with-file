package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File baseFile = new File(fromFileName);
        File reportFile = new File(toFileName);
        int sumOfSupply = 0;
        int sumOfBuy = 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportFile, true))) {
            List<String> data = Files.readAllLines(baseFile.toPath());
            for (String value : data) {
                String[] values = value.split(",");
                if (values[0].equals("supply")) {
                    sumOfSupply += Integer.parseInt(values[1]);
                }
                if (values[0].equals("buy")) {
                    sumOfBuy += Integer.parseInt(values[1]);
                }
            }
            writer.write("supply," + sumOfSupply + System.lineSeparator());
            writer.write("buy," + sumOfBuy + System.lineSeparator());
            writer.write("result," + (sumOfSupply - sumOfBuy));
        } catch (IOException e) {
            throw new RuntimeException("Can't read/write data");
        }
    }
}
