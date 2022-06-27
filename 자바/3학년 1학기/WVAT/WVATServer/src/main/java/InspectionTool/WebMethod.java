package InspectionTool;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WebMethod extends Vulnerability {
    public WebMethod() {
        super();
    }

    @Override
    public void check(String domain) {
        runModule(toRootUrl(domain));
    }

    public String toRootUrl(String domain) {
        String[] domainParse = domain.split("//|/");
        return domainParse[0] + "//" + domainParse[1] + "/";
    }

    public void runModule(String domain) {
        try {
            String allow = " ";

            HttpURLConnection c = (HttpURLConnection) new URL(domain).openConnection();
            c.setRequestMethod("OPTIONS");

            for (int i = 0; i < c.getHeaderFields().size(); i++) {
                allow = c.getHeaderField(i);
                setInputValue("Method Options");
                if (allow.contains("PUT") || allow.contains("DELETE")) {
                    setResult("ALLOW : " + allow);
                    break;
                }else if(c.getHeaderField(0).contains("200 OK")){
                    setResult("ALLOW : " + c.getHeaderField("Allow") );
                }else  setResult(c.getHeaderField(0));
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            setExists(true);
        }
    }
}
