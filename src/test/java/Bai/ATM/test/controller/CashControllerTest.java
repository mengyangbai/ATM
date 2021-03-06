package bai.atm.test.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import bai.atm.Application;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class CashControllerTest {
	 
	@Autowired
	private WebApplicationContext context;
 
	private MockMvc mockMvc;
	
	@Before
	public void setupMockMvc() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	 
		/***
		 * 
		 * @throws Exception
		 */
	  	@Test
		public void getUserTest() throws Exception {
			
			RequestBuilder request = MockMvcRequestBuilders.get("/ATM/info");
			MvcResult result = mockMvc.perform(request)
					.andReturn();

			System.out.println(result.getResponse().getContentAsString());
		}
	 

}