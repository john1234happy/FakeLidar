/*
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

public class Test {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(7780), 0);
        server.createContext("/PSIA/System/deviceInfo", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            if("GET".equals(t.getRequestMethod())) {
                DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS");
                Clock clock = Clock.systemDefaultZone();
                Instant instant = clock.instant();
                Date result = new Date(System.currentTimeMillis());

                String response = null;
                try {
                    response = """
                            <DeviceInfo xmlns="urn:psialliance-org" version="1.0">
                            <deviceName>Quanergy Systems LiDAR</deviceName>
                            <model>M8</model><revNumber>D4P</revNumber><deviceID>7175616e-6572-6779-6a6e-d4c9b200120a</deviceID><serialNumber>1010C2900120A</serialNumber><macAddress>8c:8d:28:47:2d:59
                                  </macAddress><firmwareVersion> 6.02;7.03</firmwareVersion><firmwareReleasedDate>""" + convertToNewFormat(instant.toString()) + """
                            </firmwareReleasedDate><logicVersion>md5-c485683d</logicVersion><logicReleasedDate>""" + convertToNewFormat(instant.toString()) + """
                            </logicReleasedDate><bootVersion>M8 Linaro 13.09 #1 SMP PREEMPT Wed Nov 1 18:50:39 PDT 2017</bootVersion><bootReleasedDate>""" + convertToNewFormat(instant.toString()) + """
                                    </bootReleasedDate>
                            </DeviceInfo>""";
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.flush();
            }
        }
    }

    public static String convertToNewFormat(String dateStr) throws ParseException {
        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat destFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sourceFormat.setTimeZone(utc);
        Date convertedDate = sourceFormat.parse(dateStr);
        return destFormat.format(convertedDate);
    }
}*/
