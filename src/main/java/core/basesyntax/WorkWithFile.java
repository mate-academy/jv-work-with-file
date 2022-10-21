package core.basesyntax;

import java.io.*;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String value = bufferedReader.readLine();
            while (value != null) {
                value = bufferedReader.readLine();
                String[] listFromFile = value.split(",");
                for (int i = 0; i < listFromFile.length; i++) {

                }
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }
}
