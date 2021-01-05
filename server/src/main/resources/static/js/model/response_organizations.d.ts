import {organization} from "./organization";
import response from "./response";

export default class response_organizations extends response<Array<organization>> {
    data: Array<organization>
}