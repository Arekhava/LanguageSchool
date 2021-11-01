package JavaIO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class ReaderMain {
	public static void main(String[] args) {
		try (FileReader reader = new FileReader("data/info.txt")){
			int symbol = reader.read();
			System.out.println(symbol);
			char[] buffer = new char[8];
			reader.skip(2);
			reader.read(buffer);
			System.out.println(Arrays.toString(buffer));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		/*}finally {
			try {
				if (reader != null) {
				reader.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		
			}*/
		}
	}

}
