package com.gogreen.apis.user.repository;

import com.gogreen.models.auth.entities.GoGreenUserEntity;
import com.gogreen.models.user.entities.CommunityUserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Named;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class GoGreenCommunityUserWrapper {
	private GoGreenUserEntity goGreenUserEntity;

	private CommunityUserEntity communityUserEntity;
}
