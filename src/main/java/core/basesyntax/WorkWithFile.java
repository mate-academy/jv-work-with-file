package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String CSV_SEPARATOR = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private int buy;
    private int supply;

    public String getResult() {
        return "supply" + CSV_SEPARATOR + this.supply + LINE_SEPARATOR
             + "buy" + CSV_SEPARATOR + this.buy + LINE_SEPARATOR
             + "result" + CSV_SEPARATOR + (this.supply - this.buy);
    }

    public void setStatistic(String fromFileName) {
        try (BufferedReader from = new BufferedReader(new FileReader(fromFileName))) {
            String data = from.readLine();
            while (data != null) {
                String[] info = data.split(CSV_SEPARATOR);
                switch (info[0]) {
                    case "buy": this.buy += Integer.parseInt(info[1]);
                        break;
                    case "supply": this.supply += Integer.parseInt(info[1]);
                        break;
                    default:
                        break;
                }
                data = from.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file, " + e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        setStatistic(fromFileName);
        File toFile = new File(toFileName);
        try (BufferedWriter to = new BufferedWriter(new FileWriter(toFile))) {
            to.write(getResult());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data, " + e);
        }

    }
}
