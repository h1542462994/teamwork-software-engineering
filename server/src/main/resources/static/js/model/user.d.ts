import {Department} from "./department";
import {Organization} from "./organization";

export declare class User {
    uid: string
    age: number
    name: string
    email: string
    sex: boolean
    organizations: Array<Organization>
    departments: Array<Department>

    level: number
    state: number
}