export default class response<T> {
    code: number
    summary: string
    message: string
    data: T
}