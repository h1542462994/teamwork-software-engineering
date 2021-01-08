package org.learning.server.entity

import javax.persistence.*

@Entity
class UserOrgNodeInvitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1
    @ManyToOne
    var user: User = User()
    @ManyToOne
    var orgNode: OrgNode = OrgNode()
}