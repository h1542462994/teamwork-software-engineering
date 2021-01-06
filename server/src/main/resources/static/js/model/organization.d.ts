import {Department} from "./department";
import {User} from "./user";

export declare class Organization {
    id: number
    name: string
    description: string

    level: number
    departments: Array<Department>
    users: Array<User>
}