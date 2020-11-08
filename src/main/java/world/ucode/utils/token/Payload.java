package world.ucode.utils.token;

public class Payload {
    public String alg;
    public String typ;

    public Payload() {
        setAlg("SHA-512");
        setTyp("JWT");
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }
    public void setTyp(String typ) {
        this.typ = typ;
    }
}
