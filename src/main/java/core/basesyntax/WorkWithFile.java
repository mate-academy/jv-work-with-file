package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String CSV_SEPARATOR = ",";

    private String getResult(int[] cases) {
        return "supply" + CSV_SEPARATOR + cases[0] + System.lineSeparator()
                + "buy" + CSV_SEPARATOR + cases[1] + System.lineSeparator()
                + "result" + CSV_SEPARATOR + (cases[0] - cases[1]);
    }

    public int[] setStatistic(String fromFileName) {
        int buy = 0;
        int supply = 0;
        try (BufferedReader fromFile = new BufferedReader(new FileReader(fromFileName))) {
            String data = fromFile.readLine();
            while (data != null) {
                String[] info = data.split(CSV_SEPARATOR);
                switch (info[0]) {
                    case "buy": buy += Integer.parseInt(info[1]);
                        break;
                    case "supply":supply += Integer.parseInt(info[1]);
                        break;
                    default:
                        break;
                }
                data = fromFile.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file, ", e);
        }

        return new int[]{supply, buy};
    }

    public void getStatistic(String fromFileName, String toFileName) {
        File toFileObject = new File(toFileName);
        try (BufferedWriter toFile = new BufferedWriter(new FileWriter(toFileObject))) {
            toFile.write(getResult(setStatistic(fromFileName)));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data, ", e);
        }
    }
}
