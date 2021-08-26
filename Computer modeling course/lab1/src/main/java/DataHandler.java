import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataHandler {

    static ArrayList<ArrayList<Double>> data = null;

    public static void readData(String fileName) {
        try(FileReader fr = new FileReader(fileName)) {
            Scanner scanFirst = new Scanner(fr);
            int capacity = 0;
            String substr = "\\w+\\s+\\w+";
            Pattern p = Pattern.compile(substr);
            Matcher m = p.matcher(scanFirst.nextLine().trim());
            while (m.find()) {
                capacity++;
            }
            data = new ArrayList<>(capacity+1);
            for (int i = 0; i < capacity+1; i++) {
                data.add(new ArrayList<>());
            }
        } catch (IOException e) {
        e.printStackTrace();
        }
        try(FileReader fr = new FileReader(fileName)) {
            Scanner scan = new Scanner(fr);
            while (scan.hasNextLine()) {
                String [] words = scan.nextLine().trim().replace(",",".").split("\\s+");
                int count = 0;
                for(int i=0; i<words.length; i++) {
                    if (words[i].matches("^(0|[1-9]\\d*)([.]\\d+)?")) {
                        count++;
                    }
                }
                if(count == words.length) {
                    for (int i = 0; i < words.length; i++) {
                        data.get(i).add(Double.valueOf(words[i]));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processData() {
        for(ArrayList<Double> dataList: data) {
            int n = dataList.size();
            double sum = 0;
            for (double num : dataList) {
                sum += num;
            }
            double matSpod = sum / n;
            double dispersia = 0;
            for (double num : dataList) {
                dispersia += Math.pow((num - matSpod), 2);
            }
            double vidhilenia = Math.sqrt(dispersia / (n-1));
            for (int i = 0; i < dataList.size(); i++) {
                if (!(((matSpod-vidhilenia*3) < dataList.get(i)) && ((matSpod+vidhilenia*3) > dataList.get(i)))) {
                    for (ArrayList<Double> list : data) {
                        list.remove(i);
                    }
                }
            }
        }
    }

    public static double[][] createMas() {
        int n = data.size();
        int m = data.get(0).size();
        double [][] dataMas = new double[m][n];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                dataMas[i][j] = data.get(j).get(i);
            }
        }
        return dataMas;
    }

    public static double [][] getData(String fileName) {
        readData(fileName);
        processData();
        return createMas();
    }
}