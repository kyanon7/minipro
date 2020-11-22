package com.model2.mvc.service.prurchase.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;


/*
 *	FileName :  UserServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
										"classpath:config/context-aspect.xml",
										"classpath:config/context-mybatis.xml",
										"classpath:config/context-transaction.xml" })
//@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	public int tranNo = 10065;

	//@Test
	public void testAddPurchase() throws Exception {
		
		Purchase purchase = new Purchase();
		Product product = new Product();
		User user = new User();
		purchase.setTranNo(tranNo);
		product.setProdNo(10013);
		purchase.setPurchaseProd(product);
		user.setUserId("user13");
		purchase.setBuyer(user);
		purchase.setPaymentOption("000");
		purchase.setReceiverName("스캇");
		purchase.setReceiverPhone("010-2034-1034");
		purchase.setDivyAddr("주소다아아앙");
		purchase.setDivyRequest("빨리 갔다달라맨이야");
		purchase.setTranCode("000");
		purchase.setDivyDate("20201122");
		
		purchaseService.addPurchase(purchase);
		
		System.out.println(purchase);
		
		purchase = purchaseService.getPurchase(tranNo);

		//==> console 확인
		System.out.println(purchase);
		
		//==> API 확인
		Assert.assertEquals(tranNo, purchase.getTranNo());
		Assert.assertEquals(10013, purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("user13", purchase.getBuyer().getUserId());
		Assert.assertEquals("000", purchase.getPaymentOption());
		Assert.assertEquals("스캇", purchase.getReceiverName());
		Assert.assertEquals("010-2034-1034", purchase.getReceiverPhone());
		Assert.assertEquals("주소다아아앙", purchase.getDivyAddr());
		Assert.assertEquals("빨리 갔다달라맨이야", purchase.getDivyRequest());
		Assert.assertEquals("000", purchase.getTranCode());
		Assert.assertEquals("20201122", purchase.getDivyDate().substring(0, 10).replace("-", ""));
	}
	
	//@Test
	public void testGetPurchase() throws Exception {
		
		Purchase purchase = new Purchase();
		
		purchase = purchaseService.getPurchase(tranNo);

		//==> console 확인
		System.out.println(purchase);
		
		//==> API 확인
		Assert.assertEquals(tranNo, purchase.getTranNo());
		Assert.assertEquals(10013, purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("user13", purchase.getBuyer().getUserId());
		Assert.assertEquals("000", purchase.getPaymentOption());
		Assert.assertEquals("스캇", purchase.getReceiverName());
		Assert.assertEquals("010-2034-1034", purchase.getReceiverPhone());
		Assert.assertEquals("주소다아아앙", purchase.getDivyAddr());
		Assert.assertEquals("빨리 갔다달라맨이야", purchase.getDivyRequest());
		Assert.assertEquals("000", purchase.getTranCode());
		Assert.assertEquals("20201122", purchase.getDivyDate().substring(0, 10).replace("-", ""));

//		Assert.assertNotNull(purchaseService.getPurchase(10100));
		Assert.assertNotNull(purchaseService.getPurchase(tranNo));
	}
	
	//@Test
	public void testUpdatePurchase() throws Exception{
		 
		Purchase purchase = purchaseService.getPurchase(tranNo);
		Assert.assertNotNull(purchase);
		
		Assert.assertEquals(tranNo, purchase.getTranNo());
		Assert.assertEquals(10013, purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("user13", purchase.getBuyer().getUserId());
		Assert.assertEquals("000", purchase.getPaymentOption());
		Assert.assertEquals("스캇", purchase.getReceiverName());
		Assert.assertEquals("010-2034-1034", purchase.getReceiverPhone());
		Assert.assertEquals("주소다아아앙", purchase.getDivyAddr());
		Assert.assertEquals("빨리 갔다달라맨이야", purchase.getDivyRequest());
		Assert.assertEquals("000", purchase.getTranCode());
		Assert.assertEquals("20201122", purchase.getDivyDate().substring(0, 10).replace("-", ""));

		purchase.setPaymentOption("001");
		purchase.setReceiverName("유저13");
		purchase.setReceiverPhone("010-3156-3634");
		purchase.setDivyAddr("배달주소");
		purchase.setDivyRequest("늦으면 안된다맨이야");
		
		purchaseService.updatePurchase(purchase);
		
		purchase = purchaseService.getPurchase(tranNo);
		Assert.assertNotNull(purchase);
		
		//==> console 확인
		System.out.println(purchase);
			
		//==> API 확인
		Assert.assertEquals("001", purchase.getPaymentOption());
		Assert.assertEquals("유저13", purchase.getReceiverName());
		Assert.assertEquals("010-3156-3634", purchase.getReceiverPhone());
		Assert.assertEquals("배달주소", purchase.getDivyAddr());
		Assert.assertEquals("늦으면 안된다맨이야", purchase.getDivyRequest());
	 }
	 
	
	 //==>  주석을 풀고 실행하면....
	 //@Test
	 public void testGetPurchaseListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	
	 	String buyerId = "user13";
	 	Map<String,Object> map = purchaseService.getPurchaseList(search, buyerId);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = purchaseService.getPurchaseList(search, buyerId);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console 확인
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //@Test
	 public void testGetSaleListAll() throws Exception{
		
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	
	 	Map<String,Object> map = purchaseService.getSaleList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = purchaseService.getSaleList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console 확인
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 @Test
	 public void testGetPurchaseListByTranNo() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(Integer.toString(tranNo));
	 	
	 	String buyerId = "user13";
	 	Map<String,Object> map = purchaseService.getPurchaseList(search, buyerId);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = purchaseService.getPurchaseList(search, buyerId);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //@Test
	 public void testGetSaleListByTranNo() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("삼성");
	 	
	 	Map<String,Object> map = purchaseService.getSaleList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = purchaseService.getSaleList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
}