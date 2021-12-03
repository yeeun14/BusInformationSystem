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

            URL url = new URL("http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList.json?key="
                    + key + "&strSrch=130");

            BufferedReader bf;

            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONObject ServiceResult = (JSONObject)jsonObject.get("ServiceResult");
            JSONObject itemList = (JSONObject)ServiceResult.get("itemList");

            /*JSONArray nations = (JSONArray)itemList.get("nations");
            JSONObject nations_nationNm = (JSONObject)nations.get(0);

            JSONArray directors = (JSONArray)itemList.get("directors");
            JSONObject directors_peopleNm = (JSONObject)directors.get(0);

            JSONArray genres = (JSONArray)itemList.get("genres");

            JSONArray actors = (JSONArray)itemList.get("actors");*/

            System.out.println("노선ID : " + itemList.get("busRouteId"));
            System.out.println("노선명 : " + itemList.get("busRouteNm"));
            System.out.println("영화명(영문) : " + itemList.get("movieNmEn"));
            System.out.println("재생시간 : " + itemList.get("showTm"));
            System.out.println("개봉일 : " + itemList.get("openDt"));
            System.out.println("영화유형 : " + itemList.get("typeNm"));
            System.out.println("제작국가명 : " + itemList.get("nationNm"));

            String genreNm = "";

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

