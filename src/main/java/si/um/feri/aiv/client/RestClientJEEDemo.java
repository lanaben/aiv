package si.um.feri.aiv.client;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import si.um.feri.aiv.vao.Doctor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestClientJEEDemo {
	                                         
    static final URI OSEBE_URI = URI.create("http://localhost:8080/FirstLectures/faces/api/zdravniki/");
    static final URI OSEBE_OSEBA_URI = URI.create("http://localhost:8080/FirstLectures/faces/api/zdravniki/");

//    static final URI OSEBE_URI = URI.create("http://localhost:8080/OsebeDemo/rest/osebe/");
//    static final URI OSEBE_OSEBA_URI = URI.create("http://localhost:8080/OsebeDemo/rest/osebe/oseba/");

    HttpClient client = HttpClient.newBuilder().build();
    Jsonb jsonb= JsonbBuilder.create();

    // void vsiZdravniki() throws Exception {
    //     HttpRequest request = HttpRequest.newBuilder().GET().uri(OSEBE_OSEBA_URI).build();
    //     HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
    //     System.out.println(response.body());
    // }

    void dodaj(Doctor o) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(jsonb.toJson(o)))
                .header("Content-Type","\tapplication/json")
                .uri(OSEBE_OSEBA_URI)
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.discarding());
        System.out.println(response.body());

    }

    public static void main(String[] args) throws Exception {
        Doctor doc = new Doctor("miha", "kor", "mihi.kor@gmail.com", 3);
  
        RestClientJEEDemo client=new RestClientJEEDemo();
        client.dodaj(new Doctor("miha", "kor", "mihi.kor@gmail.com", 3));
        
        System.out.println("Zdravnik je bil uspešno dodan: " + doc.toString());
    }

}
