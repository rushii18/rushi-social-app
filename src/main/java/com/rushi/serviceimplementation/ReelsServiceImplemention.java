package com.rushi.serviceimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rushi.models.Reels;
import com.rushi.models.User;
import com.rushi.repository.ReelsRepository;
import com.rushi.service.ReelsService;
import com.rushi.service.UserService;

@Service
public class ReelsServiceImplemention implements ReelsService {
	@Autowired
	private ReelsRepository reelsRepository;

	@Autowired
	private UserService userService;

	@Override
	public Reels Createreesl(User user, Reels reels) {
		Reels createreels = new Reels();

		createreels.setTitel(reels.getTitel());
		createreels.setVideo(reels.getVideo());
		createreels.setUser(user);

		return reelsRepository.save(createreels);
	}

	@Override
	public List<Reels> findUserReels(Integer userid) throws Exception {
		userService.findUserByid(userid);
		return reelsRepository.findByUserId(userid);
	}

	@Override
	public List<Reels> finsAllReels() {

		return reelsRepository.findAll();
	}

}
