import  java.net.*;
import  java.io.*;
import  java.util.*;


public  class  EchoClient {

    public static void main (String argv[]) {

        int  visitorNumber;


        ServerSocket  listeningSocket = null;

	try {
            listeningSocket = new ServerSocket(6644);
	}
        catch (IOException e) {
	    System.out.println ("Fail to allocate IP port 6644\n"
				        + "Error type: " + e);
	    System.exit(1);

        } //end try


	for  (visitorNumber = 1; visitorNumber <= 5; visitorNumber++)  {

            Socket  connectSocket = null;
	    try {
                connectSocket = listeningSocket.accept();
                System.out.println ("Incoming Client # " + visitorNumber);
	    }
            catch (IOException e) {
	        System.out.println ("Fail to accept connection at IP port 6644\n"
				        + "Error type: " + e);
	        System.exit(1);

            } //end try

            try {
                DataOutputStream  networkOut = new DataOutputStream(
				                    connectSocket.getOutputStream());

/*  insert here code to capture data
     send from the client and echo each
     line on the console */
 


/*  send a reply to client */

                networkOut.writeBytes ("Thanks for visiting\n");
                networkOut.flush();


                networkIn.close();
                networkOut.close();
		connectSocket.close();
	    }
            catch (IOException e) {
	        // Lazy exception handling;

            } //end try
        } //end for

    } //end main
} //end class EchoClient

