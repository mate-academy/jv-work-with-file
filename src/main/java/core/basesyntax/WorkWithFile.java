package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ","; // file data separator
    private int buyReport = 0; // result buy per day
    private int supplyReport = 0; // result supply per day

    public void getStatistic(String fromFileName, String toFileName) {
        // read fromFileName and build string
        String fileLineContent; // read line file content
        int rowsCount = 0; // count rows in file
        StringBuilder str = new StringBuilder(); // string from file content
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            while ((fileLineContent = br.readLine()) != null) {
                str.append(fileLineContent).append(System.lineSeparator());
                rowsCount++;
            }
        } catch (IOException e) {
            throw new RuntimeException("Error");
        }
        // build matrix array from file data
        String [] dataArray = str.toString().split(System.lineSeparator());
        String [][] dataMatrix = new String[rowsCount][2];
        rowsCount = 0;
        for (String dataArrayItem : dataArray) {
            dataMatrix[rowsCount] = dataArrayItem.split(COMMA);
            rowsCount++;
        }
        // calculate buy, supply and result per day value
        for (String [] dataMatrixItem : dataMatrix) {
            if (dataMatrixItem[0].equals("buy")) {
                buyReport = buyReport + Integer.parseInt(dataMatrixItem[1]);
            } else if (dataMatrixItem[0].equals("supply")) {
                supplyReport = supplyReport + Integer.parseInt(dataMatrixItem[1]);
            }
        }
        // save result to file report
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            bw.write("supply," + supplyReport + System.lineSeparator()
                    + "buy," + buyReport + System.lineSeparator()
                    + "result," + (supplyReport - buyReport));
        } catch (IOException e) {
            throw new RuntimeException("Error");
        }
    }
}
