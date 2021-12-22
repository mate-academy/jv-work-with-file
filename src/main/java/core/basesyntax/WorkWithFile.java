package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private int supply = 0;
    private int buy = 0;
    private int result = 0;

    private void readStatics(String fromFileName) {

        StringBuilder stringBuilder = new StringBuilder();

        this.supply = 0;
        this.buy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int value = reader.read();
            while (value != -1) {
                if ((char)value == (char)'\n') {
                    String[] record = stringBuilder.toString().split(",");
                    if (record[0].equals("buy")) {
                        this.buy += Integer.parseInt(record[1]);
                    }
                    if (record[0].equals("supply")) {
                        this.supply += Integer.parseInt(record[1]);
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
    }

    private void writeStatics(String toFileName) {

        this.result = this.supply - this.buy;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

            writer.write("supply," + this.supply + System.lineSeparator());
            writer.write("buy," + this.buy + System.lineSeparator());
            writer.write("result," + this.result + System.lineSeparator());

        } catch (IOException e) {
            throw new RuntimeException("Can't open file",e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {

        this.readStatics(fromFileName);
        this.writeStatics(toFileName);

    }
}
