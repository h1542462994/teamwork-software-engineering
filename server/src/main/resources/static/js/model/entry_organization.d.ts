import {Organization} from "./organization";
import {Entry} from "./entry";

export declare class EntryOrganization extends Entry<number, Organization> {
    key: number
    value: Organization
}