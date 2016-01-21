package com.tfs.register.preregister.thriftClient;

import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.stereotype.Service;

import com.tfs.register.preregister.MyThriftService;
import com.tfs.register.preregister.model.Company;

@Service
public class ThriftClient {
//	public static final String SERVER_IP = "123.59.67.239";  
//	public static final int SERVER_PORT = 18443;  
	public static final String SERVER_IP = System.getProperty("server.ip");
	public static final int SERVER_PORT = Integer.valueOf(System.getProperty("server.port")).intValue();
	public static final int TIMEOUT = 30000; 
	
	/**   
	 *    
	 * @param userName   
	 */  
//	public void startClient(String userName) {   
//		TTransport transport = null;   
//		try {    
//			transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);    
//			// protocal should be the same with server
//			TProtocol protocol = new TBinaryProtocol(transport);    
//			// TProtocol protocol = new TCompactProtocol(transport);
//			// TProtocol protocol = new TJSONProtocol(transport);    
//			MyThriftService.Client client = new MyThriftService.Client(      
//					protocol);    
//			transport.open();
//			System.out.println("myThriftClient start...");  
//			String result = client.sayHello(userName);    
//			System.out.println("Thrify client result =: " + result);   
//			} 
//		catch (TTransportException e) {    
//			e.printStackTrace();   
//			} 
//		catch (TException e) {    
//			e.printStackTrace();   
//			} 
//		finally {    
//			if (null != transport) {     
//				transport.close();    
//				}   
//			}   
//	}   	
	
	public void startClient(String userName) {   
		TTransport transport = null;   
		try {    
			transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);    
			// protocal should be the same with server
			TProtocol protocol = new TBinaryProtocol(transport);    
			// TProtocol protocol = new TCompactProtocol(transport);
			// TProtocol protocol = new TJSONProtocol(transport);    
			MyThriftService.Client client = new MyThriftService.Client(      
					protocol);    
			transport.open();
			System.out.println("myThriftClient start...");  
			String result = client.sayHello(userName);    
			System.out.println("Thrify client result =: " + result);   
			} 
		catch (TTransportException e) {    
			e.printStackTrace();   
			} 
		catch (TException e) {    
			e.printStackTrace();   
			} 
		finally {    
			if (null != transport) {     
				transport.close();    
				}   
			}   
	}    	
	
	public List<Company> showPrereigter() {   
		TTransport transport = null;   
		List<Company> companys = null;
		try {    
			transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);    
			// protocal should be the same with server
			TProtocol protocol = new TBinaryProtocol(transport);    
			// TProtocol protocol = new TCompactProtocol(transport);
			// TProtocol protocol = new TJSONProtocol(transport);    
			MyThriftService.Client client = new MyThriftService.Client(      
					protocol);    
			transport.open();
			System.out.println("showPrereigter client start...");  
			companys = client.showCompanys();    
			System.out.println("showPrereigter client result =: " + companys);  
			} 
		catch (TTransportException e) {    
			e.printStackTrace();   
			} 
		catch (TException e) {    
			e.printStackTrace();   
			} 
		finally {    
			if (null != transport) {     
				transport.close();    
				}   
			}
		 
		return companys;
	}    
}
