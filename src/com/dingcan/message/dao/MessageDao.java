package com.dingcan.message.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.dingcan.cons.Cons;
import com.dingcan.message.model.Message;
import com.dingcan.message.model.MessageRowMapper;
import com.dingcan.message.model.Order;
import com.dingcan.message.model.OrderRowMapper;
import com.dingcan.util.PageModel;

/**
 * 系统消息,比如短信,站内信等的dao层
 * 
 * @author Green Lei
 * 2012-12-2 上午9:54:33 2012
 */
public class MessageDao extends JdbcDaoSupport{

	public int addMessage(Message message){
		String sql = "INSERT INTO `t_messages` (`FromId`, `ToId`, `ContentText`, `Type`, `CreateDate`, " +
				"`PictureUrl`) VALUES "+
				"(?, ?, ?, ?, ?, ?);";
		Object[] param = new Object[] {message.getFromId(),message.getToId(),
				message.getContentText(),message.getType(),message.getCreateDate(),message.getPictureUrl()};
		return getJdbcTemplate().update(sql, param);
	}
	
	/**
	 * 找商家
	 * @param pageSize
	 * @param pageNo
	 * @return
	 * Green Lei
	 * 2012-12-2 上午11:55:07 2012
	 */
	@SuppressWarnings("unchecked")
	public PageModel<Message> findShopMessage(int pageSize,int pageNo){
		PageModel<Message> result = new PageModel<Message>();
		String sql = "SELECT * FROM t_messages where Type=? order by MessagesId desc limit ?,?;";
		List<Message> listMessage = new ArrayList<Message>();
		Object[] params = new Object[]{Cons.ADD_GOODS_MESSAGE,(pageNo-1)*pageSize,pageSize};
		try{
			listMessage = getJdbcTemplate().query(
					sql,params,new MessageRowMapper());
		}catch(EmptyResultDataAccessException e){
			listMessage = null;
		}
		result.setPageNo(pageNo);
		result.setPageSize(pageSize);
		result.setList(listMessage);
		result.setTotalRecords(getTotalRecordsOfMessages("("+Cons.ADD_GOODS_MESSAGE+")"));
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public PageModel<Message> findUserMessage(int pageSize,int pageNo){
		PageModel<Message> result = new PageModel<Message>();
		String sql = "SELECT * FROM t_messages where Type in (?,?,?) order by MessagesId desc limit ?,?;";
		List<Message> listMessage = new ArrayList<Message>();
		Object[] params = new Object[]{Cons.ADD_COMMENT_MESSAGE,Cons.ADD_MESSAGE_MESSAGE,
				Cons.ADD_SHARE_MESSAGE,(pageNo-1)*pageSize,pageSize};
		try{
			listMessage = getJdbcTemplate().query(
					sql,params,new MessageRowMapper());
		}catch(EmptyResultDataAccessException e){
			listMessage = null;
		}
		result.setPageNo(pageNo);
		result.setPageSize(pageSize);
		result.setList(listMessage);
		String type = "("+Cons.ADD_MESSAGE_MESSAGE+","+Cons.ADD_COMMENT_MESSAGE
				+","+Cons.ADD_SHARE_MESSAGE+")";
		result.setTotalRecords(getTotalRecordsOfMessages(type));
		return result;
	}
	
	public int getTotalRecordsOfMessages(String type){
		String sql = "SELECT count(1) FROM t_messages where Type in "+type;
		int result = getJdbcTemplate().queryForInt(sql);
		return result;
	}
	
	/**
	 * 
	 * @param goodsId
	 * @return
	 * Green Lei
	 * 2012-12-6 下午3:44:15 2012
	 */
	public int getMessageCountByGoodsIdAndType(int goodsId, int type){
		String sql = "SELECT count(1) FROM t_messages where Type="+type+" and ToId = "+goodsId+";";
		return getJdbcTemplate().queryForInt(sql);
	}
	
	/**
	 * 查询订餐的人
	 * @param goodsId
	 * @param type
	 * @return
	 * Green Lei
	 * 2012-12-8 下午4:14:50 2012
	 */
	@SuppressWarnings("unchecked")
	public List<Order> getOrderListByGoodsIdAndType(int goodsId, int type){
		String sql = "select t_messages.ToId as goodsId, t_messages.FromId as userId, " +
				"t_user_info.UserName,t_user_info.photo from t_messages inner join t_user_info " +
				"on t_messages.FromId=t_user_info.UserId where Type=? and ToId =? " +
				"order by t_messages.MessagesId desc;";
		return getJdbcTemplate().query(sql, new Object[] {type ,goodsId}, new OrderRowMapper());
	}
	
	/**
	 * 获得新鲜事
	 * @param pageNo
	 * @param pageSize
	 * Green Lei
	 * 2012-12-8 下午9:04:44 2012
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public PageModel<Message> getMessagePage(int pageNo, int pageSize){
		PageModel<Message> result = new PageModel<Message>();
		String sql = "SELECT * FROM t_messages order by MessagesId desc limit ?,?;";
		List<Message> listMessage = new ArrayList<Message>();
		Object[] params = new Object[]{(pageNo-1)*pageSize,pageSize};
		try{
			listMessage = getJdbcTemplate().query(
					sql,params,new MessageRowMapper());
		}catch(EmptyResultDataAccessException e){
			listMessage = null;
		}
		result.setPageNo(pageNo);
		result.setPageSize(pageSize);
		result.setList(listMessage);
		result.setTotalRecords(getTotalRecordsOfMessages());
		return result;
	}
	
	public int getTotalRecordsOfMessages(){
		String sql = "SELECT count(1) FROM t_messages ";
		int result = getJdbcTemplate().queryForInt(sql);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Message> getMessageLimited(int pageNo, int pageSize){
		String sql = "SELECT * FROM t_messages order by MessagesId desc limit ?,?;";
		List<Message> listMessage = new ArrayList<Message>();
		Object[] params = new Object[]{(pageNo-1)*pageSize,pageSize};
		try{
			listMessage = getJdbcTemplate().query(
					sql,params,new MessageRowMapper());
		}catch(EmptyResultDataAccessException e){
			listMessage = null;
		}
		return listMessage;
	}
}
