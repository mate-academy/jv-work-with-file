package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int STEP = 2;
    private static final String SUPPLY_OPERATION = "supply";
    private String line;
    private String tmp = "";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] textFromFile = readFromFile(fromFileName);
        String[] report = createReport(textFromFile);
        writeToFile(report,toFileName);
    }

    public String[] createReport(String[] dataFromFile) {

        int supplyValue = 0;
        int buyValue = 0;

        for (int i = 0; i < dataFromFile.length; i += STEP) {
            if (dataFromFile[i].equals(SUPPLY_OPERATION)) {
                supplyValue += Integer.valueOf(dataFromFile[i + 1]);
            } else {
                buyValue += Integer.valueOf(dataFromFile[i + 1]);
            }
        }

        return (new String[]{"supply," + supplyValue, "buy," + buyValue, "result,"
                + (supplyValue - buyValue)});
    }

    private String[] readFromFile(String fileName) {

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

