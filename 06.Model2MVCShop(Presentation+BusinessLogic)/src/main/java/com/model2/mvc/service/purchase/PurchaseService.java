package com.model2.mvc.service.purchase;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;


public interface PurchaseService {

	// 상품 구매
	public void addPurchase(Purchase purchase) throws Exception;
	
	// 구매 상품 확인
	public Purchase getPurchase(int tranNo) throws Exception;
	
	public Purchase getPurchase2(int prodNo) throws Exception;
	
	// 구매 상품 리스트
	public Map<String,Object> getPurchaseList(Search search, String buyerId) throws Exception;
	
	// 팔린 상품 리스트
	public Map<String,Object> getSaleList(Search search) throws Exception;
	
	// 구매 정보 수정
	public void updatePurchase(Purchase purchase) throws Exception;
	
	// 배송 정보 수정
	public void updateTranCode(Purchase purchase) throws Exception;
	
}