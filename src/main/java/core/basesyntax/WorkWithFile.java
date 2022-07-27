package core.basesyntax;

import java.io.*;

public class WorkWithFile { 
    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = read(fromFileName);
        String[] refactoredData = refactorData(data);
        
        write(toFileName, refactoredData);
    }
    
    public static String[] read(String fileName) {
        try {
            File fileToRead = new File("" + fileName);
            BufferedReader reader = new BufferedReader(new FileReader(fileToRead));
            StringBuilder builder = new StringBuilder();
            String value = reader.readLine();
            
            while (value != null) {
                builder.append(value).append(" ").append(";");
                value = reader.readLine();
            }
            
            return builder.toString().split(";");
        } catch (IOException e) {
            throw new RuntimeException("Can't read file");
        }
    }
    
    public static void write(String fileName, String[] formattedArray) {
        try {
            File fileToWrite = new File("" + fileName);
            
            for (String i : formattedArray) {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToWrite,true));
                bufferedWriter.write(i);
                bufferedWriter.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to a file", e);
        }
    }

    public static String[] refactorData(String[] notRefactoredArray) {
        int supply = 0;
        int buy = 0;
        
        for (int i = 0; i < notRefactoredArray.length; i++) {
            String[] values = notRefactoredArray[i].split(",");
            
            if (values[0].startsWith("supp")) {
                supply += Integer.parseInt(values[1].trim());
            }
            
            if  (values[0].startsWith("bu")) {
                buy += Integer.parseInt(values[1].trim());
            }
        }
        
        int result = supply - buy;
        String builderRes = "supply," + supply + System.lineSeparator() +
                "buy," + buy + System.lineSeparator() +
                "result," + result + System.lineSeparator();
        
        return builderRes.split(" ");
    }
}
