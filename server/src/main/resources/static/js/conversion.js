/**
 * 在这里定义数据转换器，以计算合适的数据格式
 * 所有的函数请使用AtoB的命名方式，并使用驼峰命名法
 */
class Conversion {
    /**
     * 计算organization对应的select的各项
     * @param organization {organization}
     * @return {Array<entry_string>}
     */
    organizationToDepartmentHeader(organization) {
        let entries = []
        entries.push({
            key: organization.id,
            type: 'org',
            value: organization.name
            })
        organization.departments.forEach((d) => {
            entries.push({
                key: d.id,
                type: 'department',
                value: d.name
                })
        })
        return entries
    }
}

conversion = new Conversion()
