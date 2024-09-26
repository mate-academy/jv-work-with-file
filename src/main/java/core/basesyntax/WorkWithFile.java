package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private File file;

    public void getStatistic(String fromFileName, String toFileName) {
        writeDataToFile(readAndSort(fromFileName), toFileName);

    }

    public int [] readAndSort(String fromFileName) {
        file = new File(fromFileName);
        String [] temp = new String[2];
        int [] result = new int[3];

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String splitInput = reader.readLine();

            while (splitInput != null) {
                temp = splitInput.split(",");
                System.out.println(splitInput);
                splitInput = reader.readLine();

                switch (temp[0]) {
                    case "supply":
                        result[0] += Integer.parseInt(temp[1]);
                        break;
                    default:
                        result[1] += Integer.parseInt(temp[1]);
                        break;
                }
            }
            result[2] = result[0] - result[1];
        } catch (IOException e) {
            throw new RuntimeException("Can`t found file", e);
        }
        return result;
    }

    public void writeDataToFile(int [] inputData, String destination) {
        file = new File(destination);
        FileWriter cleaner;
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("supply," + inputData[0] + System.lineSeparator()
                    + "buy," + inputData[1] + System.lineSeparator()
                    + "result," + inputData[2] + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Can`t find current file", e);
        }
    }
}
