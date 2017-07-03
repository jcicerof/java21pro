import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class LoadURL {

    public LoadURL(String urlRequest) {
        SocketChannel sock = null;
        try {
            URL url = new URL(urlRequest);
            String host = url.getHost();
            String page = url.getPath();
            InetSocketAddress address = new InetSocketAddress(host, 80);
            Charset iso = Charset.forName("ISO-8859-1");
            CharsetDecoder decoder = iso.newDecoder();
            CharsetEncoder encoder = iso.newEncoder();

            ByteBuffer byteData = ByteBuffer.allocate(16384);
            CharBuffer charData = CharBuffer.allocate(16384);

            sock = SocketChannel.open();
            sock.configureBlocking(false);
            sock.connect(address);

            Selector listen = Selector.open();
            sock.register(listen, SelectionKey.OP_CONNECT +
                SelectionKey.OP_READ);

            while (listen.select(500) > 0) {
                Set keys = listen.selectedKeys();
                Iterator i = keys.iterator();
                while (i.hasNext()) {
                    SelectionKey key = (SelectionKey) i.next();
                    i.remove();
                    SocketChannel keySock = (SocketChannel) key.channel();
                    if (key.isConnectable()) {
                        if (keySock.isConnectionPending()) {
                            keySock.finishConnect();
                        }
                        CharBuffer httpReq = CharBuffer.wrap(
                            "GET " + page + "\n\r\n\r");
                        ByteBuffer request = encoder.encode(httpReq);
                        keySock.write(request);
                    } else if (key.isReadable()) {
                        keySock.read(byteData);
                        byteData.flip();
                        charData = decoder.decode(byteData);
                        charData.position(0);
                        System.out.print(charData);

                        byteData.clear();
                        charData.clear();
                    }
                }
            }
            sock.close();
        } catch (MalformedURLException mue) {
            System.out.println(mue.getMessage());
        } catch (UnknownHostException uhe) {
            System.out.println(uhe.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
       
    public static void main(String arguments[]) {
        LoadURL app = new LoadURL(arguments[0]);  
    }
}
