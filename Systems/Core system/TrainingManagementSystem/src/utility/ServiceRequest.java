/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Home
 */
public class ServiceRequest
{
    public ServiceRequest()
    {
    }
    
    /**
     * Creates a get request and returns a Json Array as a string.
     * @param url
     * @param parameters
     * @return 
     */
    public String get(String url, String parameters)
    {       
        String USER_AGENT = "Mozilla/5.0";       
               
        URL obj = null;
        HttpURLConnection con = null;
        BufferedReader in;
        StringBuilder response = null;
        try
        {            
            obj = new URL(url+parameters);
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();
            
            System.out.println("response code: " + responseCode);
            
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            
            String inputLine;
            response = new StringBuilder();

            while((inputLine = in.readLine()) != null)
            {
                response.append(inputLine);
            }
            in.close();          
        }
        catch (MalformedURLException ex)
        {
            Logger.getLogger(ServiceRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(ServiceRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return response.toString();
    }
    
    public String get(String url)
    {
        return get(url, "");
    }
    
}
