package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        int supply = 0;
        int buy = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile));
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] values = value.split(",");
                switch (values[OPERATION_TYPE_INDEX]) {
                    case "supply":
                        supply += Integer.parseInt(values[AMMOUNT_INDEX]);
                        break;
                    case "buy":
                        buy += Integer.parseInt(values[AMMOUNT_INDEX]);
                        break;
                    default:
                }
                value = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("There is no such file: " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(toFile));
            bufferedWriter.write("supply," + supply + System.lineSeparator());
            bufferedWriter.write("buy," + buy + System.lineSeparator());
            bufferedWriter.write("result," + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can`t close buffered writer", e);
                }
            }
        }
    }
}
