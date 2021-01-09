import {course} from "./course";
import {response} from "./response";

export default class response_courses extends response<Array<course>> {
    data: Array<course>
}