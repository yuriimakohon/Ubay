package world.ucode.utils.user;

import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GetUserAPI {
    public String json;
    public int status;

    public GetUserAPI(int userId)  {
        try {
            URL url = new URL("http://localhost:8080/api/user/" + userId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("method", "GET");
            connection.connect();
            this.status = connection.getResponseCode();
            if (this.status >= 400) {
                System.out.println("user return status >= 400");
            } else {
                InputStream response = connection.getInputStream();
                StringWriter writer = new StringWriter();
                IOUtils.copy(response, writer, StandardCharsets.UTF_8.name());
                connection.disconnect();
                this.json = writer.toString();
            }
        } catch (IOException e) {
            this.status = 404;
            System.out.println("user return status >= 400");
        }
    }
}
