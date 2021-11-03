import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class BIS {
    public static void main(String[] args) throws IOException {

        String serviceKey = "DSIFuujRLymNXuOh6mIiMPZj987S4RiiOlsEVW19xiDT%2BhDxckUEmLiQ42%2BlOJuNJKvmTkfpW%2FuWJwqwfpEdtw%3D%3D";
        StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/buspos/getBusPosByVehId");/*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
        Scanner sc = new Scanner(System.in);
        String vehID;

        System.out.println("차량 ID를 입력하세요");
        vehID = sc.nextLine();

        urlBuilder.append("&" + URLEncoder.encode("vehId","UTF-8") + "=" + URLEncoder.encode("vehID", "UTF-8")); /**/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;

        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    }
}