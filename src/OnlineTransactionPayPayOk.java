import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
//TODO https://pay.pay-ok.org/demo/protected  Админка для контроля

public class OnlineTransactionPayPayOk {
    private String url;

    public OnlineTransactionPayPayOk(String url) {
        this.url = url;
    }

    /**
     * Function of sending data to the server for registering a check
     * @param contacts cashier's contact information
     * @param payLS buyer's personal account number
     * @param amount subsequent payment
     * @param description additional information about the status of the check
     * @return registering transaction receipt status
     */
    public String sendCheck4Registration(String contacts,
                                         String payLS, String amount, String description) {
        return sendRequest4HTTP(url, createPostRequest(getGUID(), getData4Payment(), contacts, payLS, amount, description));
    }

    /**
     * Function for obtaining transaction data by GUID
     * @param GUID unique 128-bit transaction identifier
     * @return information about the transaction; if there is no GUID, information about the absence of a registered transaction is returned
     */
    public String getInformationOnCheck(String GUID) {
        return sendRequest4HTTP(url, "={\"PAY_ID\":\"" + GUID + "\",\"PAY_ACTION\":\"GET_INFO\"}");
    }

    private String sendRequest4HTTP(String URL, String request) {
        String req = URL + request;
        if (req.contains(" ")) req = req.replace(" ", "%20");
        try {
            java.net.URL url = new URL(req);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            try (BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                return response.toString();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private String createPostRequest(String guid, String payData, String contacts,
                                     String payLS, String amount, String description) {
        return "={" +
                "\"PAY_ID\":\"" + guid + "\"," +
                "\"PAY_ACTION\":\"REG\"," +
                "\"PAY_DATE\":\"" + payData + "\"," +
                "\"PAY_EMAIL\":\"" + contacts + "\"," +
                "\"PAY_LS\":\"" + payLS + "\"," +
                "\"PAY_ITOG\":\"" + amount + "\"," +
                "\"PAY_NAME\":\"" + description + "\"" +
                "}";
    }

    private String getData(String pattern) {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(pattern)) + "";
    }

    private String getTimeNow(String pattern) {
        return LocalTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    private String getData4Payment() {
        return getData("YYYY-MM-dd") + " " + getTimeNow("HH:mm:ss");
    }

    private String getGUID() {
        return "e05acc96-" + getRandomNum(9999, 1) + "-741b-a00" + getRandomNum(9, 0) + "-30e90" + getRandomNum(9, 0) + "fda8fa";
    }

    private String getRandomString(int length) {
        Random r = new Random();
        return r.ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> (char) i)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    private int getRandomNum(int max, int min) {
        Random rn = new Random();
        return (int) rn.nextInt(max - min + 1) + min;
    }
}
