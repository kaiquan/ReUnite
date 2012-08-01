package View.PRFM;

public class Try {
	public static void main(String[] args){
		String temp = "fc3sa";
		temp = temp.replaceAll("\\s+", " ");
		System.out.println (temp);
		System.out.println (temp.matches("^[a-zA-Z]+$"));
	}
}
