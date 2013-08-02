package com.dingcan.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dingcan.goods.model.Goods;

/**
 * 购物车管理类
 * @author Administrator
 * 2013-4-29 下午12:48:56 2013
 *
 */
public class CartManager {
	/**
	 * 往购物车加入商品
	 * @param goods
	 * @param request
	 * 2013-4-29 下午1:03:52 2013
	 */
	public static void addGoods(Goods goods, HttpServletRequest request){
		HttpSession session = request.getSession(true);
		@SuppressWarnings("unchecked")
		List<Goods> cart = (List<Goods>)session.getAttribute("cart");
		if(cart == null){
			cart = new ArrayList<Goods>();
		}
		cart.add(goods);
		session.setAttribute("cart", cart);
	}
	
	/**
	 * 删除商品
	 * @param goods
	 * @param request
	 * 2013-4-29 下午1:16:13 2013
	 */
	public static void delGoods(Goods goods, HttpServletRequest request){
		HttpSession session = request.getSession(true);
		@SuppressWarnings("unchecked")
		List<Goods> cart = (List<Goods>)session.getAttribute("cart");
		if(cart == null){
			cart = new ArrayList<Goods>();
		}
		for ( int i = 0; i < cart.size(); i ++ ) {
			if(goods.getGoodsId() == cart.get(i).getGoodsId()){
				cart.remove(i);
			}
		} 
		session.setAttribute("cart", cart);
	}
	
	/**
	 * 清空购物车
	 * @param request
	 * 2013-5-1 下午3:54:57 2013
	 */
	public static void clearGoods(HttpServletRequest request){
		request.getSession(true).removeAttribute("cart");
	}
	
	/**
	 * 获取购物车中的物品
	 * @param request
	 * @return
	 * 2013-4-29 下午1:17:27 2013
	 */
	@SuppressWarnings("unchecked")
	public static List<Goods> getGoodsList(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		List<Goods> list = (List<Goods>)session.getAttribute("cart");
		if(list == null){
			list = new ArrayList<Goods>();
		}
		for ( int i = 0 ; i < list.size() - 1 ; i ++ ) {
			for ( int j = list.size() - 1 ; j > i; j -- ) {
				if (list.get(j).getGoodsId()==(list.get(i).getGoodsId())) {
					list.get(i).setCount(list.get(i).getCount()+list.get(j).getCount());
					list.remove(j);
				} 
	       } 
	    } 
		return list;
	}
	
	/**
	 * 获得总价
	 * @param request
	 * @return
	 * 2013-4-30 下午10:10:57 2013
	 */
	public static Double getTotalPrice(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		@SuppressWarnings("unchecked")
		List<Goods> list = (List<Goods>)session.getAttribute("cart");
		Double total = 0.0;
		if(list == null){
			list = new ArrayList<Goods>();
		}
		for(Goods goods:list){
			total = goods.getPriceDiscount()*goods.getCount()+total;
		}
		return total;
	}
	
	/**
	 * 总物品数量
	 * @param request
	 * @return
	 * 2013-4-30 下午10:11:50 2013
	 */
	@SuppressWarnings("unchecked")
	public static int getTotalNum(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		List<Goods> list= (List<Goods>)session.getAttribute("cart");
		int total = 0;
		if(list == null){
			list = new ArrayList<Goods>();
		}
		for(Goods goods:list){
			total = goods.getCount()+total;
		}
		return total;
	}
}
