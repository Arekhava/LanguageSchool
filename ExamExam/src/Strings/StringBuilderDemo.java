package Strings;

public class StringBuilderDemo {
	public static void main(String[] args) {
		StringBuilder builder1 = new StringBuilder();
		StringBuilder builder2 = new StringBuilder(45);
		builder1.append("natallia");
		builder2.append(builder1);
		System.out.println(builder1 + " "+ builder2);
		System.out.println(builder1.equals(builder2)+ " "
		+ (builder1.hashCode()==builder2.hashCode()));
		System.out.println(builder1.toString().contentEquals(builder2));
		/*//builder = "sac";
		System.out.println(builder.length());
		System.out.println(builder.capacity());
		builder.append("hello ");
		System.out.println(builder.length());
		System.out.println(builder.capacity());
		builder.insert(6, "natallia arekhava");
		System.out.println(builder.length());
		System.out.println(builder.capacity());
		//System.out.println(builder.delete(3, 12));
		//System.out.println(builder.reverse());
		System.out.println(builder);
		*/
		
	
		
	}
}
