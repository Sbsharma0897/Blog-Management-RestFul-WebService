package com.BlogApp.service;

import java.util.List;

import com.BlogApp.model.Reaction;
import com.BlogApp.payloads.ReactionDto;

public interface ReactionService {
	
	Reaction addReaction(Integer postId,ReactionDto reactionDto);
	
	Reaction deleteReaction(Integer reactionId);
	
	List<Reaction> getByPostId(Integer postId);
	
	List<Reaction> getByUserId(Integer userId);

}
