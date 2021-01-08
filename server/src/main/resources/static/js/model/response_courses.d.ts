import {Course} from "./course";
import {Response} from "./response";

export default class ResponseCourses extends Response<Array<Course>> {
    data: Array<Course>
}