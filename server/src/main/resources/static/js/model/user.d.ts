import {Department} from "./department";
import {Organization} from "./organization";
import {UserTag} from "./user_tag";

export declare class User {
    uid: string
    age: number
    name: string
    email: string
    sex: boolean
    tags: Array<UserTag>
}