import {Response} from "./response";
import {User} from "./user";

export declare class ResponseUsers extends Response<Array<User>> {
    data: Array<User>
}