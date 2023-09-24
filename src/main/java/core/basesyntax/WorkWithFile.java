package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fromFileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't read data from the file", e);
        }
        byte[] buffer = new byte[10];
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                if (fis.read(buffer) == -1) {
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException("Can't read data from the file", e);
            }
            sb.append(new String(buffer));
            buffer = new byte[10];
        }
        try {
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file", e);
        }
        String content = sb.toString();
        String nw = content.replaceAll("[.!/?*',]", " ").toLowerCase();
        String n = nw.replaceAll("[\n\r]", " ");
        String[] splt = n.split(" ");
        int allSupply = 0;
        int allBuy = 0;
        int result = 0;
        for (int i = 0; i < splt.length; i += 2) {
            if (splt[i].equals("")) {
                i -= 1;
                continue;
            }
            if (splt[i].equals("buy")) {
                allBuy += Integer.parseInt(splt[i + 1]);
            }
            if (splt[i].equals("supply")) {
                allSupply += Integer.parseInt(splt[i + 1]);
            }
        }
        result = allSupply - allBuy;
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(toFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file", e);
        }
        try {
            writer.write("supply,");
            writer.write(Integer.toString(allSupply));
            writer.write(System.lineSeparator());
            writer.write("buy,");
            writer.write(Integer.toString(allBuy));
            writer.write(System.lineSeparator());
            writer.write("result,");
            writer.write(Integer.toString(result));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file", e);
        }
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file", e);
        }
    }
}
