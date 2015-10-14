package org.mohan.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.util.List;
import java.util.Locale;

import org.mohan.app.dao.CustomerDAO;
import org.mohan.app.dao.TypeDAO;
import org.mohan.app.model.Customer;
import org.mohan.app.model.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
//		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
//		String formattedDate = dateFormat.format(date);
		
//		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	public static void data() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			URL url = new URL("http://localhost:8080/RestWithSpring/rest/customer");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
			List<Customer> list = null;
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				list =  mapper.readValue(output, new TypeReference<List<Customer>>(){});
			}
			for (Customer cus : list) {
				System.out.println(cus);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Done!!!!!!");
	}
	public static void main(String[] args) {
		
	}
	
	public static void type() {
		TypeDAO t = new TypeDAO();
		Type obj = new Type();
		obj.setType("QQ");
		obj.setRate(210.5);
		obj.setActive(true);
//		List<Type> list = t.getAll();
//		for (Type type : list) {
//			System.out.println(type);
//		}
//		System.out.println("==============================================================================");
//		Type qq = new Type();
//		qq.setType("QQ");
//		Type ty = t.getByPK(qq);
//		System.out.println("==============================================================================");
//		t.add(obj);
//		obj.setRate(111.5);
//		t.update(obj);
		t.delete(obj);
	}
	
	public static void cus() {
		CustomerDAO c = new CustomerDAO();
//		List<Customer> list = c.getAll();
//		for (Customer customer : list) {
//			System.out.println(customer);
//		}
//		System.out.println("==============================================================================");
//		Customer obj = new Customer();
//		obj.setNid("10");
//		Customer aa = c.getByPK(obj);
//		System.out.println(aa);
		
		Customer obj = new Customer();
		obj.setNid("MK");
		obj.setName("Mohan");
		obj.setAddress("173 cotton");
		obj.setEmail("mk@m.m");
		Date d = Date.valueOf("2015-01-01");
		obj.setDate(d);
//		c.add(obj);
//		c.update(obj);
//		c.delete(obj);
	}
}
