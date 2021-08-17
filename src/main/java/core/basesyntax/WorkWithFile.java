package core.basesyntax;

import javax.naming.NamingEnumeration;
import java.io.*;
import java.util.regex.Pattern;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String inputFile = fileReader(fromFileName);
        String resultString = productCount(inputFile);


    }

    private String fileReader(String fromFileName) {
        File file = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String productCount(String file) {
        StringBuilder result = new StringBuilder();
        while (!file.equals("")) {
            String[] fileArray = file.split(" ");
            String name = fileArray[0].replaceAll(",\\d+", "");
            int amount = 0;
            for (String fileString: fileArray) {
                String[]fileStringArray = fileString.split(",");
                if (name.equals(fileStringArray[0])) {
                    amount += Integer.parseInt(fileStringArray[1]);
                }
            }
            result.append(name).append(",").append(amount).append(System.lineSeparator());
            file = file.replaceAll(Pattern.quote(name) + ",\\d+[ ]?", "");
        }
        return result.toString();
    }

    private void fileWriter(String fileName, String resultString) {
        File outputFile = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {

            for (String string: resultString.split(" ") ) {
                bufferedWriter.write(string);
                bufferedWriter.write(System.lineSeparator());
            }



        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
