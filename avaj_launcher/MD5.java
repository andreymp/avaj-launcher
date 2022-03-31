package avaj_launcher;

import exceptions.MD5Exception;

public class MD5 {

    private static final String HELICOPTER =  "cfc32525e46bdad5c742971a1098fe12";
    private static final String BALOON = "994736b4f0aec72f6e5ae580051d012f";
    private static final String JETPLANE = "554cd647d6b135f7e36ab1214c5e816a";

    public static String decrypt(String md5String) throws MD5Exception {
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
