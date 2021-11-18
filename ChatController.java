import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatController {

    public VBox vMassege;
    private static ServerSocket serverSocket = null;
    public TextArea txtmessageArea;
    private Socket socket=null;
  private static DataInputStream dataInputStream;
  private static DataOutputStream dataOutputStream;
    public TextField txtMassege;

    private void initInitialize(){
        new Thread(new Runnable() {
            @Override

            public void run() {
                try {
                    serverSocket=new ServerSocket(5000);
                    socket=serverSocket.accept();
                    dataInputStream=new DataInputStream(socket.getInputStream());
                    dataOutputStream=new DataOutputStream(socket.getOutputStream());

                    String messgeIn="";

                    while (!messgeIn.equals("end")){
                        messgeIn=dataInputStream.readUTF();
                        txtMassege.setText(txtMassege.getText().trim()+"\n Client:\t"+txtmessageArea);
                        File file=new File("assent/bmw.png");
                        BufferedImage image = ImageIO.read(file);

                        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                        ImageIO.write(image,"jpg",byteArrayOutputStream);
                        dataOutputStream.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void initialize(){
            initInitialize();


  }


    public void onActionSend(ActionEvent actionEvent) throws IOException {
        String msg_out = "";
        msg_out = txtMassege.getText();
        dataOutputStream.writeUTF(msg_out);
    }

    public void onActionImage(ActionEvent actionEvent) {
    }
}
