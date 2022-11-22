package core.basesyntax;

import javax.imageio.IIOException;
import java.io.*;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File fileFrom = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        int counter = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileFrom))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
                counter++;
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file ",e);
        }

        int supply = 0;
        int buy = 0;
        String[] operationType = new String[counter];
        int[] amount = new int[counter];
        String[] resultString = stringBuilder.toString().split("\\W+");

        for (int i = 0; i < resultString.length; i++) {
            if(i % 2 == 0) {
                operationType[i / 2] = resultString[i];
            } else {
                amount[(i - 1) / 2] = Integer.parseInt(resultString[i]);
            }

        }
        for (int i = 0; i < counter; i++) {
            for (int j = 1; j < counter; j++){
                if(operationType[i].equals(operationType[j]) && !operationType[i].equals("") && i!=j) {
                    amount[i] += amount[j];
                    operationType[j] = "";
                }
            }
        }
        File fileTo = new File(toFileName);
        for (int i = 0; i < operationType.length; i++){
            if (operationType[i].equals("")) break;
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTo, true))){
                bufferedWriter.write(operationType[i] + "," + amount[i] + System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file", e);
            }
        }

    }
}
