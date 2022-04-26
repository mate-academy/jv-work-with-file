package core.basesyntax;


import java.io.*;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        int allSupply = 0;
        int allBuy = 0;
        String[] tempData = new String[2];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFile));

            String value = reader.readLine();
            while (value != null) {
                tempData = value.split(",");
                switch (tempData[0]) {
                    case BUY:
                        allBuy += Integer.valueOf(tempData[1]);
                    case SUPPLY:
                        allSupply += Integer.valueOf(tempData[1]);
                        break;
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }
}
