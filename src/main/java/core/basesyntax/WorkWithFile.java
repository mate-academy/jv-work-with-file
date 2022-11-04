package core.basesyntax;

import java.io.*;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {

        File file = new File(fromFileName);
        int result;
        try {
            result = extracted(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
//        int result = calculateResult(supplyCount, buyCount);
        System.out.println("");
    }

    private int extracted(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String value = bufferedReader.readLine();
        int supplyCount = 0;
        int buyCount = 0;
        while (value != null) {
            String[] listFromFile = value.split(",");
            for (int i = 0; i < listFromFile.length; i++) {
                if (listFromFile[0].equals("supply")) {
                    supplyCount = supplyCount + Integer.parseInt(listFromFile[1]);
                }
                if (listFromFile[0].equals("buy")) {
                    buyCount += Integer.parseInt(listFromFile[1]);
                }
            }
            value = bufferedReader.readLine();
        }
        return supplyCount - buyCount;
    }
}
