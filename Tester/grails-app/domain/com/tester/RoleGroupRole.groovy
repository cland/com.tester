package com.tester

import org.apache.commons.lang.builder.HashCodeBuilder

class RoleGroupRole implements Serializable {

	RoleGroup roleGroup
	Role role

	boolean equals(other) {
		if (!(other instanceof RoleGroupRole)) {
			return false
		}

		other.roleGroup?.id == roleGroup?.id &&
			other.role?.id == role?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (roleGroup) builder.append(roleGroup.id)
		if (role) builder.append(role.id)
		builder.toHashCode()
	}

	static RoleGroupRole get(long roleGroupId, long roleId) {
		find 'from RoleGroupRole where roleGroup.id=:roleGroupId and role.id=:roleId',
			[roleGroupId: roleGroupId, roleId: roleId]
	}

	static RoleGroupRole create(RoleGroup roleGroup, Role role, boolean flush = false) {
		new RoleGroupRole(roleGroup: roleGroup, role: role).save(flush: flush, insert: true)
	}

	static boolean remove(RoleGroup roleGroup, Role role, boolean flush = false) {
		RoleGroupRole instance = RoleGroupRole.findByRoleGroupAndRole(roleGroup, role)
		if (!instance) {
			return false
		}

		instance.delete(flush: flush)
		true
	}

	static void removeAll(RoleGroup roleGroup) {
		executeUpdate 'DELETE FROM RoleGroupRole WHERE roleGroup=:roleGroup', [roleGroup: roleGroup]
	}

	static void removeAll(Role role) {
		executeUpdate 'DELETE FROM RoleGroupRole WHERE role=:role', [role: role]
	}

	static mapping = {
		id composite: ['role', 'roleGroup']
		version false
	}
}
