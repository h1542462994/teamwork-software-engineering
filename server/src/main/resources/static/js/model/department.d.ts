import {User} from "./user";
import {Organization} from "./organization";

export declare class Department {
    id: number
    name: string
    description: string

    children: Array<Department>
}
