package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SORT_SUPPLY = "upply";
    private static final String SORT_BUY = "uy";

    public static void getStatistic(String fromFileName, String toFileName) {
        int supplyAmount = 0;
        int byeAmount = 0;
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String fileLine = bufferedReader.readLine();
            while (fileLine != null) {
                if (fileLine.contains(SORT_SUPPLY)) {
                    supplyAmount += Integer.parseInt(fileLine.substring(7));
                } else if (fileLine.contains(SORT_BUY)) {
                    byeAmount += Integer.parseInt(fileLine.substring(4));
                }
                fileLine = bufferedReader.readLine();
            }
            bufferedWriter.write("supply," + supplyAmount + System.lineSeparator()
                    + "buy," + byeAmount + System.lineSeparator()
                    + "result," + (supplyAmount - byeAmount));
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }
}
