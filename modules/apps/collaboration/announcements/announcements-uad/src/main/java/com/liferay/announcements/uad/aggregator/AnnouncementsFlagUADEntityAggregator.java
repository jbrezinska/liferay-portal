/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.announcements.uad.aggregator;

import com.liferay.announcements.kernel.model.AnnouncementsFlag;
import com.liferay.announcements.kernel.service.AnnouncementsFlagLocalService;
import com.liferay.announcements.uad.constants.AnnouncementsUADConstants;
import com.liferay.announcements.uad.entity.AnnouncementsFlagUADEntity;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.user.associated.data.aggregator.BaseUADEntityAggregator;
import com.liferay.user.associated.data.aggregator.UADEntityAggregator;
import com.liferay.user.associated.data.entity.UADEntity;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Noah Sherrill
 */
@Component(
	immediate = true,
	property = {"model.class.name=" + AnnouncementsUADConstants.ANNOUNCEMENTS_FLAG},
	service = UADEntityAggregator.class
)
public class AnnouncementsFlagUADEntityAggregator
	extends BaseUADEntityAggregator {

	@Override
	public List<UADEntity> getUADEntities(long userId) {
		DynamicQuery dynamicQuery =
			_announcementsFlagLocalService.dynamicQuery();

		dynamicQuery.add(RestrictionsFactoryUtil.eq("userId", userId));

		List<AnnouncementsFlag> announcementsFlags =
			_announcementsFlagLocalService.dynamicQuery(dynamicQuery);

		List<UADEntity> uadEntities = new ArrayList<>(
			announcementsFlags.size());

		for (AnnouncementsFlag announcementsFlag : announcementsFlags) {
			uadEntities.add(
				new AnnouncementsFlagUADEntity(
					userId, String.valueOf(announcementsFlag.getFlagId()),
					announcementsFlag));
		}

		return uadEntities;
	}

	@Override
	public UADEntity getUADEntity(String uadEntityId) throws PortalException {
		AnnouncementsFlag announcementsFlag =
			_announcementsFlagLocalService.getAnnouncementsFlag(
				Long.parseLong(uadEntityId));

		return new AnnouncementsFlagUADEntity(
			announcementsFlag.getUserId(), uadEntityId, announcementsFlag);
	}

	@Reference
	private AnnouncementsFlagLocalService _announcementsFlagLocalService;

}