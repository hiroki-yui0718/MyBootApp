package com.tuyano.springboot.socket;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;

public class ServerSide {
	public String runSample() {

		ServerSocket sSocket = null;
		Socket socket = null;
		BufferedReader reader = null;
		PrintWriter writer = null;
		String line = null;

		try{
			//IPアドレスとポート番号を指定してサーバー側のソケットを作成
			sSocket = new ServerSocket();
			sSocket.bind(new InetSocketAddress
					("192.168.3.7",8765));
//					("192.168.0.15",8765));

			System.out.println("クライアントからの入力待ち状態");

			//クライアントからの要求を待ち続けます
			socket = sSocket.accept();

			//クライアントからの受取用
			reader = new BufferedReader(
					new InputStreamReader
					(socket.getInputStream()));

			//サーバーからクライアントへの送信用
			writer = new PrintWriter(
					socket.getOutputStream(), true);

			//無限ループ　byeの入力でループを抜ける
				
				while(true) {
					line = reader.readLine();
					if(line != null || !(line.equals("")))break;
				}
				System.out.println("クライアントで入力された文字＝Suica detected. idm = " + line);


		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if (reader!=null){
					reader.close();
				}
				if (writer!=null){
					writer.close();
				}
				if (socket!=null){
					socket.close();
				}
				if (sSocket!=null){
					sSocket.close();
				}
				System.out.println("サーバー側終了です");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return line;
		
		
	}
}
