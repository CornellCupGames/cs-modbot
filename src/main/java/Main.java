import static spark.Spark.*;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.jade.JadeTemplateEngine;

public class Main {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("message", "Hello World");

        get("/hello", (req, res) -> new ModelAndView(map, "test"), new JadeTemplateEngine());
    }
}