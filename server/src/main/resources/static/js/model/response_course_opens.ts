import {Response} from "./response";
import {CourseOpen} from "./course_open";

export declare class ResponseCourseOpens extends Response<Array<CourseOpen>> {
    data: Array<CourseOpen>
}