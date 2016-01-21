package com.tfs.register.preregister;

import java.net.InetSocketAddress;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.ServerConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableAutoConfiguration
@ComponentScan
public class myThriftClientMain {
	public static final String SERVER_IP = "localhost";  
	public static final int SERVER_PORT = 10005;  
	public static final int TIMEOUT = 30000; 
	
	/**   
	 *    
	 * @param userName   
	 */  
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
	/**     
	 * @param args 
	 */   
	public static void main(String[] args) {  
		
		SpringApplication.run(myThriftClientMain.class, args);
		
//		System.out.println("MyThriftClient init...");  
//		myThriftClientMain client = new myThriftClientMain();   
//		client.startClient("{'companyName':'Lejia', 'companyNameLocal':'Lejia', 'companyAddress':'Guangdong', 'txtEmail':'qpsandy@126.com', 'telephone':'17721217320', 'contactPerson':'Molly'}");   
//		System.out.println("MyThriftClient end.");  
	}
	
	
	@Bean
	public JettyEmbeddedServletContainerFactory jettyEmbeddedServletContainerFactory() {
		JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();
		factory.addServerCustomizers(server -> {
			HttpConfiguration httpConfig = new HttpConfiguration();
			httpConfig.setSendServerVersion(false);
			httpConfig.setSendDateHeader(false);
			ServerConnector connector = new ServerConnector(server, new HttpConnectionFactory(httpConfig));
			InetSocketAddress address = new InetSocketAddress(factory.getAddress(), factory.getPort());
			connector.setHost(address.getHostName());
			connector.setPort(18080);
			server.setConnectors(new Connector[]{connector});
		});
		return factory;
	}
}
