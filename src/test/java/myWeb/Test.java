package myWeb;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test {
	
	public static void main(String[] args) throws IOException {
		FileChannel fc = new FileInputStream("F:/workplace/sql/heihei.sql").getChannel();
		ByteBuffer buf = ByteBuffer.allocate(48);
		fc.read(buf);
		buf.flip();
		while(buf.hasRemaining()) {
			System.out.println(buf.getChar());
		}
	}
}
