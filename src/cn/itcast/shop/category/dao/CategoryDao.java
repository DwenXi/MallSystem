package cn.itcast.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

import cn.itcast.shop.category.vo.Category;

public class CategoryDao extends HibernateDaoSupport{
	
	//dao层查询所有一级分类的方法
	public List<Category> findAll() {
		String hql = "from Category";
		List<Category> list = this.getHibernateTemplate().find(hql);
			return list;
	}
	
	//dao层保存一级分类的方法
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}
	
	//dao层根据cid查询一级分类的方法
	public Category findByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class, cid);
	}
	
	//dao层的删除一级分类的方法
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}
	
	//dao层修改一级分类的方法
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}

}
