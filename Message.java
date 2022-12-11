import java.util.HashMap;

public class Message implements java.io.Serializable {
    String func;
    HashMap<String, String> params;

    public String getFunc() {
        return func;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public Message(String func, HashMap<String, String> params) {
        this.func = func;
        this.params = params;
    }
}
