/*******************************************************************************
 * OpenCPS is the open source Core Public Services software
 * Copyright (C) 2016-present OpenCPS community
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/

package org.opencps.usermgt.permissions;

import org.opencps.usermgt.model.Employee;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author trungnt
 */
public class EmployeePermission {

	public static void check(PermissionChecker permissionChecker, long threadId,
			String actionId) throws PortalException, SystemException {

		if (!contains(permissionChecker, threadId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(PermissionChecker permissionChecker,
			Employee employee, String actionId)
			throws PortalException, SystemException {

		if (!contains(permissionChecker, employee, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(PermissionChecker permissionChecker,
			Employee employee, String actionId)
			throws PortalException, SystemException {

		return permissionChecker.hasPermission(employee.getGroupId(),
				Employee.class.getName(), employee.getPrimaryKey(), actionId);
	}

	public static boolean contains(PermissionChecker permissionChecker,
			long groupId, String actionId) {

		return permissionChecker.hasPermission(groupId,
				Employee.class.getName(), groupId, actionId);
	}
}
