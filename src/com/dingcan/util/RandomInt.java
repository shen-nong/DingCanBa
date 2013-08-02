package com.dingcan.util;

public class RandomInt {

	/**
	 * 获得从from到to的随即整数
	 * @param from
	 * @param to
	 * @return
	 * 2013-5-1 下午1:53:12 2013
	 */
	public static int getRandomInt(int from, int to){
	    int i = (int) (Math.random()* to+from);//产生0-10的双精度随机数   
	    return i;
	}
	
	public static void main(String args[]){
		System.out.println("default/avatar/w200_"+getRandomInt(50, 50)+".jpg");
//		System.out.println("default/logo/w200_"+getRandomInt(0, 10)+".jpg");
	}
}
