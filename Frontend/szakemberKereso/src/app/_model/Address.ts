export interface Address{
    countyId: number,
    zipCode: number,
    city: string,
    street: string,
    number: string,
    staircase?: string,
    floor?: number,
    door?: number
}
