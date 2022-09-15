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
        int supplyQuantity = separateData(file, "supply");
        int buyQuantity = separateData(file, "buy");
        file = new File(toFileName);
        fillFile(file, supplyQuantity, buyQuantity);
    }

    private int separateData(File file, String comparisonWord) {
        String [] line = new String[2];
        int result = 0;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String reading = bufferedReader.readLine();
            while (reading != null) {
                line = reading.split(",");
                if (line[OPERATION_INDEX].equals(comparisonWord)) {
                    result += Integer.parseInt(line[COUNT_INDEX]);
                }
                reading = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can`t read file: " + file.getName() + " " + e);
        } catch (IOException e) {
            System.out.println("Can`t read line from file: " + file.getName() + " " + e);
        }
        return result;
    }

    private void fillFile(File file, int addition, int subtraction) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write("supply," + addition + System.lineSeparator()
                    + "buy," + subtraction + System.lineSeparator()
                    + "result," + (addition - subtraction));
        } catch (IOException e) {
            System.out.println("Can`t write data in " + file.getName() + ", " + e);
        }
    }
}
