import  java.net.*;
import  java.io.*;
import  java.util.*;

/*  
 *
 */

public  class  SendForm {

    public static void main (String argv[]) {

        final   int  SOCKET_NUMBER = 7601;


        String  htmlHeader1 = "HTTP/1.1 200 OK\r\n";
        String  htmlHeader2 = "Date: Tue, 23 Feb 2016 16:41:01 GMT\r\n";
        String  htmlHeader3 = "Connection: close\r\n";
        String  htmlHeader4 = "Content-Type: text/html\r\n";

// The following String will be overriden later
//
        String  htmlHeader5 = "Content-Length:      \r\n\r\n";

        String  htmlForm1 = "<html>\r\n"
                     + "<head><title>Greetings for Users</title></head>\r\n"

                     + "<body>"
	             + "<form name =\"greeting\" "
	             + "action=\"http://10.15.14.26:" + SOCKET_NUMBER + "/verify\" "
	             + "method=\"GET\">\r\n";

        String  htmlForm2 = 
                       "<p>"
                     + "What is your name?  <input type=text  name=\"id\"  size=30>"
	             + "</p>\r\n";

        String  htmlForm3 = 
                       "<p>"
	             + "Confirm your class standing<br>\r\n"
	             + "<input type=\"radio\" name=\"classstanding\" "
	             +    "value=\"freshmen\">Freshmen<br>"
	             + "<input type=\"radio\" name=\"classstanding\" "
	             +    "value=\"sophomore\">Sophomore<br>"
	             + "<input type=\"radio\" name=\"classstanding\" "
	             +    "value=\"junior\">Junior<br>"
	             + "<input type=\"radio\" name=\"classstanding\" "
	             +    "value=\"senior\">Senior<br>"
	             + "</p>\r\n";

        String  htmlForm4 = 
	               "<p>"
	             + "Pick your favorite language<br>\r\n"
	             + "<select  name=\"lang\">"
	             +    "<option  value=\"cplusplus\">C++</option>"
	             +    "<option  value=\"csharp\">C#</option>"
	             +    "<option  value=\"vbasic\">Visual BASIC</option>"
	             +    "<option  value=\"java\">Java</option>"
	             +    "<option  value=\"perl\">Perl</option>"
	             + "</select>\r\n"
	             + "</p>\n";

        String  htmlForm5 = 
	               "<p>"
	             +   "<input type=\"submit\" value=\"Go\">\r\n"
	             + "</p>\r\n"
                     + "</form>"
	             + "</body>"
	             + "</html>\r\n";

        int   loopCounter;

/* modify htmlHeader5 here */
        htmlHeader5 = 


        ServerSocket  listeningSocket = null;

	try {
            listeningSocket = new ServerSocket(SOCKET_NUMBER);
	}
        catch (IOException e) {
	    System.out.println ("Fail to allocate IP port " 
                                   + SOCKET_NUMBER + "\n Error type: " + e);
	    System.exit(1);

        } //end try


        System.out.println ("\nServer is now listening on port " + SOCKET_NUMBER + "\n");



        for (loopCounter = 1;  loopCounter <= 5;  loopCounter++)
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


/* Handle input buffer here */


                System.out.println ("\nNow sending html to client\n");

                networkOut.writeBytes (htmlHeader1);
                networkOut.writeBytes (htmlHeader2);
                networkOut.writeBytes (htmlHeader3);
                networkOut.writeBytes (htmlHeader4);
                networkOut.writeBytes (htmlHeader5);


                networkOut.writeBytes (htmlForm1);
                networkOut.writeBytes (htmlForm2);
                networkOut.writeBytes (htmlForm3);
                networkOut.writeBytes (htmlForm4);
                networkOut.writeBytes (htmlForm5);


                networkOut.flush();

                networkOut.close();
		connectSocket.close();
	    }
            catch (IOException e) {
	        // Lazy exception handling;

            } //end try


        } //end for loopCounter


    } //end main
} //end class SendForm



