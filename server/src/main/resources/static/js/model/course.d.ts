
import {CourseTag} from "./course_tag";
import {User} from "./user";

export declare class Course {
    id: number
    info: string
    name: string
    pic: string
    courseTags: Array<CourseTag>
    owner: User
}