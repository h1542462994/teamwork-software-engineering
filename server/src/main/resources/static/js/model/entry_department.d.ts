import {Entry} from "./entry";
import {Department} from "./department";

export declare class EntryDepartment extends Entry<number, Department> {
    key: number
    value: Department
}