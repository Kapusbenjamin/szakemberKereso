export interface Address{
    id?: number
    county?:string,
    countyId: number,
    zipCode: number,
    city: string,
    street: string,
    number: string,
    staircase?: string | null,
    floor?: number | null,
    door?: number | null
}
