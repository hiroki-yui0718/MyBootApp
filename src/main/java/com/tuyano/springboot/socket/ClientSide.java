package com.tuyano.springboot.socket;

import java.io.*;
import java.net.*;

public class ClientSide {
	public String runSample(String line) {

		Socket cSocket = null;
		BufferedReader csInput = null;
		PrintWriter writer = null;
		BufferedReader reader = null;
		String send = null;

		try{
			//IPアドレスとポート番号を指定してクライアント側のソケットを作成
//			cSocket = new Socket("192.168.3.10", 8765);
			cSocket = new Socket("192.168.0.19", 8765);

			
			//クライアント側からサーバへの送信用
			writer = new PrintWriter
					(cSocket.getOutputStream(), true);
			
			//サーバ側からの受取用
			reader = new BufferedReader
					(new InputStreamReader
							(cSocket.getInputStream()));

				writer.println(line);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if (reader != null) {
					reader.close();
				}
				if (writer != null) {
					writer.close();
				}
				if (csInput != null) {
					csInput.close();
				}
				if (cSocket != null) {
					cSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	        System.out.println("クライアント側終了です");
	        
		}
		return send;
		
		}
}
