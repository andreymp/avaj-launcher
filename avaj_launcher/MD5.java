package avaj_launcher;

import java.security.*;

public class MD5 {

    private final String HELICOPTER =  "cfc32525e46bdad5c742971a1098fe12";
    private final String BALOON = "994736b4f0aec72f6e5ae580051d012f";
    private final String JETPLANE = "554cd647d6b135f7e36ab1214c5e816a";

    class MD5Exception extends Exception {
        public MD5Exception() { super(); }
        public MD5Exception(String msg) { super(msg); }
        public MD5Exception(Throwable err) { super(err); }
        public MD5Exception(String msg, Throwable err) { super(msg, err); }
    }

    private static String decrypt(String md5String) {
        switch (md5String) {
            case HELICOPTER:
                return "helicopter";
            case BALOON:
                return "baloon";
            case JETPLANE:
                return "jetplane";
        }
        throw new MD5Exception("Can not decode MD5");
    }
}
