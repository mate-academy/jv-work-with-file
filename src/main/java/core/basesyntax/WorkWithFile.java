package core.basesyntax;

import core.basesyntax.calculation.CalculationStatistics;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WorkWithFile {
    private ArrayList<String> str = new ArrayList<>();
    private CalculationStatistics calculation = new CalculationStatistics();

    public void getStatistic(String fromFileName, String toFileName) {
        readFile(fromFileName);
        writeFile(toFileName);
    }

    private void readFile(String fileNameRead) {
        String text;
        try (FileReader fileFrom = new FileReader(fileNameRead)) {
            BufferedReader bufferedReader = new BufferedReader(fileFrom);
            while ((text = bufferedReader.readLine()) != null) {
                str.add(text);
            }
            str.trimToSize();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileNameRead, e);
        }
    }

    private void writeFile(String fileNameWrite) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileNameWrite))) {
            bufferedWriter.write(calculation.getNumber(str));
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileNameWrite, e);
        }
    }
}
