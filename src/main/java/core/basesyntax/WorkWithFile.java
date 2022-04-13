package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder stringBuilder = new StringBuilder();
            String row = reader.readLine();
            while (row != null) {
                stringBuilder.append(row).append(";");
                row = reader.readLine();
            }
            String[] operationsData = stringBuilder.toString().split(";");
            int supllyAmount = 0;
            int buyAmount = 0;
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            for (String operation : operationsData) {
                if (operation.substring(0,operation.indexOf(",")).equals("supply")) {
                    supllyAmount += Integer.parseInt(operation.substring(7));
                } else {
                    buyAmount += Integer.parseInt(operation.substring(4));
                }
            }
            bufferedWriter.write("supply," + supllyAmount + "\r"
                    + "\nbuy," + buyAmount + "\r" + "\nresult," + (supllyAmount - buyAmount));
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File " + fromFileName + "is not founded!", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName + "!", e);
        }
    }
}
