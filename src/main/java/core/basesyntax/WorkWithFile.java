package core.basesyntax;

import java.io.*;

import static java.lang.Integer.parseInt;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        createReport(data);
        writeToFile(data, toFileName);
    }
        private String readFromFile (String fromFileName) {
            File file = new File(fromFileName);
            String value = null;
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                StringBuilder stringBuilder = new StringBuilder();
                value = bufferedReader.readLine();
                while (value != null) {
                    stringBuilder.append(value).append(System.lineSeparator());
                    value = bufferedReader.readLine();
                }
                String data = stringBuilder.toString();
                return data;
            } catch (IOException e) {
                throw new RuntimeException("Can't read from a file", e);
            }
        }
        private String createReport (String data) {
        String supply = "supply";
        String buy = "buy";
        int supplyValue = 0;
        int buyValue = 0;
        int result = 0;
        String [] dataFromFile = data.split(",");
            for (int i = 0; i < dataFromFile.length; i++) {


            }

            String firstPart = value.substring(0,commaIndex);
            int secondPart = parseInt(value);
            if (firstPart.equals(supply)) {
                supplyValue += secondPart;
            } else {
                buyValue += secondPart;
            }

        result = supplyValue - buyValue;
    }
}
