package world.ucode.utils.token;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;
import world.ucode.model.db.dao.DAOusers;
import world.ucode.model.db.entetis.Users;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
        header.setExp(String.valueOf(date.getTime() + 1200));
        header.setIat(String.valueOf(date.getTime()));
        header.setSub("userOfUBay");


        ObjectMapper mapper = new ObjectMapper();
        String pbs = Base64.getEncoder().encodeToString(mapper.writeValueAsString(this.payload).getBytes());
        String hbs = Base64.getEncoder().encodeToString(mapper.writeValueAsString(this.header).getBytes());

//        return pbs + "." + hbs + "." + DigestUtils.sha512Hex(pbs + hbs);
        return DigestUtils.sha512Hex(pbs + hbs);
    }

    public static String getRefreshToken(String token) {
        Date date = java.util.Calendar.getInstance().getTime();
        return DigestUtils.sha512Hex(token + date.getTime());
    }

    public static long getTimeOfToken() {
        return 1200000;
    }

    public static void createSetTokens(Users user, HttpServletResponse resp, DAOusers daoUser) throws JsonProcessingException {
        String token = new Token().getToken(user.getLogin());
        String rToken = Token.getRefreshToken(token);
        Cookie kToken = new Cookie("token", token);
        Cookie kRefToken = new Cookie("ref_token", rToken);

        kToken.setPath("/");
        kRefToken.setPath("/");
        kRefToken.setMaxAge(10200);
        kToken.setMaxAge((int) Token.getTimeOfToken());
        resp.addCookie(kToken);
        resp.addCookie(kRefToken);
        user.setToken(token);
        user.setRef_token(rToken);
        daoUser.update(user);
    }

    public static void setTokens(Users user, DAOusers daoUser, HttpServletResponse resp) throws JsonProcessingException {
        createSetTokens(user, resp, daoUser);

        Cookie kId = new Cookie("id", String.valueOf(user.getId()));
        kId.setPath("/");
        resp.addCookie(kId);
    }
}
