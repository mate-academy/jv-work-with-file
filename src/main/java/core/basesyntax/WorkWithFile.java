package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static int buyReport = 0;
    private static int supplyReport = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileLineContent;
        int rowsCount = 0;
        StringBuilder str = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            while ((fileLineContent = br.readLine()) != null) {
                str.append(fileLineContent).append(System.lineSeparator());
                rowsCount++;
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String[] dataArray = str.toString().split(System.lineSeparator());
        String[][] dataMatrix = new String[rowsCount][2];
        rowsCount = 0;
        for (String dataArrayItem : dataArray) {
            dataMatrix[rowsCount] = dataArrayItem.split(",");
            rowsCount++;
        }
        for (String[] dataMatrixItem : dataMatrix) {
            for (rowsCount = 0; rowsCount < 1; rowsCount++) {
                if (dataMatrixItem[0].equals("supply")) {
                    supplyReport = supplyReport + Integer.parseInt(dataMatrixItem[1]);
                } else if (dataMatrixItem[0].equals("buy")) {
                    buyReport = buyReport + Integer.parseInt(dataMatrixItem[1]);
                }
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            bw.write("supply," + supplyReport + System.lineSeparator()
                    + "buy," + buyReport + System.lineSeparator()
                    + "result," + (supplyReport - buyReport));
        } catch (IOException e) {
            throw new RuntimeException("Can't write the file", e);
        }
    }
}
