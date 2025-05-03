package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        this.writeStatics(toFileName,this.readStatics(fromFileName));
    }

    private int[] readStatics(String fromFileName) {

        StringBuilder stringBuilder = new StringBuilder();
        int buy = 0;
        int supply = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int value = reader.read();
            while (value != -1) {
                if ((char)value == (char)'\n') {
                    String[] record = stringBuilder.toString().split(",");
                    if (record[0].equals("buy")) {
                        buy += Integer.parseInt(record[1]);
                    }
                    if (record[0].equals("supply")) {
                        supply += Integer.parseInt(record[1]);
                    }
                    stringBuilder.setLength(0);
                } else {
                    stringBuilder.append((char) value);
                }
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't open file",e);
        }
        int[] resultarr = {buy,supply};
        return resultarr;
    }

    private void writeStatics(String toFileName, int[] reads) {

        int result = reads[1] - reads[0];
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + reads[1] + System.lineSeparator());
            writer.write("buy," + reads[0] + System.lineSeparator());
            writer.write("result," + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't open file",e);
        }
    }
}
