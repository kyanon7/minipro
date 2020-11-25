package com.model2.mvc.service.product.test;

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
import com.model2.mvc.service.product.ProductService;


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
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	public int prodNo = 10281;

	//@Test
	public void testAddProduct() throws Exception {
		
		Product product = new Product();
		product.setProdNo(prodNo);
		product.setProdName("상품상품");
		product.setProdDetail("조흔 물건이다");
		product.setManuDate("20201113");
		product.setPrice(300000);
		product.setFileName("/sdd/qqq.txt");
		
		productService.addProduct(product);
		
		System.out.println(product);
		
		product = productService.getProduct(prodNo);

		//==> console 확인
		System.out.println(product);
		
		//==> API 확인
		Assert.assertEquals(prodNo, product.getProdNo());
		Assert.assertEquals("상품상품", product.getProdName());
		Assert.assertEquals("조흔 물건이다", product.getProdDetail());
		Assert.assertEquals("20201113", product.getManuDate());
		Assert.assertEquals(300000, product.getPrice());
		Assert.assertEquals("/sdd/qqq.txt", product.getFileName());
	}
	
	//@Test
	public void testGetProduct() throws Exception {
		
		Product product = new Product();
		//==> 필요하다면...
//		product.setProdNo(prodNo);
//		product.setProdName("상품상품");
//		product.setProdDetail("조흔 물건이다");
//		product.setManuDate("20201113");
//		product.setPrice(300000);
//		product.setFileName("/sdd/qqq.txt");
		
		product = productService.getProduct(prodNo);

		//==> console 확인
		System.out.println(product);
		
		//==> API 확인
		Assert.assertEquals(prodNo, product.getProdNo());
		Assert.assertEquals("상품상품", product.getProdName());
		Assert.assertEquals("조흔 물건이다", product.getProdDetail());
		Assert.assertEquals("20201113", product.getManuDate());
		Assert.assertEquals(300000, product.getPrice());
		Assert.assertEquals("/sdd/qqq.txt", product.getFileName());
//
//		Assert.assertNotNull(productService.getProduct(10100));
		Assert.assertNotNull(productService.getProduct(prodNo));
	}
	
	//@Test
	 public void testUpdateProduct() throws Exception{
		 
		Product product = productService.getProduct(prodNo);
		Assert.assertNotNull(product);
		
		Assert.assertEquals(prodNo, product.getProdNo());
		Assert.assertEquals("상품상품", product.getProdName());
		Assert.assertEquals("조흔 물건이다", product.getProdDetail());
		Assert.assertEquals("20201113", product.getManuDate());
		Assert.assertEquals(300000, product.getPrice());
		Assert.assertEquals("/sdd/qqq.txt", product.getFileName());

		product.setProdName("대박물건");
		product.setProdDetail("조흔 상품이다");
		product.setPrice(400000);
		product.setManuDate("20201118");
		
		productService.updateProduct(product);
		
		product = productService.getProduct(prodNo);
		Assert.assertNotNull(product);
		
		//==> console 확인
		//System.out.println(user);
			
		//==> API 확인
		Assert.assertEquals("대박물건", product.getProdName());
		Assert.assertEquals("조흔 상품이다", product.getProdDetail());
		Assert.assertEquals(400000, product.getPrice());
		Assert.assertEquals("20201118", product.getManuDate());
	 }
	 
	
	 //==>  주석을 풀고 실행하면....
	 @Test
	 public void testGetProductListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console 확인
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
//	 @Test
	 public void testGetProductListByProdNo() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(Integer.toString(prodNo));
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //@Test
	 public void testGetUserListByProdName() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("삼성");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }	 
}