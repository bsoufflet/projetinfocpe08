package com.webdomotic.examples;

import java.io.FileInputStream;

import com.webdomotic.core.ConsoleSocket;


public class ExampleSocket {

	private static byte[] filedata;
	/**
	 * Main example for socket use
	 */
	public static void main(String[] args) {
		try{
			String welcome = "hello marina, 8, 1";
			
			FileInputStream fis = new FileInputStream("C:\\Web\\test.txt");
			int num = fis.available();
			filedata = new byte[num];
			fis.read(filedata);
			
			String content = new String(filedata , 0 , filedata.length);
			System.out.println(toHex(filedata));
			System.out.println("content : "+ content);
			
			ConsoleSocket socket = new ConsoleSocket("192.168.0.30", 2000);
			socket.write('d');
			socket.write(intToByteArray(filedata.length));
			socket.write(filedata);
			
			System.out.println(socket.read());
			
			
		}catch(Exception e){
		
		}
	}
	
	
	//helper functions for main
	public static byte[] intToByteArray(int value) {
	        byte[] b = new byte[4];
	        for (int i = 0; i < 4; i++) {
	            int offset = (b.length - 1 - i) * 8;
	            b[i] = (byte) ((value >>> offset) & 0xFF);
	        }
	        return b;
	    }

	private static String digits="0123456789abcdef";
	
    public static String toHex(byte[] data)
    {
        StringBuffer	buf = new StringBuffer();
        
        for (int i = 0; i != data.length; i++)
        {
            int	v = data[i] & 0xff;
      
            buf.append(digits.charAt(v >> 4));
            buf.append(digits.charAt(v & 0xf));
        }
        return buf.toString();
    }
}