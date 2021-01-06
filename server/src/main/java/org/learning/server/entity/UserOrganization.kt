package org.learning.server.entity

import javax.persistence.*

@Entity
class UserOrganization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1
    @ManyToOne(targetEntity = User::class)
    var user: User = User()
    @ManyToOne(targetEntity = Organization::class)
    var organization: Organization = Organization()
    /**
     * 等级，level=0表示普通用户，level=1表示次管理员，level=2表示主管理员
     */
    var level: Int = 0
}