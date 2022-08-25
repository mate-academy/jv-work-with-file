package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int OPERATION_INDEX = 0;
    public static final int COUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        String [] line = new String[2];
        int supplyQuantity = 0;
        int buyQuantity = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String reading = bufferedReader.readLine();
            while (reading != null) {
                line = reading.split(",");
                if (line[OPERATION_INDEX].equals("supply")) {
                    supplyQuantity += Integer.parseInt(line[COUNT_INDEX]);
                } else {
                    buyQuantity += Integer.parseInt(line[COUNT_INDEX]);
                }
                reading = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can`t read file: " + fromFileName + " " + e);
        } catch (IOException e) {
            System.out.println("Can`t read line from file: " + fromFileName + " " + e);
        }
        file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write("supply," + supplyQuantity + System.lineSeparator()
                    + "buy," + buyQuantity + System.lineSeparator()
                    + "result," + (supplyQuantity - buyQuantity));
        } catch (IOException e) {
            System.out.println("Can`t write data in " + toFileName + ", " + e);
        }
    }
}
