import {Department} from "./department";
import {User} from "./user";

export declare class Organization {
    id: number
    name: string
    description: string


    state: number | null
    owner: User | null
    level: number
    departments: Array<Department>
    users: Array<User>
}