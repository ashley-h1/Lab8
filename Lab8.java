import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.text.DecimalFormat;
//read pay csv, calculate weekly pay with overtime, write the new data into output csv
//2 decimal places w/ $ and update column headings
public class Lab8{
    public static void main(String[]args) throws IOException{
        
        String filePath = "C:/Users/NCG User/java/Lab8/pay.csv";
        String writerPath = "C:/Users/NCG User/java/Lab8/output.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            BufferedWriter writer = new BufferedWriter(new FileWriter(writerPath));
            String line;
            try{
                while ((line = reader.readLine()) != null){
                    String[] data = line.split(",");
                    for (int i = 0; i < data.length; i++){
                        writer.write(data[i] + ",");
                        if (data[i].equals("HoursWorked")){

                            writer.write("WeeklyPay");
                        }
                    }
                    //if the line has numbers, convert them to doubles, then calculate the weekly pay
                    //and write it into the output csv
                    try{
                        double rate = Double.parseDouble(data[1]);
                        double hours = Double.parseDouble(data[2]);
                        double overtime = hours - 40;
                        double weekly = (rate*hours)+((rate*1.5)*overtime);
                        DecimalFormat f = new DecimalFormat("##.00");
                        writer.write("$" + f.format(weekly));
                    }
                    catch(NumberFormatException e){
                        //System.out.println("Heading");
                    }
                    writer.newLine();
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
            finally{
                reader.close();
                writer.close();
            }
        }
    }
}