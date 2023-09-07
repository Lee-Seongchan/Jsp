package kr.co.farmstory2.service;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dao.ProductDAO;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.ProductDTO;

public enum ProductService {
	INSTANCE;
	
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	ProductDAO dao = new ProductDAO();
	
	
	public void insertProduct(ProductDTO dto) {
		dao.insertProduct(dto);
	}
	
	public ProductDTO selectProduct(String pNo) {
		return dao.selectProduct(pNo);
	}
	
	public List<ProductDTO> selectProducts(int start) {
		return dao.selectProducts(start);
	}
	
	public List<ProductDTO> selectProduct2s(String type, int start) {
		return dao.selectProduct2s(type, start);
	}
	
	public void updateProduct() {
			
	}
	
	public void deleteProduct() {
		
	}
	
	public int selectCountProductsTotal(String type) {
		return dao.selectCountProductsTotal(type);
	}
	
}
