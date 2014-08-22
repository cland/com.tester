package com.tester

import org.apache.commons.lang.builder.HashCodeBuilder

class UserRoleGroup implements Serializable {

	User user
	RoleGroup roleGroup

	boolean equals(other) {
		if (!(other instanceof UserRoleGroup)) {
			return false
		}

		other.user?.id == user?.id &&
			other.roleGroup?.id == roleGroup?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (user) builder.append(user.id)
		if (roleGroup) builder.append(roleGroup.id)
		builder.toHashCode()
	}

	static UserRoleGroup get(long userId, long roleGroupId) {
		find 'from UserRoleGroup where user.id=:userId and roleGroup.id=:roleGroupId',
			[userId: userId, roleGroupId: roleGroupId]
	}

	static UserRoleGroup create(User user, RoleGroup roleGroup, boolean flush = false) {
		new UserRoleGroup(user: user, roleGroup: roleGroup).save(flush: flush, insert: true)
	}

	static boolean remove(User user, RoleGroup roleGroup, boolean flush = false) {
		UserRoleGroup instance = UserRoleGroup.findByUserAndRoleGroup(user, roleGroup)
		if (!instance) {
			return false
		}

		instance.delete(flush: flush)
		true
	}

	static void removeAll(User user) {
		executeUpdate 'DELETE FROM UserRoleGroup WHERE user=:user', [user: user]
	}

	static void removeAll(RoleGroup roleGroup) {
		executeUpdate 'DELETE FROM UserRoleGroup WHERE roleGroup=:roleGroup', [roleGroup: roleGroup]
	}

	static mapping = {
		id composite: ['roleGroup', 'user']
		version false
	}
}
