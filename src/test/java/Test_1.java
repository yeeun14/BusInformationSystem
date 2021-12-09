//노선정보 API는 json의 형태를 제공하지 않는다.

//import jdk.nashorn.internal.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Test_1 {
    public static void main(String[] args) {
        // 인증키 (개인이 받아와야함)
        String key = "DSIFuujRLymNXuOh6mIiMPZj987S4RiiOlsEVW19xiDT%2BhDxckUEmLiQ42%2BlOJuNJKvmTkfpW%2FuWJwqwfpEdtw%3D%3D";

        // 파싱한 데이터를 저장할 변수
        String result = "Test";

        try {

            URL url = new URL("http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList?key="
                    + key + "&strSrch=130" + "&_json");

            BufferedReader bf;

            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONObject ServiceResult = (JSONObject)jsonObject.get("ServiceResult");
            JSONObject msgBody = (JSONObject)ServiceResult.get("msgBody");
            JSONObject itemList = (JSONObject)msgBody.get("itemList");

            /*JSONArray nations = (JSONArray)itemList.get("nations");
            JSONObject nations_nationNm = (JSONObject)nations.get(0);

            JSONArray directors = (JSONArray)itemList.get("directors");
            JSONObject directors_peopleNm = (JSONObject)directors.get(0);

            JSONArray genres = (JSONArray)itemList.get("genres");

            JSONArray actors = (JSONArray)itemList.get("actors");*/

            System.out.println("노선ID : " + itemList.get("busRouteId"));
            System.out.println("노선명 : " + itemList.get("busRouteNm"));
            System.out.println("노선유형 : " + itemList.get("routeType"));
            System.out.println("기점 : " + itemList.get("stStationNm"));
            System.out.println("종점 : " + itemList.get("edStationNm"));
            System.out.println("금일첫차시간 : " + itemList.get("firstBusTm"));
            System.out.println("금일막차시간 : " + itemList.get("lastBusTm"));
            System.out.println("운수사명 : " + itemList.get("corpNm"));

            //String genreNm = "";

           /* for(int i = 0; i < genres.size(); i++) {
                JSONObject genres_genreNm = (JSONObject)genres.get(i);
                genreNm += genres_genreNm.get("genreNm") + " ";
            }

            System.out.println("장르 : " + genreNm);

           System.out.println("감독명 : " + directors_peopleNm.get("peopleNm"));

            String peopleNm = "";

            for(int i = 0; i < actors.size(); i++) {
                JSONObject actors_peopleNm = (JSONObject)actors.get(i);
                peopleNm += actors_peopleNm.get("peopleNm") + " ";
                peopleNm = peopleNm + actors_peopleNm.get("peopleNm") + " ";
            }

            System.out.println("출연배우 : " + peopleNm);*/

        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}

