package service;

import mapper.CouponMapper;

public class CouponServiceImpl implements CouponService{

	@Override
	public String read(String uid) {
		// TODO Auto-generated method stub
		return new CouponMapper().read(uid);
	}

	

	
}
