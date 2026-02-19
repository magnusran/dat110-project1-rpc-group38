package no.hvl.dat110.messaging;

import java.nio.ByteBuffer;
import java.util.Arrays;

import no.hvl.dat110.TODO;

public class MessageUtils {

	public static final int SEGMENTSIZE = 128;

	public static int MESSAGINGPORT = 8080;
	public static String MESSAGINGHOST = "localhost";

	public static byte[] encapsulate(Message message) {
		// encapulate/encode the payload data of the message and form a segment
		// according to the segment format for the messaging layer

		byte[] data = message.getData();
        return ByteBuffer.allocate(128).put((byte)data.length).put(data).array();
		
	}

	public static Message decapsulate(byte[] segment) {
		// decapsulate segment and put received payload data into a message
		ByteBuffer buf = ByteBuffer.wrap(segment);
		int dataLength = buf.get(0);
		byte[] data = Arrays.copyOfRange(segment, 1, 1 + dataLength);

        return new Message(data);
	}
	
}
