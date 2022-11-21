package com.BlogApp.payloads;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.BlogApp.model.Emoji;
import com.BlogApp.model.User;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReactionDto implements Serializable{
	
	@Enumerated(EnumType.STRING)
	private Emoji emojiString;
	

}
