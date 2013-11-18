package org.markvarabyou.servlets.utilities;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Helper class for agile-tool servlets classes.
 * User: Mark Varabyou
 * Date: 11/18/13
 * Time: 10:24 AM
 */
public class ServletUtilities {
    public static final String DOCTYPE =
            "<!doctype html>";

    public static String headWithTitle(String title) {
        return(DOCTYPE + "\n" +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n");
    }

    public static  String tail(){
        return "</html>";
    }

    /** Read a parameter with the specified name, convert it to an int,
     and return it. Return the designated default value if the parameter
     doesn't exist or if it is an illegal integer format.
     */

    public static int getIntParameter(HttpServletRequest request,
                                      String paramName,
                                      int defaultValue) {
        String paramString = request.getParameter(paramName);
        int paramValue;
        try {
            paramValue = Integer.parseInt(paramString);
        } catch(NumberFormatException nfe) { // Handles null and bad format
            paramValue = defaultValue;
        }
        return(paramValue);
    }

    public static int getIdParameter(HttpServletRequest request) {
        return getIntParameter(request, "id", -1);
    }

    public static String getCookieValue(Cookie[] cookies,
                                        String cookieName,
                                        String defaultValue) {
        for(int i=0; i<cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (cookieName.equals(cookie.getName()))
                return(cookie.getValue());
        }
        return(defaultValue);
    }

    // Approximate values are fine.

    public static final int SECONDS_PER_MONTH = 60*60*24*30;
    public static final int SECONDS_PER_YEAR = 60*60*24*365;
}
