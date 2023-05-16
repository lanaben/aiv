package si.um.feri.aiv.client;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import si.um.feri.aiv.vao.Doctor;
import si.um.feri.aiv.vao.Person;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestClient {
	                                        
    static final URI ZDRAVNIKI_URI = URI.create("http://localhost:8080/FirstLectures/faces/api/zdravniki/");
    static final URI PACIENTI_URI = URI.create("http://localhost:8080/FirstLectures/faces/api/pacienti/");

    static final URI GET_PACIENTI_URI = URI.create("http://localhost:8080/FirstLectures/faces/api/zdravniki/patients/");

    HttpClient client = HttpClient.newBuilder().build();
    Jsonb jsonb= JsonbBuilder.create();

    void dodajZdravnika(Doctor o) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(jsonb.toJson(o)))
                .header("Content-Type","\tapplication/json")
                .uri(ZDRAVNIKI_URI)
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.discarding());
        System.out.println(response.toString());
    }

    void getDoctors() throws Exception {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(ZDRAVNIKI_URI).build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    void getPatients() throws Exception {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(GET_PACIENTI_URI).build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }


    void dodajPacienta(Person o) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(jsonb.toJson(o)))
                .header("Content-Type","\tapplication/json")
                .uri(PACIENTI_URI)
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.discarding());
        System.out.println(response.body());
    }

    public static void main(String[] args) throws Exception {
  
        RestClient client = new RestClient();

        Doctor d = new Doctor("janez", "novak", "janez@gmail.com", 3);
        Doctor d2 = new Doctor("jaka", "novak", "jaka@gmail.com", 3);
        Doctor d3 = new Doctor("miha", "novak", "miha@gmail.com", 3);
        
        client.dodajZdravnika(d);
        client.dodajZdravnika(d2);
        client.dodajZdravnika(d3);

        Person p = new Person("Lana", "Benedičič", "lana.benedicic@gmail.com", "12.12.2000", "test", d);
        Person p2 = new Person("Ana", "Benedičič", "ana.benedicic@gmail.com", "12.12.2000", "test", d2);
        Person p3 = new Person("Sara", "Benedičič", "sara.benedicic@gmail.com", "12.12.2000", "test", d3);

        client.dodajPacienta(p);
        client.dodajPacienta(p2);
        client.dodajPacienta(p3);

        client.getDoctors();
        client.getPatients();
    }

}
