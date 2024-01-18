package com.rushi.service;

import java.util.ArrayList;
import java.util.List;

import com.rushi.models.Reels;
import com.rushi.models.User;

public interface ReelsService {

	public Reels Createreesl(User user ,Reels reels);
	
	public List<Reels> finsAllReels();
	
	public List<Reels> findUserReels(Integer userid) throws Exception;
	
}
