import  java.net.*;
import  java.io.*;
import  java.util.*;

/*  
 *
 */

public  class  HtmlPageServer {

    public static void main (String argv[]) {

        final   int  SOCKET_NUMBER = 7601;


        String  htmlHeader1 = "HTTP/1.1 200 OK\r\n";
        String  htmlHeader2 = "Date: Tue, 23 Feb 2016 16:41:01 GMT\r\n";
        String  htmlHeader3 = "Connection: close\r\n";
        String  htmlHeader4 = "Content-Type: text/html\r\n";
        String  htmlHeader5 = "Content-Length:   354\r\n\r\n";

        String  htmlText1 = "<html> <head> <title>A simple page</title> </head> \r\n";
        String  htmlText2 = "<body> <h1>Welcome to a Simple Page!</h1> <hr>\r\n"
                      + " <p> This paragraph will display on your web browser.  It \r\n" 
                      + "shows that text can be entered in free form without \r\n"
                      + "any fancy formatting in the html source file. </p>\r\n";
        String  htmlText3 = "<center><b>February 23, 2016 \r\n"
                      + "</b></center> \r\n </body> </html>                      \r\n";

        int   loopCounter;

        ServerSocket  listeningSocket = null;

	try {
            listeningSocket = new ServerSocket(SOCKET_NUMBER);
	}
        catch (IOException e) {
	    System.out.println ("Fail to allocate IP port " 
                                   + SOCKET_NUMBER + "\n Error type: " + e);
	    System.exit(1);

        } //end try


        System.out.println ("Server is now listening on port " + SOCKET_NUMBER);


        for (loopCounter = 1;  loopCounter <= 10;  loopCounter++)
        {

            Socket  connectSocket = null;
	    try {
                connectSocket = listeningSocket.accept();
	    }
            catch (IOException e) {
	        System.out.println ("Fail to accept connection at IP port "
                                   + SOCKET_NUMBER + "\n Error type: " + e);
	        System.exit(1);

            } //end try

            try {


		Scanner  networkIn = new Scanner(connectSocket.getInputStream());

                DataOutputStream  networkOut = new DataOutputStream(
				                    connectSocket.getOutputStream());

/* handler input here */

                System.out.println ("\nNow sending html to client\n");

                networkOut.writeBytes (htmlHeader1);
                networkOut.writeBytes (htmlHeader2);
                networkOut.writeBytes (htmlHeader3);
                networkOut.writeBytes (htmlHeader4);
                networkOut.writeBytes (htmlHeader5);

                networkOut.writeBytes (htmlText1);
                networkOut.writeBytes (htmlText2);
                networkOut.writeBytes (htmlText3);
                networkOut.flush();

                networkOut.close();
		connectSocket.close();
	    }
            catch (IOException e) {
	        // Lazy exception handling;

            } //end try


        } //end for loopCounter


    } //end main
} //end class HtmlPageServer



