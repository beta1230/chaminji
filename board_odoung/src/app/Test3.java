package app;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Random;

import org.mindrot.jbcrypt.BCrypt;

public class Test3 {
	public static void main(String[] args) throws UnsupportedEncodingException{
//		Random random = new Random(1);
//		System.out.println(random.nextDouble());		
//		System.out.println(random.nextDouble());		
//		random = new Random(1);
//		System.out.println(random.nextDouble());		
//		System.out.println(random.nextDouble());
		
		String pw1 = BCrypt.hashpw("00012345", BCrypt.gensalt(5));
		System.out.println(pw1);
		String pw2 = BCrypt.hashpw("12345", BCrypt.gensalt(5));
		System.out.println(pw2);
		
		System.out.println(BCrypt.checkpw("00012345", pw1));
		System.out.println(BCrypt.checkpw("12345", pw2));
		
		Random random = new Random();
		int result = random.nextInt(100000000);
		System.out.println(result);
		
		System.out.println(BCrypt.checkpw("01592581", URLDecoder.decode("%242a%2410%24eiCeLWWPKd3NzIi6ZJRFROMEpvkShD7ERUx5P20euzd81Xii1f42S", "utf-8")));
		
		
	}

}
