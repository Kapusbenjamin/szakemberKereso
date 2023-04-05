import { Tag } from "./Tag"

export interface Address{
    id?: number
    county?:Tag,
    countyId: number,
    zipCode: number,
    city: string,
    street: string,
    number: string,
    staircase?: string | null,
    floor?: number | null,
    door?: number | null
}
