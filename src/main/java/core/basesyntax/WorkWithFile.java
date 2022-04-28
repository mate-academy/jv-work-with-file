package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class WorkWithFile {

    private ArrayList readFromFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        BufferedReader reader = null;
        ArrayList<String>  inputData = new ArrayList<>();
        String[] line;
        try {
            reader = new BufferedReader(new FileReader(fromFile));
            String value = reader.readLine();
            while (value != null) {
                line = value.split(","));
                inputData.add(Arrays.asList(line));
                value = reader.readLine();
            }
        } catch (IOException e) {
                throw new RuntimeException("Can't read file", e);
            } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can't close file");
                }
            }
        }
        return inputData;
    }

    private StringBuilder createStatistic(StringBuilder inputData ) {
        int allSupply = 0;
        int allBuy = 0;
        inputData.
        switch (tempData[0]) {
            case BUY:
                allBuy += Integer.valueOf(tempData[1]);
                break;
            case SUPPLY:
                allSupply += Integer.valueOf(tempData[1]);
                break;
            default:
        return null;
    }

    public void getStatistic(String fromFileName, String toFileName) {

        File tofile = new File(toFileName);

        String[] tempData = new String[FIELDS];
        BufferedWriter writer = null;


        try {

                switch (tempData[0]) {
                    case BUY:
                        allBuy += Integer.valueOf(tempData[1]);
                        break;
                    case SUPPLY:
                        allSupply += Integer.valueOf(tempData[1]);
                        break;
                    default:

                }
                value = reader.readLine();
            }
            int result = allSupply - allBuy;
            writer = new BufferedWriter(new FileWriter(tofile));
            StringBuilder outData = new StringBuilder();
            outData.append(SUPPLY)
                    .append(",")
                    .append(allSupply)
                    .append(System.lineSeparator())
                    .append(BUY)
                    .append(",")
                    .append(allBuy)
                    .append(System.lineSeparator())
                    .append("result")
                    .append(",")
                    .append(result);
            writer.write(outData.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can't close file");
                }
            }
        }
    }
}
