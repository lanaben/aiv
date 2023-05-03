package si.um.feri.aiv.client;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import si.um.feri.aiv.vao.Doctor;
import si.um.feri.aiv.vao.Person;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestClientJEEDemo {
	                                        
    static final URI ZDRAVNIKI_URI = URI.create("http://localhost:8080/FirstLectures/faces/api/zdravniki/");
    static final URI PACIENTI_URI = URI.create("http://localhost:8080/FirstLectures/faces/api/pacienti/");

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

    void getDoctor(String email) throws Exception {
        URI ZDRAVNIKI_URI_MAIL = URI.create("http://localhost:8080/FirstLectures/faces/api/zdravniki/"+email);
        HttpRequest request = HttpRequest.newBuilder().GET().uri(ZDRAVNIKI_URI_MAIL).build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    void getDoctors() throws Exception {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(ZDRAVNIKI_URI).build();
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
  
        RestClientJEEDemo client = new RestClientJEEDemo();

        client.getDoctors();

        Doctor d = new Doctor("miha", "kor", "mihi.kor@gmail.com", 3);

        client.dodajZdravnika(d);

        // client.getDoctor(d.getEmail());

        // System.out.println("Zdravnik je bil uspešno dodan: " + d.toString());

        Person p = new Person("Lana", "Benedičič", "lana.benedicic@gmail.com", "12.12.2000", "test", d);

        client.dodajPacienta(p);

        // System.out.println("Pacient je bil uspešno dodan: " + p.toString());

    }

}
