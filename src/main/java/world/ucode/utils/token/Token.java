package world.ucode.utils.token;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Base64;
import java.util.Date;

public class Token {
    public Payload payload;
    public Header header;

    public Token() {
        setHeader(new Header());
        setPayload(new Payload());
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public String getToken(String forWho) throws JsonProcessingException {
        if (payload == null || header == null) {
            System.err.println("Error create token");
            return "fileToken";
        }

        Date date = java.util.Calendar.getInstance().getTime();
        header.setSub(forWho);
        header.setExp(String.valueOf(date.getTime() + 7200));
        header.setIat(String.valueOf(date.getTime()));
        header.setSub("userOfUBay");


        ObjectMapper mapper = new ObjectMapper();
        String pbs = Base64.getEncoder().encodeToString(mapper.writeValueAsString(this.payload).getBytes());
        String hbs = Base64.getEncoder().encodeToString(mapper.writeValueAsString(this.header).getBytes());

        return pbs + "." + hbs + "." + DigestUtils.sha512Hex(pbs + hbs);
    }
}
