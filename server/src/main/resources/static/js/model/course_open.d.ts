import {Course} from "./course";
import {Organization} from "./organization";
import {Department} from "./department";

export declare class CourseOpen {
    id: number
    course: Course
    orgNode: Organization | Department
    root: Organization
    isEdit: boolean
}