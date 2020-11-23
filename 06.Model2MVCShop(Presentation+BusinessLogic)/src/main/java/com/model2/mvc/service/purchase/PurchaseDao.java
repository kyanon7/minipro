package com.model2.mvc.service.purchase;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseDao {

	// INSERT
	public void addPurchase(Purchase purchase) throws Exception;
	
	// SELECT ONE
	public Purchase getPurchase(int tranNo) throws Exception;
	
	public Purchase getPurchase2(int prodNo) throws Exception;
	
	// SELECT Purchase LIST
	public List<Purchase> getPurchaseList(Search search, String buyerId) throws Exception;
	
	// SELECT Sale LIST
	public List<Purchase> getSaleList(Search search) throws Exception;
	
	// UPDATE
	public void updatePurchase(Purchase purchase) throws Exception;
	
	// UPDATE TranCode
	public String updateTranCode(int tranNo, String tranCode) throws Exception;
	
	// Purchase 게시판 Page 처리를 위한 전체Row(totalCount) return
	public int getPurchaseTotalCount(Search search, String BuyerId) throws Exception;
	
	// Sale 게시판 Page 처리를 위한 전체Row(totalCount) return
	public int getSaleTotalCount(Search search) throws Exception;
}
