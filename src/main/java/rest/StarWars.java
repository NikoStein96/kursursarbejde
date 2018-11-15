package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("starwars")
public class StarWars {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    public StarWars() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() throws IOException {
        
        String starwars = getSwappiData(1);
        starwars += getSwappiData(2);
        starwars += getSwappiData(3);
        starwars += getSwappiData(4);
        starwars += getSwappiData(5);

        return starwars;
    }

    public String getSwappiData(int id) throws MalformedURLException, IOException {
        URL url = new URL("https://swapi.co/api/people/" + id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        con.setRequestProperty("User-Agent", "server");
        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = null;
        if (scan.hasNext()) {
            jsonStr = scan.nextLine();
        }
        scan.close();
        return jsonStr;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
