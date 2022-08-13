package fr.dimenspace.marstemperature;
import com.fasterxml.jackson.databind.JsonNode;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

public class App {

    private static final String Posts_API_URL = "https://mars.nasa.gov/rss/api/?feed=weather&category=msl&feedtype=json";

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient  client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(Posts_API_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        //parse json into objects
        JsonNode node = Json.parse(response.body());
        JsonNode soles = node.get("soles");
        JsonNode latest = soles.get(0);

        //display strings
        String current_date = LocalDate.now().getYear() + "-" + (LocalDate.now().getMonthValue()) + "-" + (LocalDate.now().getDayOfMonth());

        //title
        System.out.println("___  ___                    _    _            _   _               ");
        System.out.println("|  \\/  |                   | |  | |          | | | |              ");
        System.out.println("| .  . | __ _ _ __ ___     | |  | | ___  __ _| |_| |__   ___ _ __ ");
        System.out.println("| |\\/| |/ _` | '__/ __|    | |/\\| |/ _ \\/ _` | __| '_ \\ / _ \\ '__|");
        System.out.println("| |  | | (_| | |  \\__ \\    \\  /\\  /  __/ (_| | |_| | | |  __/ |   ");
        System.out.println("\\_|  |_/\\__,_|_|  |___/     \\/  \\/ \\___|\\__,_|\\__|_| |_|\\___|_| ");
        //display
        System.out.println("Current date      : " + current_date);
        System.out.println("---------------------------------------------------------------");
        System.out.println("date of the data  : " + latest.get("terrestrial_date"));
        System.out.println("---------------------------------------------------------------");
        System.out.println("Sol number " + latest.get("sol") + " :");
        System.out.println("");
        System.out.println("-Day cycle                                   -Temperatures");
        System.out.println("  Sun rised at : " + latest.get("sunrise") + "                          Min : " + latest.get("min_temp") + " °C");
        System.out.println("  Sun sat at   : " + latest.get("sunset") + "                          Max : " + latest.get("max_temp") + " °C");
        System.out.println("");
        System.out.println("Local UV irradiance index : " + latest.get("local_uv_irradiance_index"));
        System.out.println("Atmosphere Opacity : " + latest.get("atmo_opacity"));
        System.out.println("Pressure : " + latest.get("pressure") + " Pa");
        System.out.println("---------------------------------------------------------------");
        System.out.println("Season : " + latest.get("season"));
        System.out.println("Id : " + latest.get("id"));

    }
}

//made by DimenSpace
//