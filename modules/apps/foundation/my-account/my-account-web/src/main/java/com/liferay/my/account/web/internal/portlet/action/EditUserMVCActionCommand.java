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

package com.liferay.my.account.web.internal.portlet.action;

import com.liferay.my.account.web.internal.constants.MyAccountPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + MyAccountPortletKeys.MY_ACCOUNT,
		"mvc.command.name=/users_admin/edit_user"
	},
	service = MVCActionCommand.class
)
public class EditUserMVCActionCommand
	extends com.liferay.users.admin.web.portlet.action.EditUserMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		if (redirectToLogin(actionRequest, actionResponse)) {
			return;
		}

		super.doProcessAction(actionRequest, actionResponse);
	}

	@Override
	protected Object[] updateUser(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		return super.updateUser(actionRequest, actionResponse);
	}

}