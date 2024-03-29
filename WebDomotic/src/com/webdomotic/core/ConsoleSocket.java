package com.webdomotic.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public final class ConsoleSocket extends Socket{

	private Socket socket;
	private OutputStream out;
	//private BufferedWriter out1;
	private BufferedReader in; 
	
	
	public ConsoleSocket(String host, int port){

		try {
			socket = new Socket(host, port);
			out = socket.getOutputStream();
	//		out1 = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			in =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void write(char c){
		try {
			out.write(c);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void write(int i){
		try {
			out.write(i);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void write(String s){
		try {
			out.write(s.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void write(byte [] data){
		try {
			out.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String read(){
		try {
			return in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void close(){
		try {
			socket.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
}