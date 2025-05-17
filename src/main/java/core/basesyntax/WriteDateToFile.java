package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteDateToFile {
    public void writeDte(int supplyAmount, int buyAmount, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));) {
            bufferedWriter.write("supply," + supplyAmount + System.lineSeparator());
            bufferedWriter.write("buy," + buyAmount + System.lineSeparator());
            bufferedWriter.write("result," + (supplyAmount - buyAmount));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
