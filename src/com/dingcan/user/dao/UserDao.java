package com.dingcan.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.dingcan.shop.model.Shop;
import com.dingcan.shop.model.ShopRowMapper;
import com.dingcan.user.model.AutoLogin;
import com.dingcan.user.model.AutoLoginRowMapper;
import com.dingcan.user.model.Collection;
import com.dingcan.user.model.CollectionRowMapper;
import com.dingcan.user.model.Friends;
import com.dingcan.user.model.FriendsRowMapper;
import com.dingcan.user.model.User;
import com.dingcan.user.model.UserRowMapper;
import com.dingcan.user.model.UserThird;
import com.dingcan.user.model.UserThirdRowMapper;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class UserDao extends JdbcDaoSupport{
	/**
	 * 添加一个用户,同时返回主键
	 * @param user
	 * @return 
	 */
	public int addUser(final User user){
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int key = 0;
		final String sql = "INSERT INTO `t_user_info` (`UserMail`, `UserPwd`, `UserName`, `UserSex`, "+ 
				"`Birthday`, `Location`, `Photo`, `RegisterTime`, `phone`, `intro`) VALUES "+
				"(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		PreparedStatementCreator psc = new PreparedStatementCreator(){
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException { 
				PreparedStatement ps = conn.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getUserMail());
				ps.setString(2, user.getUserPwd());
				ps.setString(3, user.getUserName());
				ps.setString(4, user.getUserSex());
				ps.setString(5, user.getBirthday());
				ps.setString(6, user.getLocation());
				ps.setString(7, user.getPhoto());
				ps.setString(8, user.getRegisterTime());
				ps.setString(9, user.getPhone());
				ps.setString(10, user.getIntro());
				return ps;
			}
		};
		
		getJdbcTemplate().update(psc, keyHolder);
		key = keyHolder.getKey().intValue();
		return key;
	}
	
	public int addUserThird(UserThird userThird){
		String sql = "INSERT INTO `t_user_third` (`UserId`, `ThirdUserId`, `LoginType`, `AccessToken`, "+ 
				"`invalid`) VALUES "+
				"(?, ?, ?, ?, ?);";
		Object[] param = new Object[] {userThird.getUserId(),userThird.getThirdUserId(),userThird.getLoginType(),
				userThird.getAccessToken(),userThird.getInvalid()};
		return getJdbcTemplate().update(sql, param);
	}
	
	/**
	 * 
	 * @param thirdUid
	 * @param loginType
	 * @return
	 * 2013-5-12 上午1:22:04 2013
	 */
	@SuppressWarnings("unchecked")
	public UserThird getUserThirdByThirdIdAndLoginType(String thirdUid, int loginType){
		String sql = "select * from `t_user_third` where ThirdUserId=? and LoginType=?";
		UserThird userThird = null;
		try{
			userThird = (UserThird)getJdbcTemplate().queryForObject(
					sql, new Object[] {thirdUid, loginType}, 
					new UserThirdRowMapper());
		}catch(EmptyResultDataAccessException e){
			userThird = null;
		}
		return userThird;
	}
	
	/**
	 * @param userid
	 * @param loginType
	 * @return
	 * 2013-5-12 上午1:21:49 2013
	 */
	@SuppressWarnings("unchecked")
	public UserThird getUserThirdByUserIdAndLoginType(String userid, int loginType){
		String sql = "select * from `t_user_third` where UserId=? and LoginType=?";
		UserThird userThird = null;
		try{
			userThird = (UserThird)getJdbcTemplate().queryForObject(
					sql, new Object[] {userid, loginType}, 
					new UserThirdRowMapper());
		}catch(EmptyResultDataAccessException e){
			userThird = null;
		}
		return userThird;
	}

	/**
	 * 添加一条自动登录的记录
	 * @param autoLogin
	 * @return
	 * Green Lei
	 * 2012-11-29 下午2:14:25 2012
	 */
	public int addAutoLogin(AutoLogin autoLogin){
		String sql = "INSERT INTO `t_aotologin` (`UserId`, `SessionId`) VALUES "+
				"(?, ?);";
		Object[] param = new Object[] {autoLogin.getUserId(),autoLogin.getSessionId()};
		int result = 0;
		try{
			result = getJdbcTemplate().update(sql, param);
		}catch(Exception e){
			//TODO 已经有自动登录的记录,
		}
		return result;
	}
	
	/**
	 * 
	 * @param sessionId
	 * @return
	 * Green Lei
	 * 2012-11-29 下午2:29:21 2012
	 */
	@SuppressWarnings("unchecked")
	public AutoLogin getAutoLoginBySessionId(String sessionId){
		String sql = "select * from `t_aotologin` where SessionId=?";
		AutoLogin autoLogin = null;
		try{
			autoLogin = (AutoLogin)getJdbcTemplate().queryForObject(
					sql, new Object[] {sessionId}, 
					new AutoLoginRowMapper());
		}catch(EmptyResultDataAccessException e){
			autoLogin = null;
		}
		return autoLogin;
	}
	
	@SuppressWarnings("unchecked")
	public User getUserByUserid(String userId){
		String sql = "select * from `t_user_info` where UserId=?";
		User user = null;
		try{
			user = (User)getJdbcTemplate().queryForObject(
					sql, new Object[] {userId}, 
					new UserRowMapper());
		}catch(EmptyResultDataAccessException e){
			user = null;
		}
		return user;
	}

	public int updateUserPhoneAndLocation(User user){
		String sql = "UPDATE `t_user_info` SET `Location` = ?, `phone` = ? WHERE UserId=?;";
		Object[] param = new Object[] {user.getLocation(),user.getPhone(),user.getUserId()};
		return getJdbcTemplate().update(sql, param);
	}
	
	/**
	 * 获取新注册的用户
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> getNewUserList(int quantity){
		String sql = "SELECT * FROM t_user_info order by UserId desc limit 0,"+quantity;
		return getJdbcTemplate().query(sql, new UserRowMapper());
	}
	
	/**
	 * 添加好友
	 * @param myId
	 * @param friendId
	 * @param state
	 * @return
	 * 2013-4-28 下午12:59:43 2013
	 */
	public int addFriends(int myId, int friendId, int state){
		String sql = "INSERT INTO `t_friends` (`MyId`, `FriendId`, `State`) VALUES "+
				"(?, ?, ?);";
		Object[] param = new Object[] {myId,friendId,state};
		return getJdbcTemplate().update(sql, param);
	}
	
	/**
	 * 获得用户的好友
	 * @param userId
	 * @return
	 * 2013-4-28 下午5:46:44 2013
	 */
	@SuppressWarnings("unchecked")
	public List<Friends> getMyFriends(int userId){
		String sql = "SELECT * FROM t_friends where MyId = ? order by id desc;";
		return getJdbcTemplate().query(sql,new Object[] {userId}, new FriendsRowMapper());
	}
	
	/**
	 * 根据邮箱获得用户
	 * @param userMail
	 * @return
	 * 2013-4-29 上午9:14:09 2013
	 */
	@SuppressWarnings("unchecked")
	public User getUserByUserMail(String userMail){
		String sql = "select * from `t_user_info` where UserMail=?";
		User user = null;
		try{
			user = (User)getJdbcTemplate().queryForObject(
					sql, new Object[] {userMail}, 
					new UserRowMapper());
		}catch(EmptyResultDataAccessException e){
			user = null;
		}catch(IncorrectResultSizeDataAccessException e){
			user = new User();
		}
		return user;
	}
	
	/**
	 * 根据电话查询用户
	 * @param phone
	 * @return
	 * 2013-5-10 上午2:06:30 2013
	 */
	@SuppressWarnings("unchecked")
	public User getUserByUserPhone(String phone){
		String sql = "select * from `t_user_info` where phone=?";
		User user = null;
		try{
			user = (User)getJdbcTemplate().queryForObject(
					sql, new Object[] {phone}, 
					new UserRowMapper());
		}catch(EmptyResultDataAccessException e){
			user = null;
		}catch(IncorrectResultSizeDataAccessException e){
			user = new User();
		}
		return user;
	}
	
	/**
	 * 修改用户昵称,电话,地址
	 * @param user
	 * @return
	 * 2013-4-29 上午10:14:28 2013
	 */
	public int updateUserInfo(User user){
		String sql = "UPDATE `t_user_info` SET `Location` = ?,`UserName` = ?, `phone` = ? WHERE UserId=?;";
		Object[] param = new Object[] {user.getLocation(),user.getUserName(),user.getPhone(),user.getUserId()};
		return getJdbcTemplate().update(sql, param);
	}
	
	/**
	 * 收藏一个商铺
	 * @param collection
	 * @return
	 * 2013-4-29 下午10:57:40 2013
	 */
	public int addCollection(Collection collection){
		String sql = "INSERT INTO `t_collection` (`UserId`, `ShopId`, `Date`) VALUES "+
				"(?, ?, ?);";
		Object[] param = new Object[] {collection.getUserId(),collection.getShopId(),collection.getDate()};
		return getJdbcTemplate().update(sql, param);
	}
	
	/**
	 * @param userId
	 * @return
	 * 2013-4-29 下午10:59:42 2013
	 */
	@SuppressWarnings("unchecked")
	public List<Collection> getMyCollections(int userId){
		String sql = "SELECT * FROM t_collection where UserId = ? order by id desc;";
		return getJdbcTemplate().query(sql,new Object[] {userId}, new CollectionRowMapper());
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 * 2013-4-29 下午11:21:21 2013
	 */
	@SuppressWarnings("unchecked")
	public List<Shop> getMyCollectedShops(int userId){
		String sql = "select * from t_shop where ShopId in (select ShopId from t_collection where UserId =?);";
		return getJdbcTemplate().query(sql,new Object[] {userId}, new ShopRowMapper());
	}
	
	/**
	 * 
	 * @param userId
	 * @param shopId
	 * @return
	 * 2013-4-29 下午11:32:17 2013
	 */
	public int delCollection(int userId, int shopId){
		String sql = "delete from t_collection WHERE UserId=? and ShopId=?;";
		Object[] param = new Object[] {userId,shopId};
		return getJdbcTemplate().update(sql, param);
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 * 2013-4-29 下午11:44:20 2013
	 */
	@SuppressWarnings("unchecked")
	public List<User> getMyFriendsOfUser(int userId){
		String sql = "select * from t_user_info where UserId in (select FriendId from t_friends where MyId =?);";
		return getJdbcTemplate().query(sql,new Object[] {userId}, new UserRowMapper());
	}
	
	/**
	 * 
	 * @param myId
	 * @param friendId
	 * @return
	 * 2013-4-29 下午11:44:24 2013
	 */
	public int delFriend(int myId, int friendId){
		String sql = "delete from t_friends WHERE MyId=? and FriendId=?;";
		Object[] param = new Object[] {myId,friendId};
		return getJdbcTemplate().update(sql, param);
	}
	
	public int updateUserAvatar(User user){
		String sql = "UPDATE `t_user_info` SET `Photo` = ? WHERE UserId=?;";
		Object[] param = new Object[] {user.getPhoto(),user.getUserId()};
		return getJdbcTemplate().update(sql, param);
	}
}
