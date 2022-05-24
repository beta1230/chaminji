package app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ThumbTest {
	public static void main(String[] args) throws IOException {
		Thumbnails
			.of(new File("C:\\Users\\human\\Desktop\\thumb","origin.jpg"))
			.sourceRegion(Positions.CENTER, 200, 200)
			.size(200, 200)
			.toFile(new File("C:\\Users\\human\\Desktop\\thumb", "result4.jpg"));
		
//		File file = new File("C:\\Users\\human\\Desktop\\thumb","origin.jpg");
//		String contentType = Files.probeContentType(file.toPath());
//		System.out.println(contentType.startsWith("image"));
	}

}
