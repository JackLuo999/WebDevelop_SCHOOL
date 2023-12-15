import  java.net.*;
import  java.io.*;
import  java.util.*;
import  java.util.regex.*;


/*  
 *
 */

public  class  SimpleWebServer {

    final  static  int  SOCKET_NUMBER = 7601;



    public static void main (String argv[]) {

        String  htmlHeader1NotFound = "HTTP/1.1 404 Not Found\r\n";
        String  htmlHeader1 = "HTTP/1.1 200 OK\r\n";
        String  htmlHeader2 = "Date: Tue, 23 Feb 2016 16:41:01 GMT\r\n";
        String  htmlHeader3 = "Connection: close\r\n";
        String  htmlHeader4 = "Content-Type: text/html\r\n";

// The following String will be overriden later
//
        String  htmlHeader5 = "Content-Length:      \r\n\r\n";

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


//  Looking for the GET line and extract the url









//  generate the appropriate html page
//

                boolean  keywordFound = false;
                String   htmlContent; 

                if  (url.startsWith("/simple"))  {
                    keywordFound = true;
                    htmlContent = generateSimplePage(); 
                }
                else if  (url.startsWith("/hello"))  { 
                    keywordFound = true;
                    htmlContent = generateForm(); 
                }
                else if  (url.startsWith("/verify"))  { 
                    keywordFound = true;
                    htmlContent = generateVerifyPage(url); 
                }
                else  {
                    htmlContent = generateNotFoundPage(url); 
                }


                System.out.println ("\nNow sending html to client\n");

            
//      override the predefined Content-Length parameter.
//

//              htmlHeader5 = "Content-Length:  " + contentLen + "\r\n\r\n";

                if  (keywordFound)  {
                    networkOut.writeBytes (htmlHeader1);
                }
                else  {
                    networkOut.writeBytes (htmlHeader1NotFound);
                }
                networkOut.writeBytes (htmlHeader2);
                networkOut.writeBytes (htmlHeader3);
                networkOut.writeBytes (htmlHeader4);
                networkOut.writeBytes (htmlHeader5);

                networkOut.writeBytes (htmlContent);

                networkOut.flush();

                networkOut.close();
		connectSocket.close();
	    }
            catch (IOException e) {
	        // Lazy exception handling;

            } //end try


        } //end for loopCounter

    } //end main


    static  String  generateForm ()  {

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

        return (htmlForm1 + htmlForm2 + htmlForm3 + htmlForm4 + htmlForm5);     

    } //end generateForm


    static  String  generateSimplePage ()  {

        String  htmlText1 = "<html> <head> <title>A simple page</title> </head> \r\n";
        String  htmlText2 = "<body> <h1>Welcome to a Simple Page!</h1> <hr>\r\n"
                     + " <p> This paragraph will display on your web browser.  It \r\n" 
                     + "shows that text can be entered in free form without \r\n"
                     + "any fancy formatting in the html source file. </p>\r\n";
        String  htmlText3 = "<center><b>February 23, 2016 \r\n"
                     + "</b></center> \r\n </body> </html>                      \r\n";

        return (htmlText1 + htmlText2 + htmlText3);     

    } //end generateSimplePage


    static  String  generateVerifyPage (String url)  {

/* first extract the parameters from the  id=   and  lang=
     of the url and store them in the following strings */

        String   idFromClient = "";
        String   langFromClient = "";
        

/* generating html */

        String  htmlText1 = "<html> ";
        String  htmlText2 = "<body> <h1>Welcome, "
                              + idFromClient 
                              + ", to the cptg255 programming team.</h1>\r\n"; 
        String  lang = "";
        if  (langFromClient.equals("cplusplus"))  {
            lang = "C++";
        }  else if  (langFromClient.equals("csharp"))  {
            lang = "C#";
        }  else if  (langFromClient.equals("vbasic"))  {
            lang = "Visual BASIC";
        }  else if  (langFromClient.equals("java"))  {
            lang = "Java";
        }  else if  (langFromClient.equals("perl"))  {
            lang = "Perl";
        }  
        String  htmlText3 = "<p> I am glad you like to program in " 
                               + lang
                               + ".</p>"
                               + "</body> </html> \r\n";



        String  htmlTEMP = "<html> <head> <title> Under Construction </title> </head> \r\n"
                              + "<body>  <p> This is under construction. </p> \r\n"
                              + "</body> </html>   \r\n";

        return (htmlTEMP);

//        return (htmlText1 + htmlText2 + htmlText3);     

    } //end generateVerifyPage


    static  String  generateNotFoundPage (String url)  {

        String  htmlText1 = "<html> <body> \r\n";
        String  htmlText2 = "<h1>Not Found</h1>"
                     + "<p>The requested URL "
                     + url
                     + " was not found on this server.</p>\r\n";
        String  htmlText3 = "</body> </html> \r\n";

        return (htmlText1 + htmlText2 + htmlText3);     

    } //end generateNotFoundPage


} //end class SimpleWebServer



