package cn.itcast.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.order.dao.OrderDao;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.utils.PageBean;

/**
 * 订单模块:业务层代码
 * @author 9528
 *
 */
@Transactional
public class OrderService {
		//注入orderDao
		private OrderDao orderDao;

		public void setOrderDao(OrderDao orderDao) {
			this.orderDao = orderDao;
		}
		//保存订单的业务层代码
		public void save(Order order) {
			orderDao.save(order);
		}
		//我的订单的业务层的代码
		public PageBean<Order> findByPageUid(Integer uid, Integer page) {
			PageBean<Order> pageBean=new PageBean<Order>();
			// 设置当前页数:
			pageBean.setPage(page);
			// 设置每页显示记录数:
			// 显示5个
			int limit = 5;
			pageBean.setLimit(limit);
			// 设置总记录数:
			int totalCount = 0;
			totalCount = orderDao.findCountByUid(uid);
			pageBean.setTotalCount(totalCount);
			// 设置总页数
			int totalPage = 0;
			if(totalCount % limit == 0){
				totalPage = totalCount / limit;
			}else{
				totalPage = totalCount / limit + 1;
			}
			pageBean.setTotalPage(totalPage);
			// 设置每页显示数据集合:
			int begin = (page - 1)*limit;
			List<Order> list = orderDao.findPageByUid(uid,begin,limit);
			pageBean.setList(list);
			return pageBean;
		}
		//业务层：根据订单id查询订单
		public Order findByOid(Integer oid) {
			return orderDao.findByOid(oid);
		}
		//业务层：修改订单的操作
		public void update(Order currOrder) {
			orderDao.update(currOrder);
		}
		//业务层：后台分页查询订单的方法
		public PageBean<Order> findByPage(Integer page) {
			PageBean<Order> pageBean=new PageBean<Order>();
			// 设置当前页数:
			pageBean.setPage(page);
			// 设置每页显示记录数:
			int limit = 10;
			pageBean.setLimit(limit);
			// 设置总记录数:
			int totalCount = orderDao.findByCount();
			pageBean.setTotalCount(totalCount);
			// 设置总页数
			int totalPage = 0;
			if(totalCount % limit == 0){
				totalPage = totalCount / limit;
			}else{
				totalPage = totalCount / limit + 1;
			}
			pageBean.setTotalPage(totalPage);
			//设置每页显示的数据的集合
			int begin = (page - 1)*limit;
			List<Order> list = orderDao.findByPage(begin,limit);
			pageBean.setList(list);
			return pageBean;
		}
		
		//业务层完成根据订单id查询订单项的方法
		public List<OrderItem> findOrderItem(Integer oid) {
			return orderDao.findOrderItem(oid);
		}
		
}
