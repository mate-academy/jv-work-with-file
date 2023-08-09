package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static String line;
    private static String tmp = "";
    private static String[] dataFromFile;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyValue = 0;
        int buyValue = 0;
        dataFromFile = readFromFile(fromFileName);

        for (int i = 0; i < dataFromFile.length; i += 2) {
            if (dataFromFile[i].equals("supply")) {
                supplyValue += Integer.valueOf(dataFromFile[i + 1]);
            } else {
                buyValue += Integer.valueOf(dataFromFile[i + 1]);
            }
        }

        writeToFile(new String[]{"supply," + supplyValue, "buy," + buyValue, "result,"
                + (supplyValue - buyValue)}, toFileName);
    }

    private static String[] readFromFile(String fileName) {

        if (!tmp.equals("")) {
            tmp = "";
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            while ((line = reader.readLine()) != null) {
                tmp += line + ",";
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            System.out.println("Error i/o" + e);
        }

        return tmp.split(",");
    }

    private void writeToFile(String[] result, String fileName) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            for (String data : result) {
                writer.write(data);
                writer.newLine();
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("Error writing file" + e);
        }
    }
}

