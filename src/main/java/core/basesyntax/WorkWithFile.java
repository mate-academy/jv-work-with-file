package core.basesyntax;

import java.io.*;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyCount = 0;
        int buyCount = 0;
        int result = 0;
        File file = new File(fromFileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String value = bufferedReader.readLine();
            while (value != null) {
                value = bufferedReader.readLine();
                if (value != null) {
                    String[] listFromFile = value.split(",");
                    for (int i = 0; i < listFromFile.length; i++) {
                        if (listFromFile[0].equals("supply")) {
                            supplyCount += Integer.parseInt(listFromFile[1]);
                        }
                        if (listFromFile[0].equals("buy")) {
                            buyCount += Integer.parseInt(listFromFile[1]);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        result = supplyCount - buyCount;
    }
}