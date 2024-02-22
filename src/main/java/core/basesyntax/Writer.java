package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    public void write(String toFileName, int[] amount) {
        int generalSupply = amount[0];
        int generalBuy = amount[1];
        int result = amount[2];
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write("supply," + generalSupply + System.lineSeparator());
            bufferedWriter.write("buy," + generalBuy + System.lineSeparator());
            bufferedWriter.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't work with file", e);
        }
    }
}
