package no.hvl.dat110.rpc;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.*;

import java.nio.ByteBuffer;

public class RPCClient {

    // underlying messaging client used for RPC communication
    private MessagingClient msgclient;

    // underlying messaging connection used for RPC communication
    private MessageConnection connection;

    public RPCClient(String server, int port) {

        msgclient = new MessagingClient(server, port);
    }

    public void connect() {
        // connect using the RPC client
        connection = msgclient.connect();
    }

    public void disconnect() {

        // disconnect by closing the underlying messaging connection
        connection.close();
    }

	/*
	 Make a remote call on the method on the RPC server by sending an RPC request message and receive an RPC reply message

	 rpcid is the identifier on the server side of the method to be called
	 param is the marshalled parameter of the method to be called
	 */

    public byte[] call(byte rpcid, byte[] param) {

        byte[] returnval = null;
		/*

		The rpcid and param must be encapsulated according to the RPC message format

		The return value from the RPC call must be decapsulated according to the RPC message format

		*/
        byte[] toSend = ByteBuffer.allocate(param.length + 1).put(rpcid).put(param).array();
        connection.send(new Message(toSend));
        returnval = connection.receive().getData();

        return returnval;
    }

}
