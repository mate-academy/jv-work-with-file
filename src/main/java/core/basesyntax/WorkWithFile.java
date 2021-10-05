package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private int buyRep = 0;
    private int supplyRep = 0;
    private StringBuilder stringBuilder = new StringBuilder();
    private int rows = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        WorkWithFile statistics = new WorkWithFile();
        statistics.readFromFile(fromFileName);
        statistics.createReport();
        statistics.writeToFile(toFileName);
    }

    private void readFromFile(String fromFileName) {
        String fileLineContent;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((fileLineContent = bufferedReader.readLine()) != null) {
                stringBuilder.append(fileLineContent).append(System.lineSeparator());
                rows++;
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName, e);
        }
    }

    private void createReport() {
        String[] dataArray = stringBuilder.toString().split(System.lineSeparator());
        String[][] dataMatrix = new String[rows][2];
        rows = 0;
        for (String dataArrayElement : dataArray) {
            dataMatrix[rows] = dataArrayElement.split(SEPARATOR);
            rows++;
        }

        for (String[] dataMatrixElement : dataMatrix) {
            for (rows = 0; rows < 1; rows++) {
                if (dataMatrixElement[0].equals("buy")) {
                    buyRep += Integer.parseInt(dataMatrixElement[1]);
                } else if (dataMatrixElement[0].equals("supply")) {
                    supplyRep += Integer.parseInt(dataMatrixElement[1]);
                }
            }
        }
    }

    private void writeToFile(String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + supplyRep + System.lineSeparator()
                    + "buy," + buyRep + System.lineSeparator()
                    + "result," + (supplyRep - buyRep));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
