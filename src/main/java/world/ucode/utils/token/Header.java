package world.ucode.utils.token;

import java.util.Date;

public class Header {
    public String iss;
    public String sub;
    public String aud;
    public String exp;
    public String nbf;
    public String iat;
    public String jti;

    public Header() {
        Date date = java.util.Calendar.getInstance().getTime();

        setAud("user");
        setExp(String.valueOf(date.getTime() + 7200));
        setIat(String.valueOf(date.getTime()));
        setIss("the best boys");
        setJti(java.util.UUID.randomUUID().toString());
        setNbf(date.toString());
        setSub("userOfUBay");
    }

    public void setAud(String aud) {
        this.aud = aud;
    }
    public void setExp(String exp) {
        this.exp = exp;
    }
    public void setIat(String iat) {
        this.iat = iat;
    }
    public void setIss(String iss) {
        this.iss = iss;
    }
    public void setJti(String jti) {
        this.jti = jti;
    }
    public void setNbf(String nbf) {
        this.nbf = nbf;
    }
    public void setSub(String sub) {
        this.sub = sub;
    }
}
