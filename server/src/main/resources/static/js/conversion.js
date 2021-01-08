/**
 * 在这里定义数据转换器，以计算合适的数据格式
 * 所有的函数请使用AtoB的命名方式，并使用驼峰命名法
 */
class Conversion {
    /**
     * 计算organization对应的select的各项
     * @param organization {Organization}
     * @return {Array<EntryOrganization | EntryDepartment>}
     */
    organizationToDepartmentHeader(organization) {
        let entries = []
        entries.push({
            key: organization.id,
            type: 'org',
            value: organization
            })
        organization.departments.forEach((d) => {
            entries.push({
                key: d.id,
                type: 'department',
                value: d
                })
        })
        return entries
    }

    /**
     * 扩充organization的计算属性
     * @param user {User}
     * @param organization {Organization}
     */
    extendOrganizationOfUser(user, organization) {
        organization.level = organization.users.find((u) => u.uid === user.uid)
            .organizations[0].level
    }
}

conversion = new Conversion()
