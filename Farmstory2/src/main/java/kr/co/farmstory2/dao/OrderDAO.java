package kr.co.farmstory2.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.OrderDTO;

public class OrderDAO extends DBHelper{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public void insertOrder() {
		
	}
	
	public OrderDTO selectOrder(String orderNo) {
		
		try {
			conn = getConnection();
			
		} catch (Exception e) {

		}
		
		
		return null;
	}
	
	public List<OrderDTO> selectOrders() {
		List<OrderDTO> orders = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ORDERS);
			//psmt.setInt(1, start);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				OrderDTO dto = new OrderDTO();
				dto.setOrderNo(rs.getInt(1));
				dto.setOrderProduct(rs.getInt(2));
				dto.setOrderCount(rs.getInt(3));
				dto.setOrderDelivery(rs.getInt(4));
				dto.setOrderPrice(rs.getInt(5));
				dto.setOrderTotal(rs.getInt(6));
				dto.setReceiver(rs.getString(7));
				dto.setHp(rs.getString(8));
				dto.setZip(rs.getString(9));
				dto.setAddr1(rs.getString(10));
				dto.setAddr2(rs.getString(11));
				dto.setOrderEtc(rs.getString(12));
				dto.setOrderUser(rs.getString(13));
				dto.setOrderDate(rs.getString(14));
				dto.setpName(rs.getString(15));
				dto.setThumb1(rs.getString(16));
				orders.add(dto);
			}
			close();
			
		} catch (Exception e) {
			logger.debug("selectOrders()" + e.getMessage());
		}
		return orders;
	}
	
	public void updateOrder() {
		
	}
	
	public void deleteOrder() {
		
	}
	
	
}
