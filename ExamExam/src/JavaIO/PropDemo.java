package JavaIO;

import java.util.Properties;

public class PropDemo {
	public static void main(String[] args) {
		Properties prop = System.getProperties();
		prop.list(System.out);
	}

}
