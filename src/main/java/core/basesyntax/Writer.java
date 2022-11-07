package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    public void writeData(int[] array, String toFileName) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true));
            writer.write("supply," + array[0] + System.lineSeparator()
                    + "buy," + array[1] + System.lineSeparator()
                    + "result," + array[2]);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to the file", e);
        }
    }

    public void createReport(int[] array) {
        System.out.println("supply = " + array[0] + System.lineSeparator()
                + "buy = " + array[1] + System.lineSeparator()
                + "result = " + array[2]);
    }
}
