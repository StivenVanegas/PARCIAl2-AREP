/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arem.server;
import static spark.Spark.port;
import static spark.Spark.get;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.escuelaing.arem.aritmetica.Aritmetica;

/**
 *
 * @author StivenVanegas
 */
public class SparkWebApp {
    
    public static void main(String[] args) {
        port(getPort());
        GsonBuilder builder = new GsonBuilder();
        builder.disableHtmlEscaping();
        Gson gson = builder.create();
        

        get("/hello", (req, res) -> "Hello Docker");
        
        get("/tan", (req, res)-> {
            String s = req.queryParams("value");
            double value = Double.parseDouble(s);
            double tan = Aritmetica.tan(value);
            String json = "[{'operation':'tan', 'input':"+value+", 'output':"+tan+"}]";
            return gson.toJson(json);
            });
        
        get("/acos", (req, res)-> {
            String s = req.queryParams("value");
            double value = Double.parseDouble(s);
            double acos = Aritmetica.acos(value);
            String json = "[{'operation':'acos', 'input':"+value+", 'output':"+acos+"}]";
            return gson.toJson(json);
            });
    }
    
    public static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
    
}
