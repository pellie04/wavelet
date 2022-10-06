import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> messages = new ArrayList<String>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Welcome to the String page!");
        } else if (url.getPath().contains("/search")) {
            String[] parameters1 = url.getQuery().split("=");
            if (parameters1[0].equals("s")) {
                ArrayList<String> results = new ArrayList<String>();
                for (int i = 0; i < messages.size(); i++) { 
                    if(messages.get(i).contains(parameters1[1])){
                        System.out.println(messages.get(i));
                        results.add(messages.get(i));
                        
                        
                    }
                  }
                  return String.format("%s ",results);
                
            }
        } else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters2 = url.getQuery().split("=");
                if (parameters2[0].equals("s")) {
                    System.out.println(parameters2[1]);
                    messages.add(parameters2[1]);
                    System.out.println(String.format("This list now contain %s", messages));
                    return String.format("String added! We now know %s", parameters2[1]);
                }
            }
            return "404 Not Found!";
        }
        return "404 Not Found!";
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
