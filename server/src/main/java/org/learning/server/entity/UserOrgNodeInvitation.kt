package org.learning.server.entity

import org.learning.server.model.complex.Invitation
import javax.persistence.*

@Entity
class UserOrgNodeInvitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1
    /**
     * inverse为false表示组织申请用户加入，为true表示用户申请组织加入
     */
    var inverse: Boolean = false
    @ManyToOne
    var user: User = User()
    @ManyToOne
    var orgNode: OrgNode = OrgNode()

    fun toInvitation(root: OrgNode): Invitation {
         return Invitation().apply {
             this.id = this@UserOrgNodeInvitation.id
             this.inverse = this@UserOrgNodeInvitation.inverse
             this.user = this@UserOrgNodeInvitation.user
             this.orgNode = this@UserOrgNodeInvitation.orgNode
             this.root = root
         }
    }
}