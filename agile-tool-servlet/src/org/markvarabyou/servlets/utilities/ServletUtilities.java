package org.markvarabyou.servlets.utilities;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Base class for servlets from application.
 * User: Mark Varabyou
 * Date: 11/18/13
 * Time: 11:53 PM
 */
public class ServletUtilities {
    public Integer getIdFromRequestParameter(HttpServletRequest request){
        String paramString = request.getParameter("id");
        if (paramString == null)
            return null;
        return Integer.parseInt(paramString);
    }

    public void setJsonToResponseBody(HttpServletResponse response, String json) {
        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (out != null) {
            out.print(json);
        }
    }

    public String getJsonFromRequestBody(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(
                        inputStream));
                char[] charBuffer = new char[128];
                int bytesRead;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }
}
