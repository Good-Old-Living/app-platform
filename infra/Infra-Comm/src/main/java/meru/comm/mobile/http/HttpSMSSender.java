package meru.comm.mobile.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.text.MessageFormat;

import javax.net.ssl.HttpsURLConnection;

public class HttpSMSSender {

  private static String REQUEST_URL = "https://smsapi.24x7sms.com/api_2.0/SendSMS.aspx?"
      + "APIKEY=hDuiVf92kbx&SenderID=PAARIL&ServiceName=TEMPLATE_BASED" + "&MobileNo={0}&Message={1}";

  
  private static String DLT_REQUEST_URL = "https://smsapi.24x7sms.com/api_2.0/SendSMS.aspx?"
      + "APIKEY=hDuiVf92kbx&SenderID=KIRUTH&ServiceName=TEMPLATE_BASED&DLTTemplateID={0}&MobileNo={1}&Message={2}";

  
  private String requestURL;
  private MessageFormat urlFormat;
  private MessageFormat urlFormatDLT;

  public HttpSMSSender(String requestURL) {
    this.requestURL = requestURL;
    urlFormat = new MessageFormat(REQUEST_URL);
    
    urlFormatDLT = new MessageFormat(DLT_REQUEST_URL);
  }

  public void send(String number, String message) {
    send(null, number, message);
  }
  
  public void send(String templateId, String number, String message) {

    HttpsURLConnection httpsCon = null;

    try {

      message = URLEncoder.encode(message, "UTF-8");
      
      String url = null;
      if (templateId == null) {
        url = urlFormat.format(new Object[] { number, message });
      }
      else {
        url = urlFormatDLT.format(new Object[] { templateId, number, message });
      }
      System.out.println(">>>> URL "+url);
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
  //  HttpSMSSender smsSender = new HttpSMSSender(REQUEST_URL);
  //  smsSender.send("919845919393", "Your OTP is 123");

  }

}
