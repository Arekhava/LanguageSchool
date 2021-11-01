package JavaIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReaderWriterMain {
	public static void main(String[] args) {
		try (FileReader reader = new FileReader("data/info.txt");
				BufferedReader bufferedReader = new BufferedReader(reader);
			FileWriter writer = new FileWriter("data/out.txt");
					BufferedWriter bufferedWriter = new BufferedWriter(writer)){
			String line ;
			while((line = bufferedReader.readLine()) !=null) {
			bufferedWriter.write(line, 0, line.length());
			bufferedWriter.newLine();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}


