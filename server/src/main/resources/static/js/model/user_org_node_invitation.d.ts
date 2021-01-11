import {User} from "./user";
import {Organization} from "./organization";
import {Department} from "./department";

export declare class UserOrgNodeInvitation {
    id: number
    inverse: boolean
    user: User
    orgNode: Organization | Department
    root: Organization
}