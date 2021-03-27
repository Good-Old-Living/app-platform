package meru.comm.mobile.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.text.MessageFormat;

import javax.net.ssl.HttpsURLConnection;

public class OTPSender {

  private static String REQUEST_URL = "https://2factor.in/API/V1/768a35d8-557e-11eb-8153-0200cd936042/SMS/{0}/{1}\n";

  private MessageFormat urlFormat;

  public OTPSender() {
    urlFormat = new MessageFormat(REQUEST_URL);
  }

  public void send(String number, String message) {

    HttpsURLConnection httpsCon = null;

    try {

      message = URLEncoder.encode(message, "UTF-8");
      String url = urlFormat.format(new Object[] { number, message });

      httpsCon = (HttpsURLConnection) new URL(url).openConnection();
      httpsCon.setRequestMethod("GET");
      httpsCon.setConnectTimeout(10000);
      int resCode = httpsCon.getResponseCode();

      if (resCode != 200) {

        String resMessage = httpsCon.getResponseMessage();
        System.out.println(">>>> RES CODE " + resCode + " " + resMessage);
        try (InputStream inputStream = httpsCon.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

          String inputLine;
          StringBuilder response = new StringBuilder();

          while ((inputLine = bufferedReader.readLine()) != null) {
            response.append(inputLine);
          }
        }

      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      if (httpsCon != null) {
        httpsCon.disconnect();
      }
    }
  }

  public static void main(String[] args) {
    OTPSender smsSender = new OTPSender();
    smsSender.send("919845919393", "Your OTP is 123");

  }

}
