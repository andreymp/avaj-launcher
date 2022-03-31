package exceptions;

public class MD5Exception extends Exception {
    public MD5Exception() { super(); }
    public MD5Exception(String msg) { super(msg); }
    public MD5Exception(Throwable err) { super(err); }
    public MD5Exception(String msg, Throwable err) { super(msg, err); }
}