package reweda.damian.quiz.API;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import reweda.damian.quiz.Models.Details.Questions;
import reweda.damian.quiz.Models.Details.QuizDetailResponse;
import reweda.damian.quiz.Models.Main.QuizItem;
import reweda.damian.quiz.Models.Main.QuizResponse;

/**
 * Class responsible for downloading Quiz list and details
 */
public class QuizFetchr implements Constants{

    private static final String TAG = "QuizFetchr";

    public QuizFetchr(){}

    public List<QuizItem> fetchItems() {

        List<QuizItem> items = new ArrayList<>();

        try {
            String quizListResponse = getQuizDataFrom(API_ADDRESS);
            parseItems(items, quizListResponse);

            Log.i(TAG, "Received JSON: " + quizListResponse);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON", je);
        }

        return items;
    }

    public List<Questions> fetchQuizDetailsFor(long quizId) {

        List<Questions> items = new ArrayList<>();

        try {
            String quizDetailsResponse = getQuizDataFrom(urlFor(quizId));

            Log.i(TAG, "Received JSON: " + quizDetailsResponse);

            JSONObject jsonBody = new JSONObject(quizDetailsResponse);
            parseItemsDetails(items, jsonBody);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON", je);
        }

        return items;
    }

    private void parseItems(List<QuizItem> items, String response)
            throws IOException, JSONException {

        QuizResponse itemsResponse = new Gson().fromJson(response, QuizResponse.class);

        items.clear();
        items.addAll(itemsResponse.getItems());
    }

    private void parseItemsDetails(List<Questions> items, JSONObject jsonBody)
            throws IOException, JSONException {

        QuizDetailResponse itemsResponse = new Gson().fromJson(jsonBody.toString(), QuizDetailResponse.class);

        items.clear();
        items.addAll(itemsResponse.getQuestions());
    }


    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +
                        ": with " +
                        urlSpec);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getQuizDataFrom(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    private String urlFor(long quizId) {
        StringBuilder builder = new StringBuilder();

            builder.append(API_ADDRESS_DETAILS)
                    .append(quizId)
                    .append("/0");

        return builder.toString();
    }
}
