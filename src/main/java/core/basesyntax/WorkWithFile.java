package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        int result;
        Handler handler = null;
        try {
            List<String> fileContent = readFile(fromFile);
            handler = handle(handler, fileContent);
            result = handler.supplies - handler.buyes;
        } catch (IOException e) {
            throw new RuntimeException("Can't read this file", e);
        }
        writeFile(toFile, handler.supplies, handler.buyes, result);
    }

    private static Handler handle(Handler handler, List<String> fileContent) {
        int supplies = 0;
        int buys = 0;
        for (String content : fileContent) {
            String action = content.split(",")[0];
            String sum = content.split(",")[1];
            if (action.equals("supply")) {
                supplies += Integer.parseInt(sum);
            }
            if (action.equals("buy")) {
                buys += Integer.parseInt(sum);
            }
            handler = new Handler(supplies, buys);
        }
        return handler;
    }

    private static class Handler {
        public final int supplies;
        public final int buyes;

        public Handler(int supplies, int buyes) {
            this.supplies = supplies;
            this.buyes = buyes;
        }
    }

    private static void writeFile(File toFile, int supplies, int buyes, int result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            String builder = "supply," + supplies + System.lineSeparator()
                    + "buy," + buyes + System.lineSeparator()
                    + "result," + result + System.lineSeparator();
            bufferedWriter.write(builder);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data", e);
        }
    }

    private static List<String> readFile(File fromFile) throws IOException {
        return Files.readAllLines(fromFile.toPath());
    }
}
