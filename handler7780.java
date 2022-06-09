import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

public class handler7780 implements Runnable {
    boolean ranStartAgian = true;
    @Override
    public void run() {
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(7780), 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        server.createContext("/PSIA/System/deviceInfo", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }
        class MyHandler implements HttpHandler {
            @Override
            public void handle(HttpExchange t) throws IOException {
                if("GET".equals(t.getRequestMethod())) {
                    Clock clock = Clock.systemDefaultZone();
                    Instant instant = clock.instant();
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
                            </DeviceInfo>
                            """;
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }

                    t.sendResponseHeaders(200, response.length());

                    OutputStream os = t.getResponseBody();
                    os.write(response.getBytes());
                    os.flush();
                    os.close();
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
    /*ServerSocket socket;
    int port = 7780;

    String HttpOk = """
            HTTP/1.1 200 OK
            Content-Length: 677
            Content-Type: text/xml; charset=UTF-8
            Date: Thu, 26 May 2022 18:45:04 GMT
            Server: waitress
                        
                        
            <DeviceInfo xmlns="urn:psialliance-org" version="1.0">
              <deviceName>Quanergy Systems LiDAR</deviceName>
              <model>M8</model><revNumber>D4P</revNumber><deviceID>7175616e-6572-6779-6a6e-d4c9b200120a</deviceID><serialNumber>1010C2900120A</serialNumber><macAddress>d4:c9:b2:00:12:0a
            </macAddress><firmwareVersion> 6.02;7.03</firmwareVersion><firmwareReleasedDate>2022-05-26T18:45:04.926889</firmwareReleasedDate><logicVersion>md5-c485683d</logicVersion><logicReleasedDate>2022-05-26T18:45:04.927055</logicReleasedDate><bootVersion>M8 Linaro 13.09 #1 SMP PREEMPT Wed Nov 1 18:50:39 PDT 2017</bootVersion><bootReleasedDate>2022-05-26T18:45:04.927220</bootReleasedDate>
            </DeviceInfo>
            """;
    byte[] tagB = "GET".getBytes();
    byte[] tag = new byte[3];

    public void run() {
        try {
            socket = new ServerSocket(port);
            Socket connectionSocket = socket.accept();
            InputStream inputStream = connectionSocket.getInputStream();
            OutputStream outputStream = connectionSocket.getOutputStream();

            while(true) {
                byte[] bytes = new byte[164];
                inputStream.read(bytes);
                ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
                byteBuffer.get(tag, 0, 3);

               // System.out.println(new String(bytes));

                DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS");
                Clock clock = Clock.systemDefaultZone();
                Instant instant = clock.instant();
                Date result = new Date(System.currentTimeMillis());

                HttpOk = """
            HTTP/1.1 200 OK
            Content-Length: 677
            Content-Type: text/xml; charset=UTF-8
            Date: Tue,\040""" +simple.format(result)+"""
             GMT
            Server: waitress
                                 
                                 
            <DeviceInfo xmlns="urn:psialliance-org" version="1.0">
              <deviceName>Quanergy Systems LiDAR</deviceName>
              <model>M8</model><revNumber>D4P</revNumber><deviceID>7175616e-6572-6779-6a6e-d4c9b200120a</deviceID><serialNumber>1010C2900120A</serialNumber><macAddress>8c:8d:28:47:2d:59
            </macAddress><firmwareVersion> 6.02;7.03</firmwareVersion><firmwareReleasedDate>"""+convertToNewFormat(instant.toString())+"""
            </firmwareReleasedDate><logicVersion>md5-c485683d</logicVersion><logicReleasedDate>"""+convertToNewFormat(instant.toString())+"""
            </logicReleasedDate><bootVersion>M8 Linaro 13.09 #1 SMP PREEMPT Wed Nov 1 18:50:39 PDT 2017</bootVersion><bootReleasedDate>"""+convertToNewFormat(instant.toString())+"""
            </bootReleasedDate>
            </DeviceInfo>""";
                System.out.println("\n"+HttpOk +"\n");
                byte[] bytes1 = HttpOk.getBytes(StandardCharsets.UTF_8);

                if (Arrays.equals(tag, tagB)) {
                    outputStream.write(bytes1);
                    outputStream.flush();
                }
                byteBuffer.position(0);
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }


        *//*InputStream inp = null;
        BufferedReader brinp = null;
        DataOutputStream out = null;
        try {
            inp = socket.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            return;
        }
        String line;
        while (true) {
            try {
                line = brinp.readLine();
                if ((line == null) || line.equalsIgnoreCase("QUIT")) {
                    socket.close();
                    return;
                } else {
                    out.writeBytes(line + "\n\r");
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

        }*//*
    }
    public static String convertToNewFormat(String dateStr) throws ParseException {
        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat destFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sourceFormat.setTimeZone(utc);
        Date convertedDate = sourceFormat.parse(dateStr);
        return destFormat.format(convertedDate);


    }*/
}