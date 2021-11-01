package JavaIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class InputDemo {
	public static void main(String[] args) {
		FileInputStream input = null;
		try {
			input = new FileInputStream("data/info.txt");
			try {
				int  code = input.read();
				byte[] arr = new byte[16];
				int num = input.read(arr);
				System.out.println("num = " + num);
				System.out.println(Arrays.toString(arr));
				System.out.println(code + " char = " + (char)code);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
		if(input != null)
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
				}
		}
	}
}

