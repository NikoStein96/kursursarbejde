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
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import threads.Call;

@Path("starwars")
public class StarWars {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    public StarWars() {
    }

    @GET
    @Path("/people/arrayOfPeople")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPeopleByArray(@QueryParam("list") final List<Integer> list) throws IOException, InterruptedException, TimeoutException, ExecutionException {
        
        List<Call> callList = new ArrayList();
        ExecutorService es = Executors.newFixedThreadPool(5);
        ExecutorCompletionService<String> completionService = new ExecutorCompletionService(es);
        List<String> jsonString = new ArrayList();
        List<Future<String>> futureList;
        
        for (Integer id : list) {
            Call callable = new Call(id);
            callList.add(callable);
        }
        futureList = es.invokeAll(callList);
        for (Future<String> fut : futureList) {
            String res = fut.get(10, TimeUnit.MILLISECONDS);
            jsonString.add(res);
        }
//        for (int i = 0; i < list.size(); ++i) {
//            final Future<String> future = completionService.take();
//            try {
//                jsonString.add(future.get(10, TimeUnit.MILLISECONDS));
//            } catch (ExecutionException e) {
//                System.out.println(e);
//            }
//        }
        if (es.shutdownNow().isEmpty()) {
            System.out.println("DONE");
        } else {
            System.out.println("UNCOMPLETE TASKS");
        }
        
        String json = "{swPersons: [ \n"
                + "{ \n"
                + "firstPerson: {" + jsonString.get(0) + "}, \n"
                + "secondPerson: {" + jsonString.get(1) + "}, \n"
                + "thirdPerson: {" + jsonString.get(2) + "}, \n"
                + "fourthPerson: {" + jsonString.get(3) + "}, \n"
                + "fifthPerson: {" + jsonString.get(4) + "} \n"
                + "} \n"
                + "] \n"
                + "} \n";
        return json;

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
