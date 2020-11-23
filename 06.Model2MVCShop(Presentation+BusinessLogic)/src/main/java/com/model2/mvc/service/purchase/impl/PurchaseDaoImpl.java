package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;

@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public PurchaseDaoImpl() {
		System.out.println(this.getClass());
	}
	
	public void setSqlSession(SqlSession sqlSession) {
		System.out.println("::"+getClass()+".setSqlSession() Call.....");
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		sqlSession.insert("PurchaseMapper.addPurchase", purchase);

	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getPurchase", tranNo);
	}

	@Override
	public Purchase getPurchase2(int prodNo) throws Exception {
		
		return null;
	}

	@Override
	public List<Purchase> getPurchaseList(Search search, String buyerId) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("buyerId", buyerId);
		
		return sqlSession.selectList("PurchaseMapper.getPurchaseList", map);
	}

	@Override
	public List<Purchase> getSaleList(Search search) throws Exception {
		return sqlSession.selectList("PurchaseMapper.getSaleList", search);
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		sqlSession.update("PurchaseMapper.updatePurchase", purchase);
	}

	@Override
	public String updateTranCode(int tranNo, String tranCode) throws Exception {
		
		String forward = null;
		
		if(tranCode.equals("000")){
			tranCode = "001";
			forward = "forward:/listSale.do";
		}else if(tranCode.equals("001")){
			tranCode = "002";
			forward = "forward:/listPurchase.do";
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tranNo", tranNo);
		map.put("tranCode", tranCode);
		
		sqlSession.update("PurchaseMapper.updateTranCode", map);
		
		return forward;
	}

	@Override
	public int getPurchaseTotalCount(Search search, String buyerId) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("buyerId", buyerId);
		
		return sqlSession.selectOne("PurchaseMapper.getPurchaseTotalCount", map);
	}
	
	@Override
	public int getSaleTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getSaleTotalCount", search);
	}

}
