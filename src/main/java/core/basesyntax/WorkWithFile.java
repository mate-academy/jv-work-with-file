package core.basesyntax;

import java.io.*;
import java.util.Arrays;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't open the file" + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file" + fromFileName, e);
        }

        String data = builder.toString();
//        System.out.println(data);
        String[] arrayData = data.split(System.lineSeparator());


    }
}
