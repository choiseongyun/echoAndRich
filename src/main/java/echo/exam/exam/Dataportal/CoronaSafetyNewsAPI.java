package echo.exam.exam.Dataportal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Controller
public class CoronaSafetyNewsAPI {
    @GetMapping("/safety-news/{serviceKey}/{countryName}/{countryCode}")
    @ResponseBody
    public JSONObject getSafetyNews(
            @PathVariable String serviceKey,
            @RequestParam (defaultValue = "JSON") String returnType,
            @RequestParam (defaultValue = "10") int numOfRows,
            @RequestParam (defaultValue = "1") int pageNo,
            @PathVariable String countryName,
            @PathVariable String countryCode
    ) throws IOException {
        String apiURL = "http://apis.data.go.kr/1262000/CountryCovid19SafetyServiceNew/getCountrySafetyNewsListNew";
        JSONObject response = callSafetyNewsAPI(apiURL, serviceKey, returnType, numOfRows, pageNo, countryName, countryCode);

        // Return the API response
        return response;
    }

    private JSONObject callSafetyNewsAPI(String apiUrl, String serviceKey, String returnType, int numOfRows, int pageNo, String countryName, String countryCode) throws IOException {
        StringBuilder urlBuilder = new StringBuilder(apiUrl);
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + URLEncoder.encode(serviceKey, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "=" + URLEncoder.encode(returnType, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(numOfRows), "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(pageNo), "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("cond[country_nm::EQ]", "UTF-8") + "=" + URLEncoder.encode(countryName, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("cond[country_iso_alp2::EQ]", "UTF-8") + "=" + URLEncoder.encode(countryCode, "UTF-8"));
        URL url = new URL(urlBuilder.toString());
        // 4. 요청하고자 하는 URL과 통신하기 위한 Connection 객체 생성.
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();


        try {

            // 5. 통신을 위한 메소드 SET.
            conn.setRequestMethod("GET");
            // 6. 통신을 위한 Content-type SET.
            conn.setRequestProperty("Content-type", "application/json");
            // 7. 통신 응답 코드 확인.
            System.out.println("Response code: " + conn.getResponseCode());

            int responseCode = conn.getResponseCode();
            if (responseCode >= 200 && responseCode <= 300) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); // 인코딩 설정 변경
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }
                rd.close();
                String response = sb.toString();
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject)jsonParser.parse(sb.toString());

                JSONArray array = (JSONArray) jsonObject.get("data");
                for(int i=0; i<array.size(); i++){

                    //배열 안에 있는것도 JSON형식 이기 때문에 JSON Object 로 추출
                    JSONObject object = (JSONObject) array.get(i);

                    //JSON name으로 추출
                    System.out.println("continent_cd ==> "+object.get("continent_cd"));
                    System.out.println("continent_eng_nm ==> "+object.get("continent_eng_nm"));
                    System.out.println("continent_nm ==> "+object.get("continent_nm"));
                    System.out.println("country_eng_nm ==> "+object.get("country_eng_nm"));
                    System.out.println("country_iso_alp2 ==> "+object.get("country_iso_alp2"));
                    System.out.println("country_nm ==> "+object.get("country_nm"));
                    System.out.println("file_cnt ==> "+object.get("file_cnt"));
                    System.out.println("file_download_url ==> "+object.get("file_download_url"));
                    System.out.println("file_path ==> "+object.get("file_path"));
                    System.out.println("html_origin_cn ==> "+object.get("html_origin_cn"));
                    System.out.println("sfty_notice_id ==> "+object.get("sfty_notice_id"));
                    System.out.println("title ==> "+object.get("title"));
                    System.out.println("txt_origin_cn ==> "+object.get("txt_origin_cn"));
                    System.out.println("wrt_dt ==> "+object.get("wrt_dt"));

                }
                return jsonObject;
            } else {
                // 처리할 수 없는 응답 코드일 경우 예외 처리
                throw new IOException("Response code: " + responseCode);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            conn.disconnect();
        }
    }
}