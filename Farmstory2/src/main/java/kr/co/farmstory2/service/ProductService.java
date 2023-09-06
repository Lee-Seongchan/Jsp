package kr.co.farmstory2.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dao.ProductDAO;
import kr.co.farmstory2.dto.ProductDTO;

public enum ProductService {
	INSTANCE;
	
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	ProductDAO dao = new ProductDAO();
	
	
	public void insertProduct(ProductDTO dto) {
		dao.insertProduct(dto);
	}
	
	public void selectProduct() {
			
	}
	
	public List<ProductDTO> selectProducts() {
		return dao.selectProducts();
	}
	
	public void updateProduct() {
			
	}
	
	public void deleteProduct() {
		
	}
	
}
