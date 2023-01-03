package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String rezalt = readFile(fromFileName);
        writeFile(toFileName, rezalt);
    }

    private String readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String str = bufferedReader.readLine();
            if (str == null || str.length() == 0) {
                return "";
            }
            int sumBuy = 0;
            int sumSupply = 0;
            int result;
            while (str != null) {
                String[] strSplit = str.split(",");
                if (strSplit[0].length() > 0) {
                    if (strSplit[0].equals("supply")) {
                        sumSupply += Integer.parseInt(strSplit[1]);
                    } else {
                        sumBuy += Integer.parseInt(strSplit[1]);
                    }
                }
                str = bufferedReader.readLine();
            }
            result = sumSupply - sumBuy;
            builder.append("supply,").append(sumSupply).append(System.lineSeparator());
            builder.append("buy,").append(sumBuy).append(System.lineSeparator());
            builder.append("result,").append(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (Exception ex) {
                System.out.println("Error in closing the BufferedWriter" + ex);
            }
        }
        return builder.toString();
    }

    private void writeFile(String toFileName, String rezalt) {
        BufferedWriter bufferedWriter = null;
        try {
            File file = new File(toFileName);

            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fw);
            bufferedWriter.write(rezalt);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (Exception ex) {
                System.out.println("Error in closing the BufferedWriter" + ex);
            }
        }
    }
}
