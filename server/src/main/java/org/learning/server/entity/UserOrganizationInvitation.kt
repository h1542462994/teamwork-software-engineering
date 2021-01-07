package org.learning.server.entity

import javax.persistence.*

/**
 * 表示User和Organization的邀请情况
 */
@Entity
class UserOrganizationInvitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1
    @ManyToOne(targetEntity = User::class)
    var user: User = User()
    @ManyToOne(targetEntity = Organization::class)
    var organization: Organization = Organization()
    /**
     * inverse为false表示组织申请用户加入，为true表示用户申请组织加入
     */
    var inverse: Boolean = false

    /**
     * 0为申请(Pending)状态，1为同意(Accept)状态，2为拒绝(Decline)，3为确认(Ensure)状态
     */
    var state: Int = 0
}