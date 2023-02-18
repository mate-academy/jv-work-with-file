package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        try {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        StringBuilder stringBuilder = new StringBuilder();
        String value = null;
            value = bufferedReader.readLine();
            int commaIndex = value.indexOf(',');
            String firstPart = stringBuilder.substring(0,commaIndex);
            int secondPart = stringBuilder(regex\d);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from a file", e);
        }
    }
}
