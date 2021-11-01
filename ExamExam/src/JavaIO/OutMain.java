package JavaIO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutMain {
	public static void main(String[] args) {
		try (FileOutputStream output = new FileOutputStream("data/out.txt", true)) {
				output.write(48);
				byte[] value = {65,67,100};
				output.write(value);
		} catch (FileNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
	}
}
