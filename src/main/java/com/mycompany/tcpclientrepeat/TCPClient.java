package com.mycompany.tcpclientrepeat;

import fileUtil.fileUtil;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import lombok.SneakyThrows;

/**
 *C:/Users/azizg/Desktop/qeydler.txt
 * @author azizg
 */
public class TCPClient {
    @SneakyThrows
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Zəhmət olmasa göndərmək istədiyiniz faylın yerləşdiyi keçidi qeyd edin.");
        String userFIle = sc.nextLine();
        System.out.println(userFIle);
        
        System.out.println("göndərmək istədiyiniz şəxsin(serverin) ip və portunu daxil edin: eg. localhost:9876 ");
        String userHostPort = sc2.next();
        String[] newSt = userHostPort.split(":");
        String host = newSt[0];
        Integer port = Integer.parseInt(newSt[1]);
        
        try (Socket socket = new Socket(host,port)) {
            OutputStream ops = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(ops);

            byte[] bytes = fileUtil.readBytes(userFIle);
            dos.writeInt(bytes.length);
            dos.write(bytes);
        }
    }
}
