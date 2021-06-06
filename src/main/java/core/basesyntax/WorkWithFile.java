package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final String[] TYPE_OPERATION = {"supply", "buy"};
    static final int POSITION_OPERATION_IN_LINE = 0;
    static final int POSITION_COST_IN_LINE = 1;
    static final int POSITION_ALL_SUPPLY_OPERATION = 0;
    static final int POSITION_All_BUY_OPERATION = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File fileReader = new File(fromFileName);
        String dataLineFromFile;
        String[] dataLineSplit;
        String line;
        int sumOperation;
        int[] resultOperation = new int[TYPE_OPERATION.length];
        int count = 0;

        try {
            for (String lineOperation : TYPE_OPERATION) {
                sumOperation = 0;
                BufferedReader bufferedReader = new BufferedReader(new FileReader(fileReader));
                line = bufferedReader.readLine();
                StringBuilder dataLineToFile = new StringBuilder();

                while (line != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    dataLineFromFile = stringBuilder.append(line).toString();
                    dataLineSplit = dataLineFromFile.split(",");

                    if ((dataLineSplit[POSITION_OPERATION_IN_LINE]).equals(lineOperation)) {
                        sumOperation += Integer.parseInt(dataLineSplit[POSITION_COST_IN_LINE]);
                    }
                    resultOperation[count] = sumOperation;
                    line = bufferedReader.readLine();
                }
                count++;

                try (BufferedWriter bufferedWriter =
                             new BufferedWriter(new FileWriter(toFileName, true))) {
                    bufferedWriter.write(dataLineToFile.append(lineOperation)
                            .append(",")
                            .append(sumOperation)
                            .append("\r\n")
                            .toString());
                } catch (IOException e) {
                    throw new RuntimeException("Can't write data file", e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data file", e);
        } finally {
            try (BufferedWriter finishBufferedWriter =
                         new BufferedWriter(new FileWriter(toFileName, true))) {
                StringBuilder finishWriterToFile = new StringBuilder();
                finishBufferedWriter.write(finishWriterToFile.append("result")
                        .append(",")
                        .append(resultOperation[POSITION_ALL_SUPPLY_OPERATION]
                                - resultOperation[POSITION_All_BUY_OPERATION])
                        .append("\r\n")
                        .toString());
            } catch (IOException e) {
                throw new RuntimeException("Can't write data file", e);
            }
        }
    }
}
